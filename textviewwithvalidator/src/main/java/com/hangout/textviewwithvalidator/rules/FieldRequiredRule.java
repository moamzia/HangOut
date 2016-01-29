package com.hangout.textviewwithvalidator.rules;

import android.text.TextUtils;
import android.widget.TextView;

import com.hangout.textviewwithvalidator.R;
import com.moamzia.validator.ValidationRule;

/**
 * Created by Amin on 28-Jan-16.
 */
public class FieldRequiredRule extends ValidationRule<Boolean> {
    public static final int DEFAULT_ERROR_RESOURCE_ID = R.string.error_field_required;

    public FieldRequiredRule(Integer errorText, Boolean ruleValue) {
        super(errorText, ruleValue);
    }

    @Override
    public boolean isValid(TextView textView) {
        return !TextUtils.isEmpty(textView.getText().toString());
    }

    @Override
    public Boolean getRuleValue() {
        return ruleValue;
    }

    @Override
    public void setRuleValue(Boolean ruleValue) {
        this.ruleValue = ruleValue;
    }
}
