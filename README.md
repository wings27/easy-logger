## Easy-logger

Please contact [wenqs27@gmail.com](mailto://wenqs27@gmail.com) for support.

## Overview

Easy-logger is an lightweight and easy-to-use logging library based on slf4j-log4j.

It adds some useful features to slf4j-log4j. For example:

- **Json serialization** Arguments passed to logging system will be serialized to json format by default. You can bypass this process by explicitly invoking the `toString()` methods of the arguments.

- **Automatic logger initialization** No more annoying initialization statements! You can just invoke a static log method and we will create or find existing loggers for you.

- **Lambda support** Support java8's lambda expression as logging arguments to minimize unnecessary objects creation.

- **Totally compatible with log4j** In fact, this library is nothing more but a simple wrapper of slf4j-log4j. So you can still use log4j config files in your project.

## Usage

Log messages using static methods:

```java
public class LogTest {
    @Test
    public void testLog() {
    	Log.debug("Debug message.");
    	Log.info("Info message.");
    }
}
```

And the logger will be automatically initialized (if not initialized yet) based on the class where you log your messages, you don't need to bother creating the logger yourself.

So the code piece above is equivalent to the traditional way:

```java
// traditional way to log messages using slf4j.
public class LogTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

	@Test
	public void testLog() {
		LOGGER.debug("Debug message.");
		LOGGER.info("Info message.");
	}
}
```

Both ways have exactly the same behavior, and you can get this log message on the console:

> [09:56:48][DEBUG]com.github.wings27.easylogger.LogTest.testLog(LogTest.java:16) - Debug message.
> [09:56:48][INFO]com.github.wings27.easylogger.LogTest.testLog(LogTest.java:17) - Info message.

Assuming your `log4j.properties` is configured as:

```
log4j.rootLogger = DEBUG , stdout
log4j.appender.stdout = org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target = System.out
log4j.appender.stdout.layout = org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern = [%d{HH:mm:ss}][%p]%l - %m%n
```

**Note:**

The new way (using static method) is more concise: you don't need to add a private field in every class you want to enable logging.

But it also bears a slight performance loss as it has to determine the actual logger each time you log.

So choose them wisely. :)

