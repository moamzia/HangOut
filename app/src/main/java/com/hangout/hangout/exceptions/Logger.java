package com.hangout.hangout.exceptions;

import com.parse.ParseObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private static final Map<Class<?>, Logger> map = new ConcurrentHashMap<Class<?>, Logger>();

    public static <T> Logger prepareToLog(Class<T> callerClass) {
        map.get(callerClass);
        return null;
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
