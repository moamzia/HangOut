package com.moamzia.validator;

import android.widget.TextView;

/**
 * Created by Amin on 28-Jan-16.
 */
public class MyCustomRule extends ValidationRule<Integer>{
    public MyCustomRule(Integer errorText, Integer ruleValue) {
        super(errorText, ruleValue);
    }

    @Override
    public boolean isValid(TextView textView) {
        return textView.getText().length() > ruleValue;
    }

    @Override
    public Integer getRuleValue() {
        return ruleValue;
    }

    @Override
    public void setRuleValue(Integer value) {
        this.ruleValue = value;
    }
}
