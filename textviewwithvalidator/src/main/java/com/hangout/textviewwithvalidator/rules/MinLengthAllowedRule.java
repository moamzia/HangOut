package com.hangout.textviewwithvalidator.rules;

import android.widget.TextView;

import com.hangout.textviewwithvalidator.R;
import com.moamzia.validator.ValidationRule;

/**
 * Created by Amin on 30-Jan-16.
 */
public class MinLengthAllowedRule extends ValidationRule<Integer> {
    public static final int DEFAULT_ERROR_RESOURCE_ID = R.string.error_too_short_text;

    public MinLengthAllowedRule(Integer errorText, Integer ruleValue) {
        super(errorText, ruleValue);
    }

    @Override
    public boolean isValid(TextView textView) {
        return ruleValue.compareTo(textView.getText().toString().length()) < 0;
    }
}
