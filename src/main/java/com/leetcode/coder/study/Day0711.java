package com.leetcode.coder.study;


import java.util.*;

public class Day0711 {

    public static void main(String[] args) {

        int[] a = new int[]{3, 2, 1, 0, 4};
        boolean b = canJump(a);
        System.out.println(b);

    }


    //------------------------------【图像旋转】----------------------------------------

    /**
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * <p>
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * <p>
     * 利用额外空间   这种不符合题目
     */
    public static void rotate1(int[][] matrix) {
        int length = matrix.length;
        int[][] matrixNew = new int[length][length];

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                matrixNew[j][length - i - 1] = matrix[i][j];
            }
        }

        //全部旋转后，将临时数组转移到 旧数组里面
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                matrix[i][j] = matrixNew[i][j];
            }
        }
    }

    /**
     * 先使用水平翻转，然后再对角线翻转
     * <p>
     * 可以达到 [row][col] = [col][length - row -1]   的效果
     */
    public static void rotate(int[][] matrix) {
        int length = matrix.length;

        //先进行水平翻转
        for (int i = 0; i < length / 2; ++i) {
            for (int j = 0; j < length; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length - i - 1][j];
                matrix[length - i - 1][j] = temp;
            }
        }

        //再进行对角线翻转
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }


    //------------------------------【字母异位词分组】----------------------------------------

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * <p>
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
     * <p>
     * 使用额外的空间map来存储
     * 如果是移位词，那么排序后应该是一样的
     * 先排序，然后根据map进行比较，一样的放入到一个key下
     * 最后将map的value返回
     */
    public static List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>(strs.length);

        //将每个字符串打成char，进行排序
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortMapKey = new String(chars);

            List<String> strList = map.getOrDefault(sortMapKey, new ArrayList<String>());

            //添加到list集合，放入到map中
            strList.add(str);
            map.put(sortMapKey, strList);
        }

        return new ArrayList<>(map.values());
    }


    //------------------------------【跳跃游戏】----------------------------------------

    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个下标。
     */
    public static boolean canJump(int[] nums) {

        int length = nums.length;
        int rightmost = 0;
        for (int i = 0; i < length; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= length - 1) {
                    return true;
                }
            }
        }

        return false;
    }


    //------------------------------【合并区间】----------------------------------------

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * <p>
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    private static int[][] merge(int[][] intervals) {

        if (intervals.length == 0) {
            return new int[0][2];
        }

        //对区间进行自定义排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        ArrayList<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {

            //将第i个的边界取出来
            int left = intervals[i][0];
            int right = intervals[i][1];

            //如果上一个的边界小于当前左边界，需要新建一个int[]
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < left) {
                merged.add(new int[]{left, right});
            } else {
                //有相交的部分，进行合并，右边界取最大的
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);

            }
        }


        return merged.toArray(new int[merged.size()][]);
    }

}
