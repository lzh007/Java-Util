package com.lzh.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 〈json转换工具类〉
 *
 * @author lzh
 * @create 2019/11/6
 *
 */
public class JsonUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static Gson gson;

    private static Type mapType = new TypeToken<Map<String, String>>() {}.getType();

    static {
        try {
            gson = new GsonBuilder()
            .registerTypeAdapter(java.util.Date.class, new DateGsonDeserializer())
            .registerTypeAdapter(java.sql.Date.class, new DateGsonDeserializer())
            .registerTypeAdapter(java.sql.Timestamp.class, new DateGsonDeserializer())
            .create();

            ParserConfig.getGlobalInstance().putDeserializer(java.util.Date.class, new DateDeserializer());
            ParserConfig.getGlobalInstance().putDeserializer(java.sql.Date.class, new DateDeserializer());
            ParserConfig.getGlobalInstance().putDeserializer(java.sql.Timestamp.class, new DateDeserializer());
        } catch (Throwable e) {
            logger.error("error catched when int JsonUtils, check json version", e);
        }
    }

    public static Gson getGson(){
        return gson;
    }

    /**
     * 对象序列化Json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 对象序列化Json字符串
     *
     * @param obj
     * @param features
     * @return
     */
    public static String toJson(Object obj, SerializerFeature... features) {
        return JSON.toJSONString(obj, features);
    }

    /**
     * 对象序列化Json字符串
     * @param obj
     * @param dateFormat
     * @param features
     * @return
     */
    public static String toJson(Object obj, String dateFormat, SerializerFeature... features){
        return JSON.toJSONStringWithDateFormat(obj, dateFormat, features);
    }

    /**
     * Json字符串反序列化对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * Json字符串反序列化成List
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz){
        return JSON.parseArray(json, clazz);
    }

    /**
     * 把非空JSONString转为HashMap对象集合
     *
     * @param <U>
     * @param <T>
     *
     * @param str
     *            JSONString
     * @return List
     */
    public static <T, U> Map<T, U> getMap(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        try {
            return gson.fromJson(str, mapType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
