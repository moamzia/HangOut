package com.hangout.hangout.exceptions.entity;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Amin on 25-Dec-15.
 */
@ParseClassName("Log")
public class Log extends ParseObject {

    private static final String CLASS_NAME = "ClassName";
    private static final String MESSAGE = "Message";
    private static final String LOG_LEVEL = "LogLevel";

    public void setClassName(String className) {
        put(CLASS_NAME, className);
    }

    public String getClassName() {
        return getString(CLASS_NAME);
    }

    public void setMessage(String msg) {
        put(MESSAGE, msg);
    }

    public String getMessage() {
        return getString(MESSAGE);
    }

    public void setLogLevel(String logLevel) {
        put(LOG_LEVEL, logLevel);
    }

    public String getLogLevel() {
        return getString(LOG_LEVEL);
    }
}
