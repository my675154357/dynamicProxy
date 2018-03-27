package com.yema.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author ：yema
 * @Description ：调用对象（调用方法的对象）-----类似于租客
 *                  实现动态代理，目标对象和代理对象需要实现同一接口
 * @Date ：Create in 12:35 2018/3/27
 * @Edit ：by Intellij IDEA
 */
public class ProxyTest1 {

    /**
         java.lang.reflect.Proxy
         所有已实现的接口：
         Serializable
     */
    @Test
    public void test1(){
        //获得动态代理对象----在运行时在内存中动态的为Target创建一个虚拟的代理对象--------动态创建一个虚拟代理对象-----中介
        //objProxy是动态的代理对象，通过参数来确定需要代理的对象
        //因为参数是TargerInterface这个接口，那么动态生成的代理对象就是实现了这个接口，所以objProxy可以强转成TargerInterface类型
        TargerInterface objProxy = (TargerInterface)Proxy.newProxyInstance(
                Target.class.getClassLoader(),//与目标对象相同的类加载器
                new Class[]{TargerInterface.class},// 需要一个接口的字节码对象的数组。因为可以实现多个接口，所以这里用数组。动态代理跟反射有关，所以都是用字节码
                new InvocationHandler() {//invoke：执行代对象的方法； method：目标方法的字节码对象； args：目标方法相应的参数
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("这里可以写目标对象方法执行前的逻辑");
                        //执行目标对象的方法
                        Object invoke = method.invoke(new Target(), args);
                        System.out.println("这里可以写目标对象方法执行后的逻辑");
                        return invoke;
                    }
                }
        );
        objProxy.fun1();
        String str = objProxy.fun2();
        System.out.println(str);
    }
}
