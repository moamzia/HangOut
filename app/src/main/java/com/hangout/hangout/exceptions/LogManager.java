package com.hangout.hangout.exceptions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Amin on 25-Dec-15.
 */
public class LogManager {

    private static final Map<Class<?>, Logger> map = new ConcurrentHashMap<Class<?>, Logger>();

    public static <T> Logger prepareToLog(Class<T> callerClass) {
        map.get(callerClass);
        return null;
    }
}
