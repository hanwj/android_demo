package com.xiaoxiao.utils;

import java.io.Closeable;
import java.io.IOException;

public class FileUtil {



    /**
     * 关闭流
     * @param closeable
     */
    public static void close(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
