package com.rxjava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 文件名: 并发执行多个任务
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/5/8
 */
public class ConcurrentThread {
    /**
     * 保证所有ActionRunnable可以同时执行
     */
//    private static CountDownLatch start = new CountDownLatch(1);

    /**
     * main线程等待所有ActionRunnable执行完毕后再继续执行
     */
    private static CountDownLatch end;
    public static void main(String[] args){
        int threadCount = 10;
        end = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++){
            new Thread(new ActionRunnable(i),"Thread-" + i).start();
        }

//        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            System.out.println("main线程未能等待所有线程执行完毕");
            e.printStackTrace();
        }

        System.out.println("所有线程执行完毕，main继续执行");
    }

    private static class ActionRunnable implements Runnable{
        private long delay;
        public ActionRunnable(long delay){
            this.delay = delay;
        }

        @Override
        public void run() {
//            try {
//                start.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            try {
                TimeUnit.SECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":do something");

            end.countDown();
        }
    }
}
