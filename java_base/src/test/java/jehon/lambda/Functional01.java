package jehon.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author jehon
 *
 *  函数式接口
 */
public class Functional01 {

    // Consumer<T> T为参数类型，无返回值
    @Test
    public void test01() {
        Consumer<Integer> consumer = (x) -> System.out.println("消费型接口" + x);
        consumer.accept(100);
    }

    // Supplier<T> 无参数，T为返回值类型
    @Test
    public void test02() {
        List<Integer> list = new ArrayList<>();
        List<Integer> integers = Arrays.asList(1, 2, 3);
        list.addAll(integers);

        Supplier<Integer> supplier = () -> (int) (Math.random() * 10);
        list.add(supplier.get());

        System.out.println(supplier);
        for (Integer integer :list) {
            System.out.println(integer);
        }
    }

    // Function<T, R> T为参数类型，R为返回值类型
    @Test
    public void test03() {
        String oldStr = "abc123456xyz";
        Function<String, String> function = (s) -> s.substring(1, s.length() -1);
        System.out.println(function.apply(oldStr));
    }

    // Predicate<T> T为参数类型，返回boolean
    @Test
    public void test04() {
        Integer age = 35;
        Predicate<Integer> predicate = (i) -> i >= 35;
        if (predicate.test(age)) {
            System.out.println("你该退休了");
        } else {
            System.out.println("我觉得还OK啦");
        }
    }
}
