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

    private static void testReverseListNode(){
		ListNode head;
		head = new ListNode("1");
		ListNode p = head;
		for (int i = 0; i < 10; i++){
			ListNode node = new ListNode(i+"");
			p.next = node;
			p = node;
		}

		ListNode.printListNode(head);
		ListNode newHead = reverseListNode(head);
		ListNode.printListNode(newHead);

	}

	private static ListNode reverseListNode(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		ListNode next = head.next;
		head.next = null;
		ListNode newHead = reverseListNode(next);
		next.next = head;
		return newHead;
	}

    static class ListNode {
		public String value;
		public ListNode next;

		public ListNode(String value){
			this.value = value;
		}

		public static void printListNode(ListNode head){
			StringBuilder builder = new StringBuilder();
			builder.append(head.value);
			ListNode pNext = head.next;
			while (pNext != null){
				builder.append("->").append(pNext.value);
				pNext = pNext.next;
			}
			System.out.println("ListNode:" + builder.toString());
		}
	}
}