package com.leetcode.coder.study;

import com.leetcode.coder.util.ListNode;

/**
 * @author wangchao
 **/
public class Day0516 {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node6;
        node6.next = node7;
        node4.next = node5;
        node5.next = node6;
        //ListNode firstCommonNode = findFirstCommonNode(node1, node4);
        //System.out.println(firstCommonNode);


        int sqrt = sqrt(37);
        System.out.println(sqrt);


    }

    /**
     * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
     * <p>
     * 数据范围： n \le 1000n≤1000
     * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
     */
    private static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        /**
         * 走出差值
         * 然后长的走差值
         * 最后一步步比对
         */


        ListNode tempHead1 = pHead1;
        ListNode tempHead2 = pHead2;
        //两个链表同时向后走
        while (tempHead1 != null && tempHead2 != null) {
            tempHead1 = tempHead1.next;
            tempHead2 = tempHead2.next;
        }

        //将长的剩余部分（差值）赋给index
        ListNode index = tempHead1 == null ? tempHead2 : tempHead1;

        //确定两个链表谁长
        ListNode longNode = tempHead1 == null ? pHead2 : pHead1;
        ListNode shortNode = tempHead1 == null ? pHead1 : pHead2;

        //将长的向后走差的步数
        while (index != null) {
            longNode = longNode.next;
            index = index.next;
        }

        //一步步比对
        while (longNode != null && shortNode != null) {
            //使用对象比对，比对内存地址，是同一对象，则为第一个公共对象
            if (longNode == shortNode) {
                return longNode;
            }

            //不是则向后移动
            longNode = longNode.next;
            shortNode = shortNode.next;
        }

        return null;
    }


    //-----------------------------------【求算数平方根】-------------------------------------------------------------------

    private static int sqrt(int x) {

        //二分法
        int low = 1;
        int high = x;
        if (x > 4) {
            high = high >> 1;
        }

        int mid = 0;
        while (low < high) {
            mid = low + ((high - low + 1) / 2);

            //注意，这里是mid > 来判断，   向下取整。。
            if (mid > x / mid) {
                high = mid - 1;
            } else {
                low = mid;
            }


        }
        return low;
    }


}
