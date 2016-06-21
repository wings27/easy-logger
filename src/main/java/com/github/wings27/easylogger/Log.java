package com.github.wings27.easylogger;

import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Project easy-logger Created by wenqiushi at 2014/08/26 15:44.
 */

/**
 * Logging辅助类，通过slf4j API实现。
 * Example： Log.debug("debug message");
 * 调用栈等上下文信息会自动加入到相应参数中。
 */
public final class Log {

    private static final ConcurrentMap<String, Logger> loggerCache = new ConcurrentHashMap<String, Logger>();

    /**
     * Log a message object at level TRACE.
     *
     * @param message - the message object to be logged
     */
    public static void trace(String message) {
        loadLogger().trace(message);
    }

    /**
     * Log a message at level TRACE according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for level TRACE.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void trace(String format, Object arg) {
        loadLogger().trace(format, arg);
    }

    /**
     * Log a message at level TRACE according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the TRACE level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void trace(String format, Object arg1, Object arg2) {
        loadLogger().trace(format, arg1, arg2);
    }

    /**
     * Log a message at level TRACE according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the TRACE level.
     *
     * @param format    the format string
     * @param arguments an array of arguments
     */
    public static void trace(String format, Object... arguments) {
        loadLogger().trace(format, arguments);
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
     * Log a message object at level DEBUG.
     *
     * @param message - the message object to be logged
     */
    public static void debug(String message) {
        loadLogger().debug(message);
    }

    /**
     * Log a message at level DEBUG according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for level DEBUG.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void debug(String format, Object arg) {
        loadLogger().debug(format, arg);
    }

    /**
     * Log a message at level DEBUG according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the DEBUG level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void debug(String format, Object arg1, Object arg2) {
        loadLogger().debug(format, arg1, arg2);
    }

    /**
     * Log a message at level DEBUG according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the DEBUG level.
     *
     * @param format    the format string
     * @param arguments an array of arguments
     */
    public static void debug(String format, Object... arguments) {
        loadLogger().debug(format, arguments);
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
     * Log a message object at the INFO level.
     *
     * @param message - the message object to be logged
     */
    public static void info(String message) {
        loadLogger().info(message);
    }

    /**
     * Log a message at level INFO according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for the INFO level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void info(String format, Object arg) {
        loadLogger().info(format, arg);
    }

    /**
     * Log a message at the INFO level according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the INFO level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void info(String format, Object arg1, Object arg2) {
        loadLogger().info(format, arg1, arg2);
    }

    /**
     * Log a message at level INFO according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the INFO level.
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public static void info(String format, Object... argArray) {
        loadLogger().info(format, argArray);
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
     * Log a message object at the WARN level.
     *
     * @param message - the message object to be logged
     */
    public static void warn(String message) {
        loadLogger().warn(message);
    }

    /**
     * Log a message at the WARN level according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for the WARN level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void warn(String format, Object arg) {
        loadLogger().warn(format, arg);
    }

    /**
     * Log a message at the WARN level according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the WARN level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void warn(String format, Object arg1, Object arg2) {
        loadLogger().warn(format, arg1, arg2);
    }

    /**
     * Log a message at level WARN according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the WARN level.
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public static void warn(String format, Object... argArray) {
        loadLogger().warn(format, argArray);
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
     * Log a message object at the ERROR level.
     *
     * @param message - the message object to be logged
     */
    public static void error(String message) {
        loadLogger().error(message);
    }

    /**
     * Log a message at the ERROR level according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public static void error(String format, Object arg) {
        loadLogger().error(format, arg);
    }

    /**
     * Log a message at the ERROR level according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public static void error(String format, Object arg1, Object arg2) {
        loadLogger().error(format, arg1, arg2);
    }

    /**
     * Log a message at level ERROR according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public static void error(String format, Object... argArray) {
        loadLogger().error(format, argArray);
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
     * Is the logger instance enabled for the TRACE level?
     *
     * @return True if this Logger is enabled for the TRACE level, false otherwise.
     */
    public static boolean isTraceEnabled() {
        return loadLogger().isTraceEnabled();
    }

    /**
     * Is this logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for level DEBUG, false otherwise.
     */
    public static boolean isDebugEnabled() {
        return loadLogger().isDebugEnabled();
    }

    /**
     * Is this logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level, false otherwise.
     */
    public static boolean isInfoEnabled() {
        return loadLogger().isInfoEnabled();
    }

    /**
     * Is this logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level, false otherwise.
     */
    public static boolean isWarnEnabled() {
        return loadLogger().isWarnEnabled();
    }

    /**
     * Is this logger instance enabled for level ERROR?
     *
     * @return True if this Logger is enabled for level ERROR, false otherwise.
     */
    public static boolean isErrorEnabled() {
        return loadLogger().isErrorEnabled();
    }

    private static String getInvokerClassName() {
        StackTraceElement stacks[] = Thread.currentThread().getStackTrace();

        // 自底向上查找目标类名。
        // 之所以不按出栈顺序查找，是因为logging类可能在函数调用栈中多次出现。
        for (int i = stacks.length - 1; i >= 0; i--) {
            if (!stacks[i].getClassName().equals(Log.class.getName())) {
                continue;
            }

            // 处理数组下标溢出的情况。
            // 下标溢出说明当前类在函数调用栈栈底，也即程序入口点在这个类中，因此返回自身类名即可。
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

        // 放入新的logger到原cache中
        logger = new EasyLog4jLogger(className, Log.class.getName());
        Logger oldLogger = loggerCache.putIfAbsent(className, logger);

        return oldLogger == null ? logger : oldLogger;
    }
}
