package com.jehon;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jehon
 *
 * CyclicBarrier用于让一组线程运行并互相等待，直到共同到达一个公共屏障点 (common barrier point，又被称为同步点)，被屏障拦截的所有线程就会继续执行。
 *
 * CyclicBarrier与CountDownLatch的功能非常类似。但一个CyclicBarrier实例在释放等待线程后可以继续使用。让下一批线程在屏障点等待。但CountDownLatch实例只能被使用一次。所以CyclicBarrier被称为循环 的 barrier。
 *
 * 典型的比如公司的人员利用集体郊游，先各自从家出发到公司集合，再同时出发游玩，在指定地点集合。CyclicBarrier表示大家彼此在某处等待，集合好后才开始出发，分散活动后又在指定地点集合碰面。
 *
 * 构造器
 *
 *      CyclicBarrier(int parties) 创建CyclicBarrier对象，parties 表示屏障拦截的线程数量。
 *
 *      CyclicBarrier(int parties, Runnable barrierAction) 创建 CyclicBarrier对象，该构造方法提供了一个Runnable 参数，在一组线程中的最后一个线程到达之后，执行Runnable中的程序，再之后释放正在等待的线程。Runnable在屏障点上只运行一次。
 *
 * 方法
 *
 *      int await() 通知CyclicBarrier实例，当前线程已经到达屏障点，然后当前线程将被阻塞。
 *
 *      int await(long timeout, TimeUnit unit)  指定当前线程被阻塞的时间。
 *
 *      int getNumberWaiting() 返回当前在屏障处等待的线程数。
 *
 *      int getParties() 返回CyclicBarrier的需要拦截的线程数。
 *
 *      boolean isBroken() 查询此屏障是否处于损坏状态。
 *
 *      void reset() 将屏障重置为其初始状态。
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        TotalService totalService = new TotalServiceImpl();
        CyclicBarrier barrier = new CyclicBarrier(5, new TotalTask(totalService));

        // 实际系统时查出所有省编码code的列表，然后循环，每个code生成一个线程
        new BillTask(new BillServiceImpl(), barrier, "北京").start();
        new BillTask(new BillServiceImpl(), barrier, "上海").start();
        new BillTask(new BillServiceImpl(), barrier, "广西").start();
        new BillTask(new BillServiceImpl(), barrier, "四川").start();
        new BillTask(new BillServiceImpl(), barrier, "黑龙江").start();
    }
}

/**
 * 主任务：汇总任务
 */
class TotalTask implements Runnable {

    private TotalService totalService;

    public TotalTask(TotalService totalService) {
        this.totalService = totalService;
    }

    public void run() {
        // 读取内存中各省的数据汇总，过程略
        totalService.count();
        System.out.println("开始全国汇总");
    }
}

/**
 * 子任务：计费任务
 */
class BillTask extends Thread {

    private BillService billService; // 计费服务

    private CyclicBarrier barrier;

    private String code; // 代码，按省代码分类，各省数据库独立。

    BillTask(BillService billService, CyclicBarrier barrier, String code) {
        this.billService = billService;
        this.barrier = barrier;
        this.code = code;
    }

    public void run() {
        System.out.println("开始计算--" + code + "省--数据！");
        billService.bill(code);

        // 把bill方法结果存入内存，如ConcurrentHashMap,vector等，代码略
        System.out.println(code + "省已经计算完成，并通知汇总Service！");
        try {
            // 通知Barrier已经完成
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

interface BillService {
    void bill(String code);
}

interface TotalService {
    void count();
}

class BillServiceImpl implements BillService {

    public void bill(String code) {

    }
}

class TotalServiceImpl implements TotalService {

    public void count() {

    }
}
