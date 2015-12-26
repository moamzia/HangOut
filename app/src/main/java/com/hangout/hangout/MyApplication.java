package com.hangout.hangout;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.hangout.hangout.exceptions.ExceptionHandler;
import com.hangout.hangout.exceptions.Log;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Initializes the SDKs
 *
 * Created by Amin on 15-Dec-15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler.INSTANCE);

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Log.class);
        Parse.initialize(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
    }

}
