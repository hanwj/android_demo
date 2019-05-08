package com.rxjava;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadMain {

    public static void main(String[] args){
        testThread();
//        testAtomic();
    }

    private static int a = 1;
    private static boolean stop = false;
    private static void testThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 2;
                while (!stop){
                    System.out.printf("thread is running\n");
                }
            }
        });
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("a = %d\n",a);
//        while (true){
//            System.out.printf("a = %d\n",a);
//            if (a != 1){
//                break;
//            }
//        }
        stop = true;
    }

    private static AtomicInteger total = new AtomicInteger();
    private static void testAtomic(){
        for (int i = 0; i < 10;i++){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    for (int i =0; i < 1000;i++){
                        total.incrementAndGet();
                    }
                    System.out.printf("total = %d\n",total.get());
                }
            }.start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("total: %d\n",total.get());
    }
}
