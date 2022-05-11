package com.leetcode.coder.study;

import com.leetcode.coder.study.util.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description: 练习
 * @author: wangchao
 * @create: 2022-04-29 14:23
 **/
public class Day0429 {


    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;

//        ListNode listNode = reverseList(node1);
//        System.out.println(listNode);

//        ListNode listNode1 = reverseList1(node1);
//        System.out.println(listNode1);


//        ListNode listNode = reverseList2(node1);
//        System.out.println(listNode);


//----------------------------------------------------------【排序】---------------------------------------------------------------------------------------------------------------------
        int[] arr = new int[]{3, 42, 5, 1, 567};
//        int[] sort = sort(arr);
//        System.out.println(Arrays.asList(sort));

//----------------------------------------------------------【排序】---------------------------------------------------------------------------------------------------------------------
        int[] ints = twoSum1(arr, 6);
        System.out.println(ints);

    }




    /**
     * 反转链表 -迭代
     *
     * @param listNode 原链表
     * @return 反转后链表
     */
    private static ListNode reverseList(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }

        ListNode current = listNode;
        ListNode pre = null;
        while (current != null) {
            ListNode next = current.next;

            //前一个节点等于当前节点后节点
            current.next = pre;

            //整体向后移动一步
            pre = current;
            current = next;

        }

        return pre;
    }


    /**
     * 反转链表 -递归
     *
     * @param listNode 原链表
     * @return 反转后链表
     */
    private static ListNode reverseList1(ListNode listNode) {

        if (listNode == null || listNode.next == null) {
            return listNode;
        }

        //将本节点的后续节点反转
        ListNode reverseNode = reverseList1(listNode.next);

        //将本节点与后一个反转
        listNode.next.next = listNode;
        listNode.next = null;

        return reverseNode;
    }

    /**
     * 反转链表 -使用栈
     *
     * @param listNode 原链表
     * @return 反转后链表
     */
    private static ListNode reverseList2(ListNode listNode) {

        Stack stack = new Stack();

        //将节点入栈
        while (listNode != null) {
            stack.push(listNode);
            ListNode next = listNode.next;
            listNode.next = null;
            listNode = next;
        }

        ListNode reverseNode = null;
        ListNode current = null;
        while (!stack.isEmpty()) {
            if (reverseNode == null) {
                reverseNode = (ListNode) stack.pop();
                current = reverseNode;
                continue;
            }
            current.next = (ListNode) stack.pop();
            current = current.next;


        }


        return reverseNode;
    }


//----------------------------------------------------------【排序】--------------------------------------------------------------------------------


    /**
     * 【冒泡排序】
     * 对数组进行排序
     *
     * @param arr 原数组
     * @return 排序后的数组
     */
    private static int[] sort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return arr;
        }

        for (int i = 0; i < length - 1; i++) {
            for (int j = i; j < length - 1; j++) {
                if (arr[i] > arr[j]) {
                    //交换顺序
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }


    // TODO: 2022/4/29 其他方式待添加


//----------------------------------------------------------【两数之和】-----------------------------------------------------------------------

    /**
     * 两数之和
     * 1.使用Map
     */
    private static int[] twoSum(int[] arr, int target) {

        HashMap<Integer, Integer> targetMap = new HashMap<>(arr.length);

        for (int i = 0; i < arr.length; i++) {
            if (targetMap.containsKey(target - arr[i])) {
                return new int[]{targetMap.get(target - arr[i]), i};
            } else {
                targetMap.put(arr[i], i);
            }
        }

        return null;
    }


    /**
     * 两数之和
     * 2.两层循环 (暴力破解)
     * 当数组较大时，会运行超时
     */
    private static int[] twoSum1(int[] arr, int target) {

        int length = arr.length;

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }

        return null;
    }

}
