package com.leetcode.coder.study;


import com.leetcode.coder.util.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Day0621 {

    public static void main(String[] args) {

        List<String> strings = generateParenthesis(3);
        System.out.println(strings);

    }


    //------------------------------【删除链表的倒数第N个节点】----------------------------------------

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
     * 迭代一次
     * 使用双指针，先让快指针走出n步，然后慢指针从头开始走，
     * 快指针到头时，慢指针在第n位置上
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode fast = head;
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy;

        //先将fast指针移动n位
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        //将fast移动到头，则slow移动到倒数n位上
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        //将slow位置删除
        slow.next = slow.next.next;

        return dummy.next;
    }

    //------------------------------【括号生成】----------------------------------------

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * 回溯方法
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();

        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public static void backtrack(List<String> ans, StringBuilder curr, int opn, int close, int max) {
        //如果达到固定长度，则添加到结果集合中
        if (curr.length() == max * 2) {
            ans.add(curr.toString());
            return;
        }

        //如果开括号小于最大值，则添加开口号
        if (opn < max) {
            curr.append('(');
            //递归调用
            backtrack(ans, curr, opn + 1, close, max);
            //删除当前位置字符
            curr.deleteCharAt(curr.length() - 1);
        }

        //如果闭括号小于最大值，则添加闭口号
        if (close < opn) {
            curr.append(')');
            //递归调用
            backtrack(ans, curr, opn, close + 1, max);
            //删除当前位置字符
            curr.deleteCharAt(curr.length() - 1);
        }

    }


}
