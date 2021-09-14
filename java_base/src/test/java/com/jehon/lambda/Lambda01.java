package com.jehon.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author jehon
 */
public class Lambda01 {

    @Test
    public void test01() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, 02);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };
        // 调用
        TreeSet<Integer> set = new TreeSet<>(comparator);
    }

    @Test
    public void test02() {
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);
        TreeSet<Integer> set = new TreeSet<>(comparator);
    }
}
