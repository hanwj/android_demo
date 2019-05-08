package com.rxjava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 文件名: ThreadState
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/5/7
 */
public class ThreadState {
    public static void main(String[] args){
        new Thread(new TimeWaiting(),"TimeWaiting").start();
        new Thread(new Waiting(),"Waiting-1").start();
        new Thread(new Notify(),"Notify").start();
//        new Thread(new WaitingWithTimeout(20000),"WaitingWithTimeout-1").start();
        new Thread(new Blocked(),"Blocked-1").start();
        new Thread(new Blocked(),"Blocked-2").start();
    }

    private static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        System.out.println(Thread.currentThread().getName() + ": do something");
                        Waiting.class.wait();
                        Waiting.class.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class WaitingWithTimeout implements Runnable{
        private long timeout;
        public WaitingWithTimeout(long timeout){
            this.timeout = timeout;
        }

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        long future = System.currentTimeMillis() + timeout;
                        long remaining = timeout;
                        while (remaining > 0){
                            Waiting.class.wait(remaining);
                            remaining = future - System.currentTimeMillis();
                        }
                        if (remaining <= 0){
                            System.out.println(Thread.currentThread().getName() + ": timeout");
                        }else {
                            //todo 返回运行结果
                            System.out.println(Thread.currentThread().getName() + ": do something");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized(Blocked.class){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class Notify implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    System.out.println(Thread.currentThread().getName() + ": start");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        Waiting.class.notify();
                        System.out.println(Thread.currentThread().getName() + ": notify");
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
