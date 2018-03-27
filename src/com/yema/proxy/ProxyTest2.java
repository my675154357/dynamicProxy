package com.yema.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author ：yema
 * @Description ：注意：JDK的Proxy方式实现的动态代理 目标对象必须有接口 没有接口不能实现jdk版动态代理
 * @Date ：Create in 13:21 2018/3/27
 * @Edit ：by Intellij IDEA
 */
/*
    动态代理可以做方法增强，例如spring中的aop，实现service中给方法上加事务。
    但是一般，做方法增强用 装饰者模式 来实现，就是包装类；而动态代理用来做方法拦截，比如说权限控制。使用filter只能对rul拦截，但动态代理是方法级的，
    可以实现细粒度的拦截。
*/
public class ProxyTest2 {
    public static void main(String[] args) {

        final Target target = new Target();
        //创建动态代理对象
        TargerInterface proxy = (TargerInterface)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),//获得目标对象所有的实现接口
                new InvocationHandler() {
                    //这个方法被执行几次???-------------要看代理对象被调用了几次。代理对象调用接口的所有方法都找这个invoke

                    /*
                        proxy：这个参数就是代理对象本身的引用，表示这个代理对象本身，就是外面的那个proxy。一般开发不用这个参数，要用就用外面的proxy
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //反射实现
                        Object invoke = method.invoke(target, args);//目标对象的相应的方法
                        //返回的值给代理对象
                        return invoke;
                    }
                }
        );
        proxy.fun1();//调用invoke
        String f2 = proxy.fun2();//调用invoke
        int f3 = proxy.fun3(100);//调用invoke
        System.out.println(f2);
        System.out.println(f3);
    }
}
