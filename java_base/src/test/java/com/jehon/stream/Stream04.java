package com.jehon.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author jehon
 */
public class Stream04 {

    /*
        归约：reduce(T identity, BinaryOperator) / reduce(BinaryOperator) 可以将流中的数据反复结合起来，得到一个值
        收集：collect 将流转换成其他形式；接收一个 Collector 接口的实现，用于给流中元素做汇总的方法
    */

    // java:
    // - reduce: 需提供默认值（初始值）
    // Kotlin:
    // - fold: 不需要默认值（初始值）
    // - reduce: 需提供默认值（初始值）
    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer integer = list.stream()
                .reduce(0, (x, y) -> x +y);
        System.out.println(integer);
    }
}
