package com.xiaoxiao.testrxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * 文件名: TestIOActivity
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2020/5/20
 */
public class TestIOActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private byte[] readFile(String filePath){
        File file = new File(filePath);
        int bufferSize = 512;
        byte[] buffer = new byte[bufferSize];
        FileInputStream fileIn = null;
        BufferedInputStream bufferIn = null;
        ByteArrayOutputStream byteArrayOut = null;
        try {
            fileIn = new FileInputStream(file);
            bufferIn = new BufferedInputStream(fileIn);
            byteArrayOut = new ByteArrayOutputStream();
            while (bufferIn.read(buffer) != -1){
                byteArrayOut.write(buffer);
            }
            return byteArrayOut.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileIn != null){
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bufferIn != null){
                    try {
                        bufferIn.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (byteArrayOut != null){
                try {
                    byteArrayOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }

    private void writeToFile(String fileName){

    }
}