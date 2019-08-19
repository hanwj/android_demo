package com.rxjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件名: CompareString
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019-08-19
 */
public class CompareString {
    public static void main(String[] args){
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

}
