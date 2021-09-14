package jehon.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author jehon
 *
 * 注意：
 *  Lambda 表达实体中调用方法的参数列表、返回类型必须和函数式接口中抽象方法保持一致
 */
public class Lambda04 {

    @Test
    public void test01() {
        PrintStream ps = System.out;
        Consumer<String> con1 = (s) -> ps.println(s);
        con1.accept("aaa");

        Consumer<String> con2 = ps :: println;
        con2.accept("bbb");
    }

    @Test
    public void test02() {
        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
        System.out.println(com1.compare(1, 2));

        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(2, 1));
    }

    @Test
    public void test03() {
        BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
        System.out.println(bp1.test("a", "b"));

        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("c", "c"));
    }

    @Test
    public void test04() {
        Supplier<List> sup1 = () -> new ArrayList();

        Supplier<List> sup2 = ArrayList::new;
    }
}
