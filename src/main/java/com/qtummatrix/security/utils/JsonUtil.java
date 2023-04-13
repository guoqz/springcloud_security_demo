package com.qtummatrix.security.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JsonUtil {

    public static <T> String JsonSerializer(T t, int resultCode, String resultMessage) {
        return "{ \"resultCode\":" + resultCode + ", \"resultMessage\":\"" + resultMessage + "\", \"data\":"
                + (t == null ? "null" : JsonUtil.<T>JsonSerializer(t)) + "}";
    }

    public static <T> String JsonSerializerSuccess(T t) {
        return "{ \"resultCode\":200, \"resultMessage\":\"OK\", \"data\":" + (t == null ? "{}" : JsonUtil.<T>JsonSerializer(t)) + "}";
    }

    public static <T> String JsonSerializer(T t) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        JsonGenerator gen = null;
        try {
            gen = new JsonFactory().createGenerator(sw);
            mapper.writeValue(gen, t);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gen != null) {
                try {
                    gen.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sw.toString();
    }


    public static <T> T JsonDeserialize(String jsonStr, Class<T> clazz) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String GetJsonForJsonList(String JsonStr, int resultCode, String resultMessage) {
        if ((JsonStr + "").equals("")) {
            JsonStr = "{}";
        }
        return "{ \"resultCode\":" + resultCode + ", \"resultMessage\":\"" + resultMessage + "\", \"data\":" + JsonStr + "}";
    }

}
