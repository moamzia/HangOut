package com.moamzia.validator;

import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In order to make use of this Enum, you should do the following steps:<br>
 * 1. Create a new ValidationRule by creating a new class extending {@link ValidationRule}<br>
 * 2. Define an specific type for {@link ValidationRule} extended by your custom rule like {@code MyCustomRule extends ValidationRule<Integer>}<br>
 * 3. Add as many number of {@link TextView}s as you'd like to with as many as custom validation rules using {@link ViewValidator#addViewAndItsValidationRules(TextView, Integer, Class, Object, int)}<br>
 * 4. Call {@link ViewValidator#validate(int)} method to start the validation whenever you need in your life cycle (e.g. onClickListener of a button)
 * <p/>
 * Created by Amin on 13-Jan-16.
 */
public enum ViewValidator {
    INSTANCE;
    private static final String TAG = "com.moamzia.validator.ViewValidator";
    private final Map<TextView, ViewValidationRulesHolder> viewValidationRulesHolders = new HashMap<>();

    private class ViewValidationRulesHolder {
        private Integer triggeringViewId;
        private final Map<Class<? extends ValidationRule>, ValidationRule> validationRules = new ConcurrentHashMap<>();

        <T> void addValidationRule(Class<? extends ValidationRule<T>> validationRuleClazz, T validationRuleValue, int errorTextRID) {
            try {
                ValidationRule validationRule = validationRules.get(validationRuleClazz);
                if (validationRule == null) {
                    Class<?> clazz = validationRuleValue.getClass();
                    Constructor<? extends ValidationRule<T>> constructor = validationRuleClazz.getDeclaredConstructor(Integer.class, clazz);
                    validationRule = constructor.newInstance(errorTextRID, validationRuleValue);
                } else {
                    validationRule.setRuleValue(validationRuleValue);
                    validationRule.setErrorText(errorTextRID);
                }

                validationRules.put(validationRuleClazz, validationRule);
            } catch (InstantiationException e) {
                Log.e(TAG, e.getMessage());
            } catch (IllegalAccessException e) {
                Log.e(TAG, e.getMessage());
            } catch (NoSuchMethodException e) {
                Log.e(TAG, e.getMessage());
            } catch (InvocationTargetException e) {
                Log.e(TAG, e.getMessage());
            }
        }

        Map<Class<? extends ValidationRule>, ValidationRule> getValidationRules() {
            return validationRules;
        }
    }

    /**
     * This method should be called for the following scenarios:<br>
     * 1. You're adding a view for the first time with its first validation rule<br>
     * 2. You're adding a duplicate view with a new validation rule<br>
     * 3. You're adding a duplicate view with a duplicate rule but new rule value or error text RID<br>
     * For all scenarios mentioned above, this method will handle it properly.
     *
     * @param view
     * @param triggeringViewId
     * @param validationRuleClazz
     * @param validationRuleValue
     * @param errorTextRID
     * @param <T>
     */
    public static <T> void addViewAndItsValidationRules(TextView view, Integer triggeringViewId, Class<? extends ValidationRule<T>> validationRuleClazz, T validationRuleValue, int errorTextRID) {
        ViewValidationRulesHolder rulesHolder = ViewValidator.INSTANCE.viewValidationRulesHolders.get(view);
        if (rulesHolder == null) {
            rulesHolder = ViewValidator.INSTANCE.new ViewValidationRulesHolder();
        }

        rulesHolder.triggeringViewId = triggeringViewId;
        rulesHolder.addValidationRule(validationRuleClazz, validationRuleValue, errorTextRID);

        ViewValidator.INSTANCE.viewValidationRulesHolders.put(view, rulesHolder);
    }

    /**
     * The TextViews should be bound to a triggeringViewId like a button in most cases which when pressed,
     * it has to trigger the validation.
     * This method will start the validation and will return true if all validations were passed for TextViews having the triggeringViewID specified
     * TODO: Add possibility of one view being bound to more than one Triggering View ID.
     *
     * @param triggeringViewId
     * @return boolean. If all validations pass: true else false.
     */
    public static boolean validate(int triggeringViewId) {
        boolean isValid = true;
        for (Map.Entry<TextView, ViewValidationRulesHolder> entries : ViewValidator.INSTANCE.viewValidationRulesHolders.entrySet()) {
            //We would only validate if the TextView has an specified triggering view ID and it's equals the one passed.
            //OR if it doesn't have any specified.
            if (entries.getValue().triggeringViewId == null || (entries.getValue().triggeringViewId != null && entries.getValue().triggeringViewId.equals(triggeringViewId))) {
                for (ValidationRule rule : entries.getValue().getValidationRules().values()) {
                    //For each validation rule, we'll check it's specific isValid method.
                    if (!rule.isValid(entries.getKey())) {
                        //if not valid, we'll return false and set the Error property of the view with specified error text.
                        isValid = false;
                        entries.getKey().setError(entries.getKey().getContext().getString(rule.getErrorText()));
                        break;
                    }
                }
            }
        }
        return isValid;
    }
}
