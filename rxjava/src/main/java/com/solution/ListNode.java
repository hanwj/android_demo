package com.solution;

public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value){
        this.value = value;
    }

    public static ListNode create(int[] values){
        ListNode head = null;
        ListNode tail = null;
        for (int i = 0; i < values.length; i++){
            ListNode node = new ListNode(values[i]);
            if (head == null){
                head = node;
                tail = head;
            }else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    public static void print(ListNode head){
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
