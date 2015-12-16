package com.hangout.hangout;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Amin on 15-Dec-15.
 */
public class MyApplication extends Application {

    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
    }
}
