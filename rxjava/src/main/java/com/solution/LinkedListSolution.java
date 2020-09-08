package com.solution;

/**
 * 翻转链表
 * 1.循环拿到反转的起始节点mNode和mNode的上一个节点
 * 2.从mNode开始反转链表n-m+1次
 * 3.设置mNode的next为n节点的next
 * 4.设置mNode的上一个节点的next为n节点
 */
public class LinkedListSolution {

    public static void main(String[] args){
        ListNode head = null;
        ListNode tail = null;
        for (int i = 1; i <= 10; i++){
            ListNode node = new ListNode(i);
            if (i == 1){
                head = node;
                tail = head;
            }else {
                tail.next = node;
                tail = node;
            }
        }

        printLinkedList(head);
        ListNode newHead = reverseLinkedList(head,2,9);
        printLinkedList(newHead);
    }

    /**
     * 反转链表
     * @param head
     * @param m 起始位置
     * @param n 结束位置
     * @return 新的head
     */
    private static ListNode reverseLinkedList(ListNode head,int m,int n){
        if (m < 1 || m >= n){
            return head;
        }

        //循环获取反转的起始m节点，并保存m节点的上一个节点
        int pos = 1;
        ListNode mNode = head;
        ListNode preNode = null;
        while (pos != m){
            preNode = mNode;
            mNode = mNode.next;
            ++pos;
        }

        //反转m到n节点
        ListNode tmp;
        ListNode cur = mNode;
        ListNode pre = null;
        for (int i = m;i <= n;i++){
            if (cur == null){
                break;
            }
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        //设置m节点next为n节点的next
        mNode.next = cur;


        if (preNode != null){
            //设置m节点上一个节点的next为n节点
            preNode.next = pre;
            return head;
        }else {
            //m节点为head节点，返回反转后的新head节点
            return pre;
        }
    }

    private static void printLinkedList(ListNode head){
        StringBuilder builder = new StringBuilder();
        builder.append(head.value);
        ListNode pNext = head.next;
        while (pNext != null){
            builder.append("->").append(pNext.value);
            pNext = pNext.next;
        }
        System.out.println("ListNode:" + builder.toString());
    }

    static class ListNode{
        int value;
        ListNode next;

        ListNode(int value){
            this.value = value;
        }
    }
}
