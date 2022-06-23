package com.leetcode.coder.study;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day0623 {

    public static void main(String[] args) {

        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 6, 7}, 7);


    }


    //------------------------------【数组总和】----------------------------------------

    /**
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * <p>
     * 回溯方法
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }


    /**
     * @param candidates 原数组
     * @param target     目标和，会随着不断减少
     * @param ans        总答案集合
     * @param combine    此次分支的集合
     * @param idx        已经使用了几个数字
     */
    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {

        if (idx == candidates.length) {
            return;
        }

        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }

        //直接跳过,如果没有跳过步骤，则会一直选择第一个元素
        dfs(candidates, target, ans, combine, idx + 1);

        //选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            //将最后一位删除：回溯
            combine.remove(combine.size() - 1);
        }

    }


    //------------------------------【全排列】----------------------------------------

    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * <p>
     * 回溯方法
     */
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        //将数组的元素转移到集合里面
        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int length = nums.length;
        backtrack(length, ans, output, 0);

        return ans;
    }

    /**
     * 抽象出的递归方法
     *
     * @param length 总长度
     * @param ans    总结果集合
     * @param list   这个分支的结果集合
     * @param index  已经使用到的索引
     */
    public static void backtrack(int length, List<List<Integer>> ans, List<Integer> list, int index) {
        //当等于总长度后，将集合放入到总结果中
        if (index == length) {
            ans.add(new ArrayList<>(list));
        }

        //由于不能删除已使用元素，需要将已经使用元素移动到前面：交换
        for (int i = index; i < length; i++) {
            //动态维护，将已用元素移动到前面
            Collections.swap(list, index, i);
            //递归调用
            backtrack(length, ans, list, index + 1);
            //回溯，交换为之前的样子
            Collections.swap(list, index, i);
        }
    }

}
