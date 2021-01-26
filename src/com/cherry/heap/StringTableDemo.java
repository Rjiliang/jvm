package com.cherry.heap;

public class StringTableDemo {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";

        String s4 = s1 + s2;// new StringBuilder.append("a").append("b").toString()  toString -> new String("ab")在堆上创建了对象

        System.out.println(s3 == s4);//false
    }

}
