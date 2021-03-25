package com.solution;

/**
 * 文件名: 两个线程交替工作
 * 描述：两个线程交替打印1，2，3，4，5，6 。。。
 * 修改人: caixiaoxiao
 * 日期: 2021/3/3
 */
public class ThreadInTurn {

    private volatile int value = 0;
    private Object lock = new Object();

    public static void main(String[] args){
        new ThreadInTurn().test();
    }

    public void test(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (lock){
                        printValue();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable,"Thread A");
        Thread thread2 = new Thread(runnable,"Thread B");
        thread1.start();
        thread2.start();
    }

    private void printValue(){
        value++;
        System.out.println(Thread.currentThread().getName() + ": value=" + value);
    }
}
