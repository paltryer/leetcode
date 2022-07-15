package com.leetcode.coder.study;


import com.leetcode.coder.util.ArrayUtil;
import com.leetcode.coder.util.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Day0713 {

    public static void main(String[] args) {

        int i = uniquePaths(3, 7);
        System.out.println(i);

    }

    /**
     * 滴滴面试题【一面】
     */
    public static ListNode merge(ListNode node1, ListNode node2) {

        //返回链表
        ListNode ansList = new ListNode(-1);

        while (node1 != null && node2 != null) {
            //如果都不为空，进行合并
            if (node1.val <= node2.val) {
                ansList.next = node1;
                node1 = node1.next;

            } else {
                ansList.next = node2;
                node2 = node2.next;
            }

            //将返回结果向后移动
            ansList = ansList.next;
        }

        //如果node1 有剩余
        if (node1 != null) {
            ansList.next = node1;
        }

        //如果node2 有剩余
        if (node2 != null) {
            ansList.next = node2;
        }

        return ansList.next;

    }


    //----------------------------------------【不同路径】-----------------------------------------

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * <p>
     * 问总共有多少条不同的路径？
     */
    public static int uniquePaths(int m, int n) {
        //使用二维数组存储可能路径数
        int[][] f = new int[m][n];

        //将第一列全部初始为1
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        //将第一行全部初始为1
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }

        //计算中间位置的值
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                //到达这位的数 = 前一个 + 上一个
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }

        return f[m - 1][n - 1];
    }


    //----------------------------------------【最小路径和】-----------------------------------------

    /**
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * 核心思想和上面的路径是一样的
     * 1.需要创建个二维数组
     * 2.将第一列、第一行的值初始化
     * 3.使用动态规划去计算min 中间的值
     */
    public static int minPathSum(int[][] grid) {

        //如果二维数组是空的，直接返回0
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int columns = grid[0].length;

        //声明一个数组用来存储最小和
        int[][] dp = new int[rows][columns];

        dp[0][0] = grid[0][0];
        //将第一列的值计算完毕
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        //将第一行的值计算完毕
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        //计算中间每个值得最小值
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {

                //动态规划计算,保证是路径的最小值
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }

        }

        return dp[rows - 1][columns - 1];
    }


    //----------------------------------------【颜色分类】-----------------------------------------

    /**
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * <p>
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 必须在不使用库的sort函数的情况下解决这个问题
     * <p>
     * 题中只有三个颜色，所以仅对前两种颜色进行处理交换即可
     */
    public static void sortColors(int[] nums) {
        int length = nums.length;

        int index = 0;

        //将0全部移动到前面
        for (int i = 0; i < length; ++i) {
            if (nums[i] == 0) {
                //将第i位置和index进行交换
                ArrayUtil.swap(nums, i, index++);
            }
        }

        //将1全部移动到前面
        for (int i = index; i < length; i++) {

            if (nums[i] == 1) {
                ArrayUtil.swap(nums, i, index++);
            }
        }
    }


    //----------------------------------------【子集】-----------------------------------------

    /**
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * 回溯
     */
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        dfs(0, nums);
        return ans;
    }

    /**
     * 回溯方法
     */
    public void dfs(int cur, int[] nums) {

        //如果达到最大长度，则放入结果集合中
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        //采用了本元素，放入到集合中
        t.add(nums[cur]);
        dfs(cur + 1, nums);

        //回溯将新添加的移除
        t.remove(t.size() - 1);

        //跳过本元素的情况
        dfs(cur + 1, nums);

    }
}
