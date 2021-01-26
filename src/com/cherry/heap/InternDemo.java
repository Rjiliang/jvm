package com.cherry.heap;

public class InternDemo {

    //["a","b"]
    public static void main(String[] args) {
        //new String("a")在堆中创建了对象，值和串池中“a”相同但不是同一个对象
        //在串池中放入了a和b, s 为新的字符串new String("ab")仅仅存在堆中，不在串池（默认动态拼接的字符串不会放入串池，串池都是常量的字符串值）
        String s = new String("a") + new String("b");

        //intern函数将动态拼接的字符串放入串池，如果有则不会放入，如果没有，放入串池，将串池中的对象返回
        String s2 = s.intern();//将“ab” 放入串池，返回“ab”

        System.out.println(s == s2);//true
        //执行到这行代码，加载ab常量字符串时，发现串池中存在，返回串池中的“ab”
        System.out.println("ab" == s2);//true
    }
}
