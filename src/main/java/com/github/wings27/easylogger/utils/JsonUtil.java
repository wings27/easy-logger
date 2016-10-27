package com.github.wings27.easylogger.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.wings27.easylogger.Log;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS;
import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES;
import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES;

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
            objMapper.configure(ALLOW_COMMENTS, true);
            objMapper.configure(ALLOW_UNQUOTED_FIELD_NAMES, true);
            objMapper.configure(ALLOW_SINGLE_QUOTES, true);
            objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }
    }

}
