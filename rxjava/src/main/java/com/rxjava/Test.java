//package com.xiaoxiao;

import java.util.List;
import java.util.ArrayList;

public class Test{
	
	static class UrlConstant{
		public static String baseUrl = "http://www.caixiaoxiao.com";
	}

	static class UrlApi{
		public static String url = UrlConstant.baseUrl + "/test";
	}

	public static void main(String[] args){
		new UrlApi();
		UrlConstant.baseUrl = "http://www.caixiaoxiaosit.com";
		System.out.println(UrlApi.url);
		int[] arr = {3,2,1};
		sortIntegers(arr);

		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("12");
		list.add("123");
		list.add("1234");
		list.add("12345");
		list.add("123456");
		list.add("1234567");
		list.add("12345678");
		list.add("123456789");
		for (String str : list) {
			System.out.println(str);
			if (str.length() < 4) {
				list.remove(str);		
			}	
		}
	}


	public static void sortIntegers(int[] A) {
        // write your code here
        int size = A.length;
        int tmp;
        for (int i = 0;i < size ; i++ ){
            for(int j = 0;j < i;j++){
                if (A[i] < A[j]){

                	System.out.println("" + i + "," + j);
                	System.out.println("" + A[i] + "," + A[j]);
                    tmp = A[j];
                    A[j] = A[i];
                    A[i] = tmp;
                }                                         
            }
            printArr(A);
        } 
    }

    private static void printArr(int[] A){
    	String str = "[";
    	for (int i = 0; i < A.length;i++) {
    		str = str + A[i] + ",";	
    	}
    	System.out.println(str + "]");
    }
}