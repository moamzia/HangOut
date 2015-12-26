package com.hangout.hangout.exceptions;

import android.util.Log;

/**
 * Used Enum to have a singleton class for better performance. Since, I'm not sure if garbage collector will
 *
 * Created by Amin on 23-Dec-15.
 */
public enum ExceptionHandler implements Thread.UncaughtExceptionHandler{

    INSTANCE;

    private static final String TAG = "ExceptionHandler";
    private Thread.UncaughtExceptionHandler defaultUEH;

    private ExceptionHandler(){
        defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e(TAG, ex.getMessage(), ex);
        Log.e(TAG, "thread name = " + thread.getName());

        defaultUEH.uncaughtException(thread, ex);
    }

}
