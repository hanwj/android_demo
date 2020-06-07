package com.rxjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;

/**
 * 文件名: CompareString
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019-08-19
 */
public class CompareString {
    public static void main(String[] args){
//       test();
       quickSort();
       int i = -3;
       System.out.println("i:" + (~i));
    }

    private static void testCompareString(){
        System.out.println("字符串比较");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String str1;
            String str2;
            try {
                System.out.println("请输入第一个字符串");
                str1 = reader.readLine();
                System.out.println("请输入第二个字符串");
                str2 = reader.readLine();
                int result = str1.compareTo(str2);
                System.out.println("比较结果:" + str1 + " " + (result > 0 ? ">" : result==0 ? "=" : "<") + " " + str2);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("输入异常，请重新输入");
            }
        }

    }

    private static int compareString(String str1,String str2){
        return str1.compareTo(str2);
    }

    private static int a = 1;
    private static void test(){
        System.out.println("Thread:" + Thread.currentThread().getName() + "--a:" + a);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 2;
                System.out.println("Thread:" + Thread.currentThread().getName() + "--a:" + a);
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread:" + Thread.currentThread().getName() + "--a:" + a);
                a = 3;
                System.out.println("Thread:" + Thread.currentThread().getName() + "--a:" + a);
            }
        });
        thread2.start();


        System.out.println("Thread:" + Thread.currentThread().getName() + "--a:" + a);
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Thread:" + Thread.currentThread().getName() + "--a:" + a);
    }

    private static void quickSort(){
        int[] arr = new int[]{0,4,6,2,9,1,23,87,34};
        quickSort(arr,0,arr.length-1);
        printArr(arr);
    }

    private static void quickSort(int[] arr,int l,int r){
        if (l >= r){
            System.out.println("l:" + l + ",r:" + r);
            printArr(arr);
            return;
        }
        int pivot = arr[l];
        int start = l;
        int end = r;
        while (l < r){
            for (int i = r;i >= l;i--){
                r = i;
                if (arr[i] < pivot){
                    swap(arr,l,i);
                    break;
                }
            }
            for (int j = l;j <= r;j++){
                l = j;
                if (arr[j] > pivot){
                    swap(arr,r,j);
                    break;
                }
            }
        }
        quickSort(arr,start,l-1);
        quickSort(arr,l+1,end);
    }

    private static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void printArr(int[] arr){
        System.out.print("arr:");
        for (int i : arr){
            System.out.print(i + ",");
        }
        System.out.print("\n");
    }
}
