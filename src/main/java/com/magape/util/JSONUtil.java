package com.magape.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.magape.model.domain.NFT;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    public static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = gsonBuilder.create();
    }

    public static String toJSON(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJSON(String str, Class<T> classOfT) {
        return gson.fromJson(str, classOfT);
    }

    public static <T> List<T> fromJSONArray(String json,Class<T> tClass) {
        Type listType = TypeToken.getParameterized(ArrayList.class, tClass).getType();
        System.out.println(json);
        return gson.fromJson(json, listType);
    }
}
