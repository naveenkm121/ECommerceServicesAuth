package com.ecommerce.ui.utils;



import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsonHelper {
	
	private static Boolean hasGson;
    private static Object gson;

    public static boolean hasGson() {
        if (hasGson == null) {
            try {
                Class.forName("com.google.gson.Gson");
                hasGson = true;
            } catch (Exception e) {
                hasGson = false;
            }
        }
        return hasGson;
    }

    public static Gson get() {
        if (gson == null) {
            gson = new Gson();
            GsonBuilder builder = new GsonBuilder();
            gson = builder.serializeNulls().create();

        }
        return (Gson) gson;
    }
    
    public static String toJson(Object obj) {
        try {
            return obj == null ? null : get().toJson(obj);
        } catch (Exception e) {
           System.out.println("GsonHelper :: Cannot convert object to JSON");
           e.printStackTrace();
            return null;
        }
    }
    
    public static <T> T fromJson(String str, Class<T> clazz) {
        return fromJson(str, (Type) clazz);
    }

    public static <T> T fromJson(String str, Type type) {
        try {
            return str == null ? null : (T) get().fromJson(str, type);
        } catch (Exception e) {
        	 System.out.println("GsonHelper :: Cannot convert object to JSON");
        	 e.printStackTrace();
            return null;
        }
    }
    
    public static <T> List<T> getList(String json, Class<T> clazz) {
	    Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
	    return new Gson().fromJson(json, typeOfT);
	}


}
