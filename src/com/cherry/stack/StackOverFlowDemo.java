package com.cherry.stack;

/**
 * 演示栈内存溢出 StackOverflowError
 * -Xss256k
 */
public class StackOverFlowDemo {

    private static int count= 0;

    public static void main(String[] args) {

        try {
            method1();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(count);
        }


    }
    public static void method1() {
        count ++;
        method1();
    }
}
