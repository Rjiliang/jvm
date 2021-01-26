package com.cherry.direct_memory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 通过任务管理器查看直接内存
 * 禁用显式回收对直接内存的影响
 * -XX:+DisableExplicitGC 显式的
 */
public class DirectMemoryDemo2 {
    static int _1Gb = 1024 * 1024 * 1024;
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1Gb);
        System.out.println("分配1G内存....");
        System.in.read();
        System.out.println("释放1G内存....");
        byteBuffer = null;
        System.gc(); // 显式的垃圾回收，Full GC
        System.in.read();
    }
}
