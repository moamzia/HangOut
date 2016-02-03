package com.hangout.textviewwithvalidator.rules.field.type;

import android.widget.TextView;

import com.moamzia.validator.ValidationRule;

/**
 * Rule that defines the type of the text field, and based on the type(s) declared for the field, it will check its validity.
 * e.g. if Email FieldTypes is chosen, then we'll check if the text is an email.
 * <p/>
 * Created by Amin on 30-Jan-16.
 */
public class FieldTypeRule extends ValidationRule<FieldTypeRule.FieldTypes> {

    public enum FieldTypes {
        EMAIL(new EmailFieldType(), 0), USERNAME(new UsernameFieldType(), 1);

        private final FieldType fieldType;
        private final int value;

        FieldTypes(FieldType fieldType, int value) {
            this.fieldType = fieldType;
            this.value = value;
        }

        public FieldType getFieldType() {
            return fieldType;
        }

        public int getValue() {
            return value;
        }

        public static FieldTypes getEnumFromValue(int value) {
            for (FieldTypes fieldType : values()) {
                if (fieldType.getValue() == value) {
                    return fieldType;
                }
            }
            return null;
        }
    }

    public FieldTypeRule(Integer errorText, FieldTypes ruleValue) {
        super(errorText, ruleValue);
    }

    @Override
    public boolean isValid(TextView textView) {
        return ruleValue.getFieldType().isValid(textView.getText().toString());
    }

    @Override
    public int getErrorText() {
        return ruleValue.getFieldType().getErrorTextResourceID();
    }

}
