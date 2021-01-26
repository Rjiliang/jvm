package com.cherry.methodArea;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 演示元空间溢出
 * java8 方法区的实现是MateSpace 默认使用的本地内存
 * -XX:MaxMetaspaceSize=8m
 */
public class MethodAreaDemo extends ClassLoader {//加载类的二进制字节码

    public static void main(String[] args) {
        int j = 0;
        try {
            MethodAreaDemo methodAreaDemo = new MethodAreaDemo();
            for (int i = 0; i <30000; i++) {
                //ClassWriter 生成类的二进制字节码
                ClassWriter cw = new ClassWriter(0);
                //版本号，访问修饰符，类名，包名，类的父类，实现的接口名称
                cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                byte[] bytes = cw.toByteArray();
                //类加载器，触发类的加载
                methodAreaDemo.defineClass("Class"+i,bytes,0,bytes.length);
            }
        }finally {
            System.out.println(j);
        }
    }

}
