package com.solution;

/**
 * 合并两个有序链表
 */
public class LinkedListMergeSolution {
    public static void main(String[] args){
        ListNode head1 = ListNode.create(new int[]{1,3,5,7,9,11,12});
        ListNode head2 = ListNode.create(new int[]{2,4,6,8,10});
        ListNode.print(head1);
        ListNode.print(head2);
        ListNode newHead = mergeLinkedList(head1,head2);
        ListNode.print(newHead);
    }

    private static ListNode mergeLinkedList(ListNode head1,ListNode head2){
        if(head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
        ListNode newHead = null;
        ListNode p1 = head1;
        ListNode p2 = head2;
        ListNode p3 = null;
        ListNode tmp;
        while(p1!= null && p2!= null){
            if(p1.value < p2.value){
                tmp = p1;
                p1 = p1.next;
            }else {
                tmp = p2;
                p2 = p2.next;
            }
            if(newHead == null){
                newHead = tmp;
                p3 = newHead;
            }else {
                p3.next = tmp;
                p3 = p3.next;
            }
        }
        if(p1 != null){
            p3.next = p1;
        }
        if(p2 != null){
            p3.next = p2;
        }
        return newHead;
    }
}
