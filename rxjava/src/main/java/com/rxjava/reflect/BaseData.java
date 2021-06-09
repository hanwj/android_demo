package com.rxjava.reflect;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author caixiaoxiao
 * @desc 测试
 * @date 5/8/21
 */
public class BaseData<T> {
    private T data;

    public BaseData(){
        data = genDefaultConfig();
    }

    private T genDefaultConfig(){
        Class<T> cls = getConfigCls();
        System.out.println("cls:" + cls);
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<T> getConfigCls(){
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) superClass.getActualTypeArguments()[0];
    }
}
