package com.hangout.hangout;

import android.app.Application;

import com.hangout.hangout.exceptions.ExceptionHandler;
import com.hangout.hangout.exceptions.entity.Log;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Initializes the SDKs
 *
 * Created by Amin on 15-Dec-15.
 */
public class MyApplication extends Application {

    public static final int FACEBOOK_LOGIN_REQUEST_CODE = 10;

    @Override
    public void onCreate(){
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler.INSTANCE);

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Log.class);
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseACL.setDefaultACL(new ParseACL(), true);

//        FacebookSdk.sdkInitialize(getApplicationContext()); probably not needed
        ParseFacebookUtils.initialize(getApplicationContext(), FACEBOOK_LOGIN_REQUEST_CODE);
    }

}
