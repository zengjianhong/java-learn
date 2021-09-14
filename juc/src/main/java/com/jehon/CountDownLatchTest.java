package com.jehon;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jehon
 *
 * CountDownLatch是一个同步倒数计数器。CountDownLatch允许一个或多个线程等待其他线程完成操作。
 *
 * CountDownLatch对象内部存有一个整数作为计数器。调用countDown()方法就将计数器减1，当计数到达0时，则所有等待者会停止等待。计数器的操作是原子性的。
 *
 * 构造方法
 *
 *      CountDownLatch(int count)  构造方法参数指定了计数的次数。
 *
 * 方法
 *
 *      void await()  使当前线程在锁存器倒计数至0之前一直等待，除非线程被中断。
 *
 *      boolean await(long timeout, TimeUnit unit)  使当前线程在锁存器倒计数至0之前一直等待，除非线程被中断或超出了指定的等待时间。
 *
 *      void countDown()  计数减1。当计数为0，则释放所有等待的线程。
 *
 *      long getCount()  返回当前计数。
 *
 *      String toString()  返回标识此锁存器及其状态的字符串。
 *
 * CountDownLatch类与join方法
 * CountDownLatch实例本质与Thread的join方法相同。但join方法仅可以支持当前线程等待一个线程的结束，若需要等待多个线程，则需要逐个线程的调用join方法，非常麻烦。CountDwonLatch可以很方便的实现一个线程等待多个线程。
 *
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(3); // 同步倒数计数器

        Worker w1 = new Worker(latch, "张三");
        Worker w2 = new Worker(latch, "李四");
        Worker w3 = new Worker(latch, "王五");

        Boss boss = new Boss(latch);

        executor.execute(w1); // 工人工作
        executor.execute(w2);
        executor.execute(w3);

        executor.execute(boss); // 老板工作

        executor.shutdown();
    }
}

class Worker implements Runnable {

    private CountDownLatch downLatch;

    private String name;

    public Worker(CountDownLatch downLatch, String name) {
        this.downLatch = downLatch;
        this.name = name;
    }

    public void run() {

        this.doWork(); // 工人工作

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10)); // 工作时长
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + "活干完了！");
        this.downLatch.countDown(); // 计数减1
    }

    private void doWork() {
        System.out.println(this.name + "正在干活！");
    }
}

class Boss implements Runnable {

    private CountDownLatch downLatch;

    public Boss(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    public void run() {
        System.out.println("老板正在等待所有的工人干完活……");
        try {
            this.downLatch.await(); // 当计数部位0时，线程永远阻塞，为0则继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("工人活都干完了，老板开始检查了！");
    }
}
