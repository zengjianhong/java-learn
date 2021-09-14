package jehon.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author jehon
 */
public class Stream03 {

    /*
        allMatch：检查是否匹配所有元素
        anyMatch：检查是否至少匹配一个元素
        noneMatch：检查是否没有匹配所有元素
        findFirst：返回第一个元素
        findAny：返回当前流中的任意元素
        count：返回流中元素的总个数
        max：返回流中最大值
        min：返回流中最小值
    */
    public enum Status {
        FREE, BUSY, VOCATION;
    }

    @Test
    public void test01() {
        List<Status> list = Arrays.asList(Status.FREE, Status.BUSY, Status.VOCATION);

        boolean flag1 = list.stream()
                .allMatch((s) -> s.equals(Status.BUSY));
        System.out.println(flag1);

        boolean flag2 = list.stream()
                .anyMatch((s) -> s.equals(Status.BUSY));
        System.out.println(flag2);

        boolean flag3 = list.stream()
                .noneMatch((s) -> s.equals(Status.BUSY));
        System.out.println(flag3);

        // 避免空指针异常
        Optional<Status> op1 = list.stream()
                .findFirst();
        // 如果optional为空，找一个替代的对象
        Status s1 = op1.orElse(Status.BUSY);
        System.out.println(s1);

        Optional<Status> op2 = list.stream()
                .findAny();
        System.out.println(op2.get());

        long count = list.stream().count();
        System.out.println(count);
    }
}
