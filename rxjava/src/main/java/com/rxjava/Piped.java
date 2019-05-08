package com.rxjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件名: Piped
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/5/6
 */
public class Piped {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while (true) {
            System.out.println("tttttttttt");
            str = reader.readLine();
            System.out.println(str);
        }
//        reader.readLine();
//        int receive = 0;
//        while ((receive = System.in.read(read)) != -1){
//            System.out.write(receive);
//            System.out.flush();
//        }
//        System.out.println("end");
    }


}
