package com.github.wings27.easylogger;

import com.github.wings27.easylogger.utils.JsonUtil;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * Project easy-logger Created by wenqiushi at 2014/08/26 15:44.
 *
 * Please refer to https://github.com/wings27/easy-logger
 */
public final class Log {

    private static final ConcurrentMap<String, Logger> loggerCache = new ConcurrentHashMap<>();

    /**
     * Log a message at level TRACE according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the TRACE level.
     *
     * @param format    the format string
     * @param arguments an array of arguments
     */
    public static void trace(String format, Object... arguments) {
        Logger logger = loadLogger();
        if (logger.isTraceEnabled()) {
            logger.trace(format, objects2JsonStrings(arguments));
        }
    }

    /**
     * Log an exception (throwable) at level TRACE with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void trace(String msg, Throwable t) {
        loadLogger().trace(msg, t);
    }

    /**
     * Log a message at level DEBUG according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the DEBUG level.
     *
     * @param format    the format string
     * @param arguments an array of arguments
     */
    public static void debug(String format, Object... arguments) {
        Logger logger = loadLogger();
        if (logger.isDebugEnabled()) {
            logger.debug(format, objects2JsonStrings(arguments));
        }
    }

    /**
     * Log an exception (throwable) at level DEBUG with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void debug(String msg, Throwable t) {
        loadLogger().debug(msg, t);
    }

    /**
     * Log a message at level INFO according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the INFO level.
     *
     * @param format   the format string
     * @param arguments an array of arguments
     */
    public static void info(String format, Object... arguments) {
        Logger logger = loadLogger();
        if (logger.isInfoEnabled()) {
            logger.info(format, objects2JsonStrings(arguments));
        }
    }

    /**
     * Log an exception (throwable) at the INFO level with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void info(String msg, Throwable t) {
        loadLogger().info(msg, t);
    }

    /**
     * Log a message at level WARN according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the WARN level.
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public static void warn(String format, Object... argArray) {
        Logger logger = loadLogger();
        if (logger.isWarnEnabled()) {
            logger.warn(format, objects2JsonStrings(argArray));
        }
    }

    /**
     * Log an exception (throwable) at the WARN level with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void warn(String msg, Throwable t) {
        loadLogger().warn(msg, t);
    }

    /**
     * Log a message at level ERROR according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public static void error(String format, Object... argArray) {
        Logger logger = loadLogger();
        if (logger.isErrorEnabled()) {
            logger.error(format, objects2JsonStrings(argArray));
        }
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public static void error(String msg, Throwable t) {
        loadLogger().error(msg, t);
    }

    /**
     * Log a message at level TRACE according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the TRACE level.
     *
     * @param format   the format string
     * @param suppliers an array of suppliers
     */
    public static void trace(String format, Supplier<?>... suppliers) {
        Logger logger = loadLogger();
        if (logger.isTraceEnabled()) {
            Object[] arguments = new Object[suppliers.length];
            for (int i = 0; i < suppliers.length; i++) {
                arguments[i] = suppliers[i].get();
            }
            logger.trace(format, objects2JsonStrings(arguments));
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the DEBUG level.
     *
     * @param format   the format string
     * @param suppliers an array of suppliers
     */
    public static void debug(String format, Supplier<?>... suppliers) {
        Logger logger = loadLogger();
        if (logger.isDebugEnabled()) {
            Object[] arguments = new Object[suppliers.length];
            for (int i = 0; i < suppliers.length; i++) {
                arguments[i] = suppliers[i].get();
            }
            logger.debug(format, objects2JsonStrings(arguments));
        }
    }

    /**
     * Log a message at level INFO according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the INFO level.
     *
     * @param format   the format string
     * @param suppliers an array of suppliers
     */
    public static void info(String format, Supplier<?>... suppliers) {
        Logger logger = loadLogger();
        if (logger.isInfoEnabled()) {
            Object[] arguments = new Object[suppliers.length];
            for (int i = 0; i < suppliers.length; i++) {
                arguments[i] = suppliers[i].get();
            }
            logger.info(format, objects2JsonStrings(arguments));
        }
    }

    /**
     * Log a message at level WARN according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the WARN level.
     *
     * @param format   the format string
     * @param suppliers an array of suppliers
     */
    public static void warn(String format, Supplier<?>... suppliers) {
        Logger logger = loadLogger();
        if (logger.isWarnEnabled()) {
            Object[] arguments = new Object[suppliers.length];
            for (int i = 0; i < suppliers.length; i++) {
                arguments[i] = suppliers[i].get();
            }
            logger.warn(format, objects2JsonStrings(arguments));
        }
    }

    /**
     * Log a message at level ERROR according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
     *
     * @param format   the format string
     * @param suppliers an array of suppliers
     */
    public static void error(String format, Supplier<?>... suppliers) {
        Logger logger = loadLogger();
        if (logger.isErrorEnabled()) {
            Object[] arguments = new Object[suppliers.length];
            for (int i = 0; i < suppliers.length; i++) {
                arguments[i] = suppliers[i].get();
            }
            logger.error(format, objects2JsonStrings(arguments));
        }
    }

    private static String getInvokerClassName() {
        StackTraceElement stacks[] = Thread.currentThread().getStackTrace();

        for (int i = stacks.length - 1; i >= 0; i--) {
            if (!stacks[i].getClassName().equals(Log.class.getName())) {
                continue;
            }

            return i == stacks.length - 1 ? Log.class.getName() : stacks[i + 1].getClassName();
        }

        return Log.class.getName();
    }

    private static Logger loadLogger() {
        String className = getInvokerClassName();

        Logger logger = loggerCache.get(className);
        if (logger != null) {
            return logger;
        }

        logger = new EasyLog4jLogger(className, Log.class.getName());
        Logger oldLogger = loggerCache.putIfAbsent(className, logger);

        return oldLogger == null ? logger : oldLogger;
    }

    private static String[] objects2JsonStrings(Object... objects) {
        String[] strings = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            strings[i] = JsonUtil.serialize(objects[i]);
        }
        return strings;
    }

}
