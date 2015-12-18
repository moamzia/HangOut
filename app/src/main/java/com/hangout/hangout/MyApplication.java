package com.hangout.hangout;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.parse.Parse;

/**
 * Initializes the SDKs
 *
 * Created by Amin on 15-Dec-15.
 */
public class MyApplication extends Application {

    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
