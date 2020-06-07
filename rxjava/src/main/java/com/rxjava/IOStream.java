package com.rxjava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件名: IOStream
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2020/4/23
 */
public class IOStream {
    public static void main(String[] args){
        int input;
        System.out.println("开始输入");
        try {
            while ((input = System.in.read()) != -1){
                System.out.println("input:" + input);
            }
        }catch (Exception e){
            System.out.println("exception");
        }
        System.out.println("输入结束");
    }

    private static void readFile(String path){
        File file = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(String path,String content){
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(path));
            outputStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
