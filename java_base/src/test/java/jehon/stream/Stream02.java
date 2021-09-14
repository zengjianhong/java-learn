package jehon.stream;

import jehon.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author jehon
 */
public class Stream02 {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "Z3", 19, 9999.99),
            new Employee(102, "L4", 20, 7777.77),
            new Employee(103, "W5", 35, 6666.66),
            new Employee(104, "Tom", 44, 1111.11),
            new Employee(105, "Jerry", 60, 4444.44)
    );

    /*
        filter：接收 Lambda ，从流中排除某些元素
        limit：截断流，使其元素不超过给定数量
        skip(n)：跳过元素，返回一个舍弃了前n个元素的流；若流中元素不足n个，则返回一个空流；与 limit(n) 互补
        distinct：筛选，通过流所生成的 hashCode() 与 equals() 取除重复元素
    */

    @Test
    public void test01() {
        emps.stream()
                .filter((x) -> x.getAge() > 35)
                .limit(3)
                .distinct()
                .skip(1)
                .forEach(System.out::println);
    }

    /*
        map：接收 Lambda ，将元素转换为其他形式或提取信息；接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
        flatMap：接收一个函数作为参数，将流中每一个值都换成另一个流，然后把所有流重新连接成一个流
    */
    @Test
    public void test02() {
        List<String> list = Arrays.asList("a", "b", "c");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
    }

    public Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test03() {
        List<String> list = Arrays.asList("a", "b", "c");
        Stream02 stream02 = new Stream02();
        list.stream()
                .flatMap(stream02::filterCharacter)
                .forEach(System.out::println);
    }

    /*
        sorted()：自然排序
        sorted(Comparator c)：定制排序
    */
    @Test
    public void test04() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream()
                .sorted() // compareTo()
                .forEach(System.out::println);
    }

    @Test
    public void test05() {
        emps.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }
}
