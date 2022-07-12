package com.leetcode.coder.study;


import com.leetcode.coder.util.ListNode;

/**
 * @description: 5月10日晚练习
 * @author: wangchao
 * @create: 2022-05-10 20:56
 **/
public class Day0510 {


    public static void main(String[] args) {

        //合并两个有序链表
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node3;
        node2.next = node4;
        //递归方式
        //ListNode merge = merge(node1, node2);

        //sentinel 迭代方式
        ListNode merge = merge1(node1, node2);
        System.out.println(merge);


        int i = jumpSteps1(3);
        System.out.println(i);


    }


    /**
     * 合并两个有序链表
     * 递归方法
     */
    private static ListNode merge(ListNode list1, ListNode list2) {

        //其中一个为空，直接返回另一个
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }


        if (list1.val <= list2.val) {
            //如果1 小，使用1.next 和2合并
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            //如果2 小，使用2.next 和1合并
            list2.next = merge(list1, list2.next);
            return list2;
        }
    }


    /**
     * 合并两个有序链表
     * 迭代
     */
    private static ListNode merge1(ListNode list1, ListNode list2) {
        //创建一个虚拟节点和指针，指针负责向后移动
        ListNode sentinel = new ListNode(-1);
        ListNode index = sentinel;

        //一直向后移动，直到一个列表被移空为止
        while (list1 != null & list2 != null) {
            //判断1 2 谁小，谁小就拼接到index的后面
            if (list1.val > list2.val) {
                index.next = list2;
                list2 = list2.next;
            } else {
                index.next = list1;
                list1 = list1.next;
            }

            //指针向后移动
            index = index.next;
        }

        //若剩余，则直接移动指针
        if (list1 != null) {
            index.next = list1;
        }
        if (list2 != null) {
            index.next = list2;
        }

        //返回哨兵的next
        return sentinel.next;
    }

    //-----------------------------------【跳台阶】-------------------------------------------------

    /**
     * 跳台阶
     * 递归方式
     */
    private static int jumpSteps(int target) {

        //步数足够小，可以确认初始值
        if (target == 2 || target == 1 || target == 0) {
            return target;
        }

        //递归调用
        return jumpSteps(target - 1) + jumpSteps(target - 2);

    }

    /**
     * 跳台阶
     * 动态规划
     */
    private static int jumpSteps1(int target) {

        //如果小于等于一步，直接返回
        if (target <= 1) {
            return target;
        }

        //定义初始值，一步步向后移动
        //斐波那契数列，好处是只记录两个值，空间复杂度小
        int steps = 0;
        int first = 1;
        int second = 1;
        for (int i = 2; i <= target; i++) {
            steps = first + second;
            first = second;
            second = steps;
        }

        return steps;

    }


}
