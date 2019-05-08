package com.rxjava;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件名: 一个简单的线程池
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/5/8
 */
public class SimpleThreadPool{
    private static final int MAX_THREAD_NUM = 10;
    private static final int MIN_THREAD_NUM = 1;
    private static final int DEFAULT_THREAD_NUM = 5;

    private List<Worker> workers = new ArrayList<>();
    private LinkedList<Runnable> jobs = new LinkedList<>();

    private int workerNum;
    private AtomicInteger threadNum = new AtomicInteger();
    public static void main(String[] args){

    }

    public SimpleThreadPool(){
        this(DEFAULT_THREAD_NUM);
    }

    public SimpleThreadPool(int num){
        workerNum = num > MAX_THREAD_NUM ? MAX_THREAD_NUM : num < MIN_THREAD_NUM ? MIN_THREAD_NUM : num;
        initializedWorkers(workerNum);
    }

    private void initializedWorkers(int num){
        for (int i = 0; i < num; i++){
            Worker worker = new Worker();
            Thread thread = new Thread(worker,"worker-thread-" + threadNum.incrementAndGet());
            thread.start();
            workers.add(worker);
        }
    }

    public void execute(Runnable job){
        synchronized (jobs){
            jobs.add(job);
            jobs.notify();
        }
    }

    public void addWorker(int num){
        synchronized (workers){
            if (workerNum + num > MAX_THREAD_NUM){
                num = MAX_THREAD_NUM - workerNum;
            }
            initializedWorkers(num);
            workerNum += num;
        }
    }

    public void removeWorker(int num){
        synchronized (workers){
            if (workerNum - num < MIN_THREAD_NUM){
                System.out.println("移除线程数量超过现有线程数量，至少要保证有一个线程");
                num = MIN_THREAD_NUM;
            }
            for (int i = 0; i < num;i++){
                workers.remove(0).shutdown();
            }
            workerNum -= num;
        }
    }

    public void shutdown(){
        synchronized (workers){
            for (Worker work : workers){
                work.shutdown();
            }
        }
    }

    private class Worker implements Runnable{
        private boolean running = true;
        @Override
        public void run() {
            while (running){
                Runnable job;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    job = jobs.removeFirst();
                }

                if (job != null){
                    try{
                        job.run();
                    } catch (Exception e){
                        //忽略job执行中的异常
                        e.printStackTrace();
                    }
                }
            }
        }

        public void shutdown(){
            this.running = false;
        }
    }
}
