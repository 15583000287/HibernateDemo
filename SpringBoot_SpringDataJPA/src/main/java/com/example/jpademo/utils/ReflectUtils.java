package com.example.jpademo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReflectUtils {

    /**
     * 对象为空的字段返回字段名
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String[] getIsNullFields(T t){
        Class cls = t.getClass();
        Set<String> isNullFieldSet = new HashSet<>();
        try {
            Field[] fields = cls.getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                if(value == null) {
                    isNullFieldSet.add(field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return isNullFieldSet.toArray(new String[isNullFieldSet.size()]);
    }

    public static void main(String[] args) {
        List<String> isNullFieldList = new ArrayList<>();
        isNullFieldList.add("hello!");
        System.out.println(isNullFieldList.toArray());
    }
}
















