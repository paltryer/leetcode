package com.leetcode.coder.study;


public class Day0622 {

    public static void main(String[] args) {

        int[] ints = searchRange(new int[]{1}, 1);
        System.out.printf("->", ints);
    }


    //------------------------------【下一个排列】----------------------------------------

    /**
     * 本题要求我们实现一个算法，将给定数字序列重新排列成字典序中下一个更大的排列。
     * <p>
     * 以数字序列 [1,2,3][1,2,3] 为例，其排列按照字典序依次为：
     * <p>
     * [1,2,3]\\ [1,3,2]\\ [2,1,3]\\ [2,3,1]\\ [3,1,2]\\ [3,2,1]
     * [1,2,3]
     * [1,3,2]
     * [2,1,3]
     * [2,3,1]
     * [3,1,2]
     * [3,2,1]
     * <p>
     * 这样，排列 [2,3,1][2,3,1] 的下一个排列即为 [3,1,2][3,1,2]。特别的，最大的排列 [3,2,1][3,2,1] 的下一个排列为最小的排列 [1,2,3][1,2,3]。
     * <p>
     * <p>
     * 两遍扫描
     * <p>
     * 结题思路：
     * 找到 i位，i+1 到 n都是递减的
     * 从 i+1 到n位中，找到比i大的数字
     * 进行交换
     * 将i+1后的位置都进行reverse，得到递增的（保证最小）
     */
    public static void nextPermutation(int[] nums) {
        //找到i位，比后面小；i+1到最后是降序的
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        //找到i+1中，比i位大的数字，进行交换
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }

        //将第i+1位进行翻转，变成最小的
        reverse(nums, i + 1);

    }

    /**
     * 交换两个下标
     */
    private static void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 将数组后进行交换
     */
    private static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    //------------------------------【搜索旋转排序数组】----------------------------------------

    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * <p>
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * <p>
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * <p>
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * <p>
     * 二分查找
     */
    public static int search(int[] nums, int target) {

        int length = nums.length;
        //判断数组是否只有0或者1位
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        //使用二分法
        int left = 0;
        int right = length - 1;
        //开始循环二分
        while (left <= right) {
            int mid = (length + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[0] <= nums[mid]) {
                //前半段是有序的
                if (nums[0] <= target && target < nums[mid]) {
                    //在前半段
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else {
                //后半段是有序的
                if (nums[mid] < target && target <= nums[length - 1]) {
                    //在后半段
                    left = mid + 1;
                } else {

                    right = mid - 1;
                }
            }
        }

        return -1;
    }


    //------------------------------【在排序数组中查找元素的第一个和最后一个位置】----------------------------------------

    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题
     * 二分法解决
     */
    public static int[] searchRange(int[] nums, int target) {

        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }

        return new int[]{-1, -1};
    }

    public static int binarySearch(int[] nums, int target, boolean lower) {

        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                //mid = 右边界
                right = mid - 1;
                ans = mid;
            } else {
                //mid = 左边界
                left = mid + 1;
            }
        }

        return ans;
    }

}
