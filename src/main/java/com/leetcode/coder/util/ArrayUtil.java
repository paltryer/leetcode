package com.leetcode.coder.util;

/**
 * 数组工具类
 *
 * @author wangchao
 **/
public class ArrayUtil {

    /**
     * 交换数组中两个下标值
     *
     * @param nums  数组
     * @param left  第一个数下标
     * @param right 第二个数下标
     */
    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
