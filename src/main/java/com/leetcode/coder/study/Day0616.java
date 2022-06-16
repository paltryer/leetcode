package com.leetcode.coder.study;


import com.leetcode.coder.util.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Day0616 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l4.next = l5;
        l5.next = l6;

        ListNode listNode = addTwoNumbers(l1, l4);
        System.out.println(listNode);

    }


    //------------------------------【两数之和】----------------------------------------

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //head和tail是一个链表，head用于最后返回，tail用来每次向后移动
        ListNode headNode = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            //如果从下位近1，那么对此位加一
            int sum = val1 + val2 + carry;

            if (headNode == null) {
                headNode = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            //进位完后，将carry还原
            carry = sum / 10;

            //将链表向后移动一位
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        //如果最后一位存在近一的情况
        if (carry != 0) {
            tail.next = new ListNode(carry);
        }

        return headNode;
    }

    //------------------------------【无重复字符的最长子串】----------------------------------------


    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 方法一：滑动窗口
     */
    public static int lengthOfLongestSubstring(String s) {

        //hash集合，记录每个字符是否出现过
        Set<Character> oss = new HashSet<>();

        int n = s.length();
        //使用左右两个边界指针，i为左边指针，rk为右边指针
        int rk = -1, ans = 0;

        //i 到 rk不重复的，那么i+1到rk也是不重复的
        for (int i = 0; i < n; ++i) {

            if (i != 0) {
                //左指针向右移动一格，移除一个字符
                oss.remove(s.charAt(i - 1));
            }
            //如果rk+1的字符在hashSet中不存在，则继续移动
            while (rk + 1 < n && !oss.contains(s.charAt(rk + 1))) {
                //指针向右移动
                oss.add(s.charAt(rk + 1));
                ++rk;
            }

            //记录下最大值
            ans = Math.max(ans, rk + 1 - i);

        }

        return ans;
    }


}
