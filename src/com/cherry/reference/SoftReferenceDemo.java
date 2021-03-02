package com.cherry.reference;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示软引用
 * -Xms20m -XX:+PrintGCDetails -verbose:gc
 */
public class SoftReferenceDemo {

    public static final int _4MB = 4 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
         //oom
        /* List<Object> list = new ArrayList<>();
        for (int i = 0; i < 4 ; i++) {
            list.add(new Byte[_4MB]);
        }
        System.in.read();*/

        softReference();
        softReferenceWithQueue();
    }


    /**
     * 使用软引用不会出现OOM，但是可能会获取到空对象
     * SoftReference类所提供的get()方法返回Java对象的强引用。另外，一旦垃圾线程回收该Java对象之 后，get()方法将返回null（要区分为nulL的情况需要配合引用队列）
     */
    private static void softReference() {
        // list --> SoftReference --> byte[]
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> ref = new SoftReference<>(new byte[_4MB]);
            System.out.println(ref.get());
            list.add(ref);
            System.out.println(list.size());
        }
        System.out.println("循环结束："+list.size());
        for (SoftReference<byte[]> softReference : list) {
            System.out.println(softReference.get());
        }
    }

    private static void softReferenceWithQueue() {
        System.out.println("softReferenceWithQueue=====");
        List<SoftReference<byte[]>> list = new ArrayList<>();
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> ref = new SoftReference<>(new byte[_4MB], queue);
            System.out.println(ref.get());

            list.add(ref);
            System.out.println(list.size());
        }

        Reference<? extends byte[]> poll = queue.poll();
        while (poll != null) {
            list.remove(poll);
            poll = queue.poll();
        }

        System.out.println("======================");
        System.out.println(list.size());
        for (SoftReference<byte[]> softReference : list) {
            System.out.println(softReference);
        }
    }
}
