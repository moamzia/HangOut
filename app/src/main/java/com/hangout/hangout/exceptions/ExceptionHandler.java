package com.hangout.hangout.exceptions;

import android.util.Log;

/**
 * Used Enum to have a singleton class for better performance. Since, I'm not sure if garbage collector will
 *
 * Created by Amin on 23-Dec-15.
 */
public enum ExceptionHandler implements Thread.UncaughtExceptionHandler{

    INSTANCE;

    private Thread.UncaughtExceptionHandler defaultUEH;
    private static final Logger LOG = Logger.getErrorLogger(ExceptionHandler.class);

    ExceptionHandler(){
        defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LOG.error("Something unexpected happened: " + ex.getMessage(), ex);

        defaultUEH.uncaughtException(thread, ex);
    }

}
