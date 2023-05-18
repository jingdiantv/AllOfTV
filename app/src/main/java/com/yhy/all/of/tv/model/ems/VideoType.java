package com.yhy.all.of.tv.model.ems;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/**
 * 视频类型
 * <p>
 * Created on 2023-01-20 03:15
 *
 * @author 颜洪毅
 * @version 1.0.0
 * @since 1.0.0
 */
public enum VideoType {
    /**
     * 电影
     */
    FILM(100, "电影"),

    /**
     * 剧集
     */
    EPISODE(200, "电视剧"),

    /**
     * 未知，不指定类型
     */
    UNKNOWN(10000, "未知"),
    ;

    public final Integer code;
    public final String name;

    VideoType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static VideoType parse(Integer code) {
        return Arrays.stream(VideoType.values()).filter(it -> Objects.equals(code, it.code)).findFirst().orElse(UNKNOWN);
    }

    public static class TypeAdapter implements JsonSerializer<VideoType>, JsonDeserializer<VideoType> {

        @Override
        public VideoType deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return parse(json.getAsInt());
        }

        @Override
        public JsonElement serialize(VideoType src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.code);
        }
    }
}
