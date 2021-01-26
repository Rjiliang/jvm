package com.cherry.stack;

public class DeadLockDemo {
    static final Integer a = 1;
    static final Integer b = 2;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (a) {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("我获得A 和 B");
                }
            }
        }).start();
        Thread.sleep(1000L);
        new Thread(()->{
            synchronized (b) {
                synchronized (a) {
                    System.out.println("我获得A 和 B");
                }
            }
        }).start();
    }

}
