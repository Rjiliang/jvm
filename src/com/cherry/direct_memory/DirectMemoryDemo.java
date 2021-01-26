package com.cherry.direct_memory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将文件copy到其他位置
 * 演示ByteBuffer作用
 */
public class DirectMemoryDemo {

    static final String filePath = "F:\\CDH\\安装包\\cm5.15.0-centos7.tar.gz";
    static final String toPath = "F:\\CDH\\安装包\\cm5.15.0-centos7.tar.gz.bak";
    static final int _1M = 1024 * 1024;
    public static void main(String[] args) {
        fileCopy();//fileCopy cost:33067.766026 fileCopy cost:1922.138598
        directMemory();//directBuffer cost：825.660398  directBuffer cost：648.129385
    }

    private static void directMemory() {
        long startTime = System.nanoTime();
        try (FileChannel from = new FileInputStream(new File(filePath)).getChannel();
             FileChannel to = new FileOutputStream(new File(toPath)).getChannel();
        ) {

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1M);
            while (true) {
                int read = from.read(byteBuffer);
                if (read == -1) {
                    break;
                }
                byteBuffer.flip();
                to.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (Exception e) {

        }
        System.out.println("directBuffer cost：" + (System.nanoTime() - startTime) / 1000_000.0);
    }

    private static void fileCopy(){
        long startTime = System.nanoTime();
        try (FileInputStream from = new FileInputStream(new File(filePath));
             FileOutputStream to = new FileOutputStream(new File(toPath))
        ) {
            byte[] buffer = new byte[_1M];

            while (true) {
                int length = from.read(buffer);
                if (length == -1) {
                    break;
                }
                to.write(buffer,0,length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fileCopy cost:"+ (System.nanoTime() - startTime) /1000_000.0);

    }

}
