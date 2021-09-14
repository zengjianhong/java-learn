package com.jehon.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author jehon
 */
public class Lambda02 {

    int num = 10; //jdk1.7以前，必须final修饰

    @Test
    public void test01() {
        // 匿名内部类
        new Runnable() {

            @Override
            public void run() {
                // 在局部类中引用同级局部变量
                // 只读
                System.out.println("Hello World" + num);
            }
        };
    }

    @Test
    public void test02() {
        Runnable runnable = () -> {
            System.out.println("Hello Lambda");
        };
    }

    @Test
    public void test03() {
//        Consumer<String> consumer = (a) -> System.out.println(a);
//        consumer.accept("我觉得还行！");

        Consumer<String> consumer = a -> System.out.println(a);
        consumer.accept("我觉得还行！");
    }

    @Test
    public void test04() {
//        Comparator<Integer> comparator = (a, b) -> {
//            System.out.println("比较接口");
//            return Integer.compare(a, b);
//        };
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);
    }
}
