package com.github.wings27.easylogger.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.wings27.easylogger.Log;

/**
 * Project easy-logger
 * Created by wenqiushi on 2016-06-22 18:04.
 */
public class JsonUtil {

    public static String serialize(Object obj) {
        try {
            return LazyInit.objMapper.writeValueAsString(obj);
        } catch (Exception e) {
            Log.debug("", e);
            return "{}";
        }
    }

    private static final class LazyInit {
        private final static ObjectMapper objMapper = new ObjectMapper();

        static {
            objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
        }
    }

}
