package com.hangout.textviewwithvalidator.rules.field.type;

import com.moamzia.validator.ValidationRule;

/**
 * Created by Amin on 02-Feb-16.
 */
public interface FieldType {

    boolean isValid(String input);

    int getErrorTextResourceID();
}
