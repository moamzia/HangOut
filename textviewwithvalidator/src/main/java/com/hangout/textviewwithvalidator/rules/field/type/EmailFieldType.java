package com.hangout.textviewwithvalidator.rules.field.type;

import android.text.TextUtils;
import android.util.Patterns;

import com.hangout.textviewwithvalidator.R;

/**
 * Created by Amin on 02-Feb-16.
 */
public class EmailFieldType implements FieldType {
    @Override
    public boolean isValid(String input) {
        return TextUtils.isEmpty(input) ? false : Patterns.EMAIL_ADDRESS.matcher(input).matches();
    }

    @Override
    public int getErrorTextResourceID() {
        return R.string.error_not_valid_email;
    }

}
