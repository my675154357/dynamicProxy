package com.yema.proxy;

/**
 * @Author ：yema
 * @Description ：这是目标对象（被代理的对象，有真正实现方法的对象）---类似于房东的角色
 * @Date ：Create in 12:28 2018/3/27
 * @Edit ：by Intellij IDEA
 */
public class Target implements TargerInterface{
    @Override
    public void fun1() {
        System.out.println("fun1() is running...");
    }

    @Override
    public String fun2() {
        System.out.println("fun2() is running...");
        return "fun2()";
    }

    @Override
    public int fun3(int num) {
        return num;
    }
}
