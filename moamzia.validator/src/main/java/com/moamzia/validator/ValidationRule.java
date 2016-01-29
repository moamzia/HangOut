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
        this.errorText = errorText;
    }

    public int getErrorText() {
        return errorText;
    }

    public void setErrorText(int errorText) {
        this.errorText = errorText;
    }

    public abstract boolean isValid(TextView textView);

    public abstract T getRuleValue();

    public abstract void setRuleValue(T t);
}
