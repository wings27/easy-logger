package com.github.wings27.easylogger;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import java.io.Serializable;

/**
 * Project easy-logger Created by wenqiushi at 2014/08/27 11:58.
 */

/**
 * 基于slf4j API实现的slf4j-log4j Adapter类。
 * 该实现可以指定logger包装器的全称类名。（full qualified class name）。
 * 全称类名通常作为遍历函数调用栈的结束标识，从而使logging类获得打印日志信息的上下文。
 */
public class EasyLog4jLogger extends MarkerIgnoringBase
        implements LocationAwareLogger, Serializable {

    protected String name;

    protected String callerClassName;

    // Does the log4j version in use recognize the TRACE level?
    // The trace level was introduced in log4j 1.2.12.
    protected boolean traceCapable;

    protected transient org.apache.log4j.Logger logger;

    /**
     * 由指定的loggerName构造类的实例。loggerName的详细信息请参阅log4j manual.
     *
     * @param loggerName 指定的loggerName
     */
    public EasyLog4jLogger(String loggerName) {
        this(loggerName, EasyLog4jLogger.class.getName());
    }

    /**
     * 由指定的loggerName和调用者的全称类名构造类的实例。
     * 调用isTraceCapable()应确保在初始化logger之后进行，否则可能产生异常。
     *
     * @param loggerName      指定的loggerName
     * @param callerClassName 调用者的全称类名
     */
    public EasyLog4jLogger(String loggerName, String callerClassName) {

        this.name = loggerName;
        this.logger = getLoggerByName(loggerName);

        this.callerClassName = callerClassName;
        this.traceCapable = isTraceCapable();  // 调用isTraceCapable()要在logger初始化之后
    }

    /**
     * 返回log4j是否支持名为TRACE的类目(category). 这一类目是从log4j 1.2.12版本开始支持的。
     * 注意，调用isTraceCapable()应确保在初始化logger之后进行，否则可能产生异常。
     *
     * @return 返回表示log4j是否支持名为TRACE的category的布尔值。
     */
    private boolean isTraceCapable() {
        try {
            logger.isTraceEnabled();
            return true;
        } catch (NoSuchMethodError e) {
            return false;
        }
    }

    private Logger getLoggerByName(String name) {
        Logger log4jLogger;
        if (name.equalsIgnoreCase(org.slf4j.Logger.ROOT_LOGGER_NAME)) {
            log4jLogger = LogManager.getRootLogger();
        } else {
            log4jLogger = LogManager.getLogger(name);
        }
        return log4jLogger;
    }

    /**
     * Printing method with support for location information.
     *
     * @param marker     The marker to be used for this event, may be null.
     * @param callerFQCN The fully qualified class name of the <b>logger instance</b>, typically the logger
     *                   class, logger bridge or a logger wrapper.
     * @param level      One of the level integers defined in this interface
     * @param message    The message for the log event
     * @param t          Throwable associated with the log event, may be null.
     */
    @Override
    public void log(Marker marker, String callerFQCN, int level, String message,
                    Object[] argArray, Throwable t) {
        Level log4jLevel;
        switch (level) {
            case LocationAwareLogger.TRACE_INT:
                log4jLevel = traceCapable ? Level.TRACE : Level.DEBUG;
                break;
            case LocationAwareLogger.DEBUG_INT:
                log4jLevel = Level.DEBUG;
                break;
            case LocationAwareLogger.INFO_INT:
                log4jLevel = Level.INFO;
                break;
            case LocationAwareLogger.WARN_INT:
                log4jLevel = Level.WARN;
                break;
            case LocationAwareLogger.ERROR_INT:
                log4jLevel = Level.ERROR;
                break;
            default:
                throw new IllegalStateException("Level number " + level
                        + " is not recognized.");
        }
        logger.log(callerFQCN, log4jLevel, message, t);

    }

    /**
     * Is the logger instance enabled for the TRACE level?
     *
     * @return True if this Logger is enabled for the TRACE level, false otherwise.
     * @since 1.4
     */
    @Override
    public boolean isTraceEnabled() {
        if (traceCapable) {
            return logger.isTraceEnabled();
        } else {
            return logger.isDebugEnabled();
        }
    }

    /**
     * Log a message object at level TRACE.
     *
     * @param msg - the message object to be logged
     */
    public void trace(String msg) {
        logger.log(getCallerClassName(), traceCapable ? Level.TRACE : Level.DEBUG, msg,
                null);
    }

    /**
     * Log a message at level TRACE according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for level TRACE.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void trace(String format, Object arg) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(getCallerClassName(), traceCapable ? Level.TRACE : Level.DEBUG,
                    ft.getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log a message at level TRACE according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the TRACE level
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void trace(String format, Object arg1, Object arg2) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(getCallerClassName(), traceCapable ? Level.TRACE : Level.DEBUG, ft
                    .getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log a message at level TRACE according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the TRACE level.
     *
     * @param format    the format string
     * @param arguments an array of arguments
     */
    public void trace(String format, Object... arguments) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.log(getCallerClassName(), traceCapable ? Level.TRACE : Level.DEBUG, ft
                    .getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at level TRACE with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void trace(String msg, Throwable t) {
        logger.log(getCallerClassName(), traceCapable ? Level.TRACE : Level.DEBUG, msg,
                t);
    }

    /**
     * Is this logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for level DEBUG, false otherwise.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Log a message object at level DEBUG.
     *
     * @param msg - the message object to be logged
     */
    public void debug(String msg) {
        logger.log(getCallerClassName(), Level.DEBUG, msg, null);
    }

    /**
     * Log a message at level DEBUG according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for level DEBUG.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void debug(String format, Object arg) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(getCallerClassName(), Level.DEBUG, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the DEBUG level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void debug(String format, Object arg1, Object arg2) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(getCallerClassName(), Level.DEBUG, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the DEBUG level.
     *
     * @param format    the format string
     * @param arguments an array of arguments
     */
    public void debug(String format, Object... arguments) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.log(getCallerClassName(), Level.DEBUG, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at level DEBUG with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void debug(String msg, Throwable t) {
        logger.log(getCallerClassName(), Level.DEBUG, msg, t);
    }

    /**
     * Is this logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level, false otherwise.
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Log a message object at the INFO level.
     *
     * @param msg - the message object to be logged
     */
    public void info(String msg) {
        logger.log(getCallerClassName(), Level.INFO, msg, null);
    }

    /**
     * Log a message at level INFO according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for the INFO level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void info(String format, Object arg) {
        if (logger.isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(getCallerClassName(), Level.INFO, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log a message at the INFO level according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the INFO level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void info(String format, Object arg1, Object arg2) {
        if (logger.isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(getCallerClassName(), Level.INFO, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log a message at level INFO according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the INFO level.
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void info(String format, Object... argArray) {
        if (logger.isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log(getCallerClassName(), Level.INFO, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at the INFO level with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void info(String msg, Throwable t) {
        logger.log(getCallerClassName(), Level.INFO, msg, t);
    }

    /**
     * Is this logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level, false otherwise.
     */
    public boolean isWarnEnabled() {
        return logger.isEnabledFor(Level.WARN);
    }

    /**
     * Log a message object at the WARN level.
     *
     * @param msg - the message object to be logged
     */
    public void warn(String msg) {
        logger.log(getCallerClassName(), Level.WARN, msg, null);
    }

    /**
     * Log a message at the WARN level according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for the WARN level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void warn(String format, Object arg) {
        if (logger.isEnabledFor(Level.WARN)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(getCallerClassName(), Level.WARN, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log a message at the WARN level according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the WARN level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void warn(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.WARN)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(getCallerClassName(), Level.WARN, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log a message at level WARN according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the WARN level.
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void warn(String format, Object... argArray) {
        if (logger.isEnabledFor(Level.WARN)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log(getCallerClassName(), Level.WARN, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at the WARN level with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void warn(String msg, Throwable t) {
        logger.log(getCallerClassName(), Level.WARN, msg, t);
    }

    /**
     * Is this logger instance enabled for level ERROR?
     *
     * @return True if this Logger is enabled for level ERROR, false otherwise.
     */
    public boolean isErrorEnabled() {
        return logger.isEnabledFor(Level.ERROR);
    }

    /**
     * Log a message object at the ERROR level.
     *
     * @param msg - the message object to be logged
     */
    public void error(String msg) {
        logger.log(getCallerClassName(), Level.ERROR, msg, null);
    }

    /**
     * Log a message at the ERROR level according to the specified format and argument.
     * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void error(String format, Object arg) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(getCallerClassName(), Level.ERROR, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log a message at the ERROR level according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void error(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(getCallerClassName(), Level.ERROR, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log a message at level ERROR according to the specified format and arguments.
     * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void error(String format, Object... argArray) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log(getCallerClassName(), Level.ERROR, ft.getMessage(),
                    ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void error(String msg, Throwable t) {
        logger.log(getCallerClassName(), Level.ERROR, msg, t);
    }

    /**
     * Return the name of this <code>Logger</code> instance.
     *
     * @return name of this logger instance
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Return the name of caller class.
     *
     * @return name of caller class
     */
    public String getCallerClassName() {
        return this.callerClassName;
    }

}
