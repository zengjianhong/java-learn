package jehon.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author jehon
 */
public class Stream01 {

    /**
     * 创建流
     */
    @Test
    public void test01() {
        // 集合流
        // - Collection.stream() 穿行流
        // - Collection.parallelStream() 并行流
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 数组流
        String[] strings = new String[10];
        Stream<String> stream2 = Arrays.stream(strings);

        // Stream 静态方法
        // Stream.of(...)
        Stream<Integer> stream3 = Stream.of(1, 2, 3);

        // 无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0, (i) -> ++i+i++);
        stream4.forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }
}
