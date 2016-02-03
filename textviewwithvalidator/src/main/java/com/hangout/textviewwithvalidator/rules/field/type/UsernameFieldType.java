package com.hangout.textviewwithvalidator.rules.field.type;

import android.text.TextUtils;

import com.hangout.textviewwithvalidator.R;

import java.util.regex.Pattern;

/**
 * Created by Amin on 03-Feb-16.
 */
public class UsernameFieldType implements FieldType {

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]$";

    @Override
    public boolean isValid(String input) {
        return !TextUtils.isEmpty(input) && Pattern.compile(USERNAME_PATTERN).matcher(input).matches();
    }

    @Override
    public int getErrorTextResourceID() {
        return R.string.error_invalid_username;
    }
}
