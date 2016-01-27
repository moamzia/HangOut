package com.hangout.hangout.exceptions;

import com.hangout.hangout.exceptions.entity.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Logger class is to be called from anywhere in the application and has to be used instead of the
 * {@link android.util.Log}
 * <p/>
 * Created by Amin on 25-Dec-15.
 */
public class Logger {

    private static final Map<String, Logger> LOGGER_MAP = new ConcurrentHashMap<>();

    private final Class<?> callerClass;
    private final boolean logEverything;

    private Logger(Class<?> callerClass, boolean logEverything) {
        this.callerClass = callerClass;
        this.logEverything = logEverything;
    }

    /**
     * Logs the error in the logCat</br>
     * Then syncs with Parse server.
     *
     * @param msg
     * @param t
     */
    public void error(String msg, Throwable t) {
        android.util.Log.e(prepareTag(LogLevel.ERROR), msg, t);

        syncLog(msg, LogLevel.ERROR);
    }

    /**
     * Logs the debug level in the logCat</br>
     * Then if logEverything is true, it will sync with Parse server.
     *
     * @param msg
     */
    public void debug(String msg) {
        debug(msg, null);
    }

    /**
     * Logs the debug level in the logCat</br>
     * Then if logEverything is true, it will sync with Parse server.
     *
     * @param msg
     * @param t
     */
    public void debug(String msg, Throwable t) {
        android.util.Log.d(prepareTag(LogLevel.DEBUG), msg, t);

        if (logEverything) {
            syncLog(msg, LogLevel.DEBUG);
        }
    }

    /**
     * Logs the info level in the logCat</br>
     * Then if logEverything is true, it will sync with Parse server.
     *
     * @param msg
     */
    public void info(String msg) {
        info(msg, null);
    }

    /**
     * Logs the info level in the logCat</br>
     * Then if logEverything is true, it will sync with Parse server.
     *
     * @param msg
     * @param t
     */
    public void info(String msg, Throwable t) {
        android.util.Log.i(prepareTag(LogLevel.INFO), msg, t);

        if (logEverything) {
            syncLog(msg, LogLevel.INFO);
        }
    }

    /**
     * Logs the verbose level in the logCat</br>
     * Then if logEverything is true, it will sync with Parse server.
     *
     * @param msg
     */
    public void verbose(String msg) {
        verbose(msg, null);
    }

    /**
     * Logs the verbose level in the logCat</br>
     * Then if logEverything is true, it will sync with Parse server.
     *
     * @param msg
     * @param t
     */
    public void verbose(String msg, Throwable t) {
        android.util.Log.v(prepareTag(LogLevel.VERBOSE), msg, t);

        if (logEverything) {
            syncLog(msg, LogLevel.VERBOSE);
        }
    }

    /**
     * Logs the warn level in the logCat</br>
     * Then if logEverything is true, it will sync with Parse server.
     *
     * @param msg
     */
    public void warn(String msg) {
        warn(msg, null);
    }

    /**
     * Logs the warn level in the logCat</br>
     * Then if logEverything is true, it will sync with Parse server.
     *
     * @param msg
     * @param t
     */
    public void warn(String msg, Throwable t) {
        android.util.Log.w(prepareTag(LogLevel.WARN), msg, t);

        if (logEverything) {
            syncLog(msg, LogLevel.WARN);
        }
    }

    /**
     * Prepares the TAG to be logged in LogCat and synced with server.
     *
     * @param errorLevel
     * @return
     */
    private String prepareTag(LogLevel errorLevel) {
        return errorLevel + ": " + callerClass.getName();
    }

    /**
     * Logs the message and level in Parse.com when the connection is there.
     * Otherwise it will save it locally on the device.
     *
     * @param msg
     * @param logLevel
     */
    private void syncLog(String msg, LogLevel logLevel) {
        Log log = new Log();
        log.setClassName(callerClass.getName());
        log.setMessage(msg);
        log.setLogLevel(logLevel.name());

        log.saveEventually();
    }

    public static <T> Logger getErrorLogger(Class<T> callerClass) {
        return getLogger(callerClass, false);
    }

    /**
     * Factory method that initiates the logger and has the possibility to decide whether you want
     * to log all LOG LEVELs or not.
     *
     * @param callerClass
     * @param logEverything
     * @param <T>
     * @return
     */
    public static <T> Logger getLogger(Class<T> callerClass, boolean logEverything) {
        Logger logger = LOGGER_MAP.get(callerClass.getName());

        if (logger == null) {
            logger = new Logger(callerClass, logEverything);
            LOGGER_MAP.put(callerClass.getName(), logger);
        }

        return logger;
    }

    private enum LogLevel {
        DEBUG, ERROR, VERBOSE, WARN, INFO;
    }
}
