package com.leetcode.coder.study;


import com.leetcode.coder.util.ListNode;

import java.util.List;

public class Day0601 {

    public static void main(String[] args) {
        int[] price = new int[]{7, 1, 5, 3, 6, 4};
        int i = maxProfit(price);
        System.out.println(i);


    }


    //------------------------------【买卖股票最佳时机】----------------------------------------

    /**
     * 买卖股票最佳时机
     * 使用动态规划
     *
     * @return 最大利润
     */
    public static int maxProfit(int[] prices) {

        if (prices.length < 1) {
            return 0;
        }

        //max为最大利润，min为最前几天内的最小股票价格
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {

            //最大值和计算下当天卖出的利润，取个最大值
            max = Math.max(max, prices[i] - min);

            //最小值和当天价格，保证min为最小值
            min = Math.min(min, prices[i]);
        }

        return max;
    }


    /**
     * 买卖股票最佳时机
     * 使用一次遍历
     *
     * @return 最大利润
     */
    public static int maxProfit1(int[] prices) {

        if (prices.length < 1) {
            return 0;
        }

        //max为最大利润，min为最前几天内的最小股票价格
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (max < prices[i] - min) {
                max = prices[i] - min;
            }

        }

        return max;
    }


    //------------------------------【只出现一次的数字】----------------------------------------

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
     * 找出那个只出现了一次的元素
     * 使用异或 所有数字异或一遍，偶数次的异或就为0了
     *
     * @return 出现一次的数字
     */
    public static int singleNumber(int[] nums) {

        int singleNum = 0;
        //使用异或
        for (int num : nums) {
            singleNum = singleNum ^ num;
        }

        return singleNum;
    }


    //------------------------------【链表是否有环】----------------------------------------

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 使用快慢指针发
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            //指针向后移动
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    //------------------------------【链表是否相交】----------------------------------------

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode tempNodeA = headA;
        ListNode tempNodeB = headB;
        while (tempNodeA != null && tempNodeB != null) {
            tempNodeA = tempNodeA.next;
            tempNodeB = tempNodeB.next;
        }
        ListNode shortNode = null;
        ListNode longNode = null;
        ListNode diffNode = null;
        if (tempNodeA == null) {
            shortNode = headA;
            longNode = headB;
            diffNode = tempNodeB;
        } else {
            shortNode = headB;
            longNode = headA;
            diffNode = tempNodeA;
        }
        //将长链表走完差值
        while (diffNode != null) {
            diffNode = diffNode.next;
            longNode = longNode.next;
        }
        while (longNode != null && shortNode != null) {
            if (longNode == shortNode) {
                return shortNode;
            }
            longNode = longNode.next;
            shortNode = shortNode.next;
        }

        return null;
    }


}
