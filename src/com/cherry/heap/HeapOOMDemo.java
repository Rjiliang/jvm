package com.cherry.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出 java.lang.OutOfMemoryError: Java heap space
 * -Xms8m
 * Java8来说，一般堆内存的初始容量为物理内存大小的1/64， 最大内存不超过物理内存的1/4或1G.
 */
public class HeapOOMDemo {

    public static void main(String[] args) {
        int i = 0;
        try {
            Thread.sleep(30000L);
            List<String> list = new ArrayList<>();
            String a = "hello";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(i);
        }

    }

}
