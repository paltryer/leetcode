package com.leetcode.coder.study;

import com.leetcode.coder.util.ListNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author wangchao
 **/
public class Day0429 {


    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;



    }


    /**
     * 反转链表 【迭代】
     *
     * @param listNode 原链表
     * @return 反转后链表
     */
    private static ListNode reverseList(ListNode listNode) {

        //如果为空或者只有一个节点，直接返回
        if (listNode == null || listNode.next == null) {
            return listNode;
        }

        ListNode pre = null;
        ListNode current = listNode;
        while (current != null) {

            //进行赋值
            ListNode next = current.next;
            current.next = pre;

            //向后移动
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


        //链表足够短，直接返回
        if (listNode == null || listNode.next == null) {
            return listNode;
        }

        //进行迭代
        ListNode reverseNode = reverseList1(listNode.next);

        //将本节点进行翻转
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
     * <p>
     * 使用两层循环
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


    /**
     * 选择排序
     */
    private static int[] selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }

            }

            //将找到的最小值和i位置进行替换
            if (i != min) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }

        return arr;
    }


    /**
     * 堆排序
     */
    private static int[] heapSort(int[] sourceArray) {
        int length = sourceArray.length;
        buildMaxHeap(sourceArray, length);

        for (int i = length - 1; i > 0; i--) {
            swap(sourceArray, 0, i);
            length--;
            heapify(sourceArray, 0, length);
        }

        return sourceArray;
    }


    /**
     * 构建堆
     */
    private static void buildMaxHeap(int[] arr, int length) {

        for (int i = (int) Math.floor(length / 2); i >= 0; i--) {
            heapify(arr, i, length);
        }
    }

    private static void heapify(int[] arr, int i, int length) {

        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < length && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < length && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, length);
            heapify(arr, largest, length);
        }
    }

    /**
     * 交换下标数据
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




//----------------------------------------------------------【两数之和】-----------------------------------------------------------------------

    /**
     * 两数之和
     * 1.使用Map
     * 时间复杂度为O（N）
     * 空间复杂度为O（N）
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
     * 时间复杂度为O（nlogN）
     * 空间复杂度为 O（1）
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
