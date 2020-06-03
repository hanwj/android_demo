package com.rxjava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件名: AtomicTest
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019-10-15
 */
public class AtomicTest {

    private static int num = 5000; //总共事务数
    private static int concurrentNum = 3; //并行数量
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args){
        String test = "aaaaa=";
        String[] arr = test.split("=");
        System.out.println("aaaaaa:" + arr.length);

        CountDownLatch countDownLatch = new CountDownLatch(num);
        Semaphore semaphore = new Semaphore(concurrentNum);
        ExecutorService exc = Executors.newCachedThreadPool();
        long curTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < num;i++){
                exc.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            semaphore.acquire();
                            count.incrementAndGet();
//                            System.out.println("count:"+count);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            semaphore.release();
                            countDownLatch.countDown();
                        }
                    }
                });
            }
            countDownLatch.await();
            exc.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count:" + count.get() + ",elapsed:" + (System.currentTimeMillis() - curTime));
    }
}
