package com.hangout.hangout;

import android.text.TextUtils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void testCursor() {
        assertTrue(isValidEmail("valid@email.com"));
    }

    public final static boolean isValidEmail(CharSequence target) {
        return TextUtils.isEmpty(target) ? false : android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}