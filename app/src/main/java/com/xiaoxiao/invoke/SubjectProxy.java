package com.xiaoxiao.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 文件名: SubjectProxy
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2020/5/25
 */
public class SubjectProxy implements InvocationHandler {

    public static void test(){
        Subject subject = new SubjectImpl();
        Subject proxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),new SubjectProxy());
        proxy.doSomething();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("doSomething")){
//            method.invoke();
        }
        return null;
    }
}
