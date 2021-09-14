package com.jehon.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jehon
 */
public class Lambda03 {

    @Test
    public void test01() {
        MyFun myFun1 = (a, b) -> a + b;
        MyFun myFun2 = (a, b) -> a - b;
        MyFun myFun3 = (a, b) -> a * b;
        MyFun myFun4 = (a, b) -> a / b;
    }

    public Integer operation(Integer a, Integer b, MyFun myFun) {
        return myFun.count(a, b);
    }

    @Test
    public void test02() {
        Integer result = operation(1, 2, (x, y) -> x + y);
        System.out.println(result);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(101, "Z3", 19, 9999.99),
            new Employee(102, "L4", 20, 7777.77),
            new Employee(103, "W5", 35, 6666.66),
            new Employee(104, "Tom", 44, 1111.11),
            new Employee(105, "Jerry", 60, 4444.44)
    );

    @Test
    public void test03() {
        Collections.sort(emps, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }
}
