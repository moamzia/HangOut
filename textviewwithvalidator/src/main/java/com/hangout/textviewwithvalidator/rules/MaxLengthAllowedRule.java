package com.hangout.textviewwithvalidator.rules;

import android.widget.TextView;

import com.hangout.textviewwithvalidator.R;
import com.moamzia.validator.ValidationRule;

/**
 * Created by Amin on 27-Jan-16.
 */
public class MaxLengthAllowedRule extends ValidationRule<Integer> {
    public static final int DEFAULT_ERROR_RESOURCE_ID = R.string.error_field_required;

    public MaxLengthAllowedRule(Integer errorText, Integer ruleValue) {
        super(errorText, ruleValue);
    }

    @Override
    public boolean isValid(TextView textView) {
        return ruleValue.compareTo(textView.getText().toString().length()) > 0;
    }

    @Override
    public Integer getRuleValue() {
        return ruleValue;
    }

    @Override
    public void setRuleValue(Integer maxLengthAllowed) {
        this.ruleValue = maxLengthAllowed;
    }

}
