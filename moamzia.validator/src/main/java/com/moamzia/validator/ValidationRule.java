package com.moamzia.validator;

import android.widget.TextView;

/**
 * To be extended by any custom rule.
 *
 * Created by Amin on 27-Jan-16.
 */
public abstract class ValidationRule<T> {
    protected int errorText;
    protected T ruleValue;

    public ValidationRule(Integer errorText, T ruleValue) {
        this.ruleValue = ruleValue;
        this.errorText = errorText;
    }

    public int getErrorText() {
        return errorText;
    }

    public void setErrorText(int errorText) {
        this.errorText = errorText;
    }

    public T getRuleValue(){
        return ruleValue;
    }

    public void setRuleValue(T ruleValue){
        this.ruleValue = ruleValue;
    }

    public abstract boolean isValid(TextView textView);
}
