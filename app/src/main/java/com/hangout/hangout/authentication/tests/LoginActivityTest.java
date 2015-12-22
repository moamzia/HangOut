package com.hangout.hangout.authentication.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.hangout.hangout.authentication.LoginActivity;

/**
 * Created by Amin on 20-Dec-15.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity>{
    private LoginActivity loginActivity;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        loginActivity = getActivity();
    }

    public void testPreconditions(){
        assertNotNull("loginActivity is null",loginActivity);
    }
}
