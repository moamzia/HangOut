package com.hangout.textviewwithvalidator.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.hangout.textviewwithvalidator.R;
import com.hangout.textviewwithvalidator.rules.FieldRequiredRule;
import com.hangout.textviewwithvalidator.rules.MaxLengthAllowedRule;
import com.hangout.textviewwithvalidator.rules.MinLengthAllowedRule;
import com.hangout.textviewwithvalidator.rules.field.type.FieldTypeRule;
import com.moamzia.validator.ViewValidator;

/**
 * This View has a validator that can be used with it. Its attributes are the validation attributes
 * that can be defined on the view XML itself statically.
 * Then {@link ViewValidator} should be used to perform the validation
 * whenever necessary.
 * <p/>
 * Created by Amin on 27-Jan-16.
 */
public class TextViewWithValidator extends AutoCompleteTextView {
    private Integer triggeringViewId;

    public TextViewWithValidator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextViewWithValidator, 0, 0);
        try {
            if (typedArray.hasValue(R.styleable.TextViewWithValidator_triggeringViewId)) {
                triggeringViewId = typedArray.getResourceId(R.styleable.TextViewWithValidator_triggeringViewId, 0);
            }

            if (typedArray.hasValue(R.styleable.TextViewWithValidator_maxLengthAllowed)) {
                int maxLength = typedArray.getInt(R.styleable.TextViewWithValidator_maxLengthAllowed, -1);
                int maxLengthErrorMessageRID = typedArray.getResourceId(R.styleable.TextViewWithValidator_maxLengthAllowed_errorMessage, MaxLengthAllowedRule.DEFAULT_ERROR_RESOURCE_ID);
                //Put the validation rule only if it's set in layout.
                if (maxLength > 0) {
                    ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, MaxLengthAllowedRule.class, maxLength, maxLengthErrorMessageRID);
                }
            }

            if (typedArray.hasValue(R.styleable.TextViewWithValidator_minLengthAllowed)) {
                int minLength = typedArray.getInt(R.styleable.TextViewWithValidator_minLengthAllowed, 0);
                int minLengthErrorMessageRID = typedArray.getResourceId(R.styleable.TextViewWithValidator_minLengthAllowed_errorMessage, MinLengthAllowedRule.DEFAULT_ERROR_RESOURCE_ID);

                ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, MinLengthAllowedRule.class, minLength, minLengthErrorMessageRID);
            }

            if (typedArray.hasValue(R.styleable.TextViewWithValidator_required)) {
                boolean required = typedArray.getBoolean(R.styleable.TextViewWithValidator_required, false);
                int requiredErrorMessageRID = typedArray.getResourceId(R.styleable.TextViewWithValidator_required_errorMessage, FieldRequiredRule.DEFAULT_ERROR_RESOURCE_ID);

                if (required) {
                    ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, FieldRequiredRule.class, required, requiredErrorMessageRID);
                }
            }

            if (typedArray.hasValue(R.styleable.TextViewWithValidator_fieldType)) {
                int fieldType = typedArray.getInt(R.styleable.TextViewWithValidator_fieldType, -1);

                if (fieldType >= 0) {
                    FieldTypeRule.FieldTypes fieldTypeEnum = FieldTypeRule.FieldTypes.getEnumFromValue(fieldType);
                    ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, FieldTypeRule.class, fieldTypeEnum, fieldTypeEnum.getFieldType().getErrorTextResourceID());
                }
            }

        } finally {
            typedArray.recycle();
        }
    }

    public void setMaxLengthAllowed(int maxLengthAllowed, Integer errorText) {
        ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, MaxLengthAllowedRule.class, maxLengthAllowed, errorText);
    }

    public void setMinLengthAllowed(int minLengthAllowed, Integer errorText) {
        ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, MinLengthAllowedRule.class, minLengthAllowed, errorText);
    }

    public void setRequired(boolean isRequired, Integer errorText) {
        ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, FieldRequiredRule.class, isRequired, errorText);
    }

    public void setFieldType(FieldTypeRule.FieldTypes fieldType, Integer errorText){
        ViewValidator.addViewAndItsValidationRules(this, triggeringViewId, FieldTypeRule.class, fieldType, fieldType.getFieldType().getErrorTextResourceID());
    }
}
