package com.github.wings27.easylogger;

import org.junit.Test;

/**
 * Project easy-logger
 * Created by wenqiushi on 2016-06-21 11:22.
 */
public class LogTest {

    @Test
    public void testLog() {
        Log.debug("debug with args : {}, {}", "arg0", "arg1");
        Log.info("info with args : {}, {}", "arg0", "arg1");
        Log.warn("warn with args : {}, {}", "arg0", "arg1");
        Log.error("error with args : {}, {}", "arg0", "arg1");
    }
}