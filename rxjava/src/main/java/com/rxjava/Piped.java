package com.rxjava;

import com.rxjava.reflect.BaseData;
import com.rxjava.reflect.Student;

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
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String str;
//
//        while (true) {
//            System.out.println("tttttttttt");
//            str = reader.readLine();
//            System.out.println(str);
//        }
//        reader.readLine();
//        int receive = 0;
//        while ((receive = System.in.read(read)) != -1){
//            System.out.write(receive);
//            System.out.flush();
//        }
//        System.out.println("end");

        testReverseListNode();

        BaseData<Student> test = new BaseData<Student>(){};
    }

    private static void testReverseListNode(){
        ListNode head;
        head = new ListNode("1");
        ListNode p = head;
        for (int i = 0; i < 10; i++){
            ListNode node = new ListNode(i+2+"");
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
        String value;
        ListNode next;

        ListNode(String value){
            this.value = value;
        }

        static void printListNode(ListNode head){
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
