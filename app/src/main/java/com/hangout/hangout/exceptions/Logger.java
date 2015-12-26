package com.hangout.hangout.exceptions;

import com.parse.ParseObject;

/**
 * Created by Amin on 25-Dec-15.
 */
public class Logger{

    private final Class<?> callerClass;

    private Logger(Class<?> callerClass) {
        this.callerClass = callerClass;
    }

    public void e(){

    }

    private class AudiLog extends ParseObject{

        public static final String CLASS_NAME = "ClassName";

        public void setClassName(String className){
            put(CLASS_NAME, className);
        }

        public String getClassName(){
            return getString(CLASS_NAME);
        }

//        public void setMethodName
    }
}
