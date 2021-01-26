package com.cherry.heap;

/**
 * 演示堆内存
 */
public class HeapDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(30000L);
        byte[] array = new byte[1024 * 1024 * 10];
        System.out.println("2...");
        Thread.sleep(30000L);
        array = null;
        System.gc();
        System.out.println("3...");
        Thread.sleep(100000000000L);
    }
}
