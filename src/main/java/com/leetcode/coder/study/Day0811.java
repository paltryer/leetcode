package com.leetcode.coder.study;


import com.leetcode.coder.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Day0811 {

    public static void main(String[] args) {


    }


    //----------------------------------------【二叉树展开为链表】-----------------------------------------

    /**
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * <p>
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */
    public static void flatten(TreeNode root) {

        ArrayList<TreeNode> treeNodes = new ArrayList<TreeNode>();

        //前序遍历得到list
        preList(root, treeNodes);

        int size = treeNodes.size();

        for (int i = 1; i < size; i++) {
            TreeNode preNode = treeNodes.get(i - 1);
            TreeNode currNode = treeNodes.get(i);

            //将上节点的左设置为空
            preNode.left = null;
            //将后一设置为右节点
            preNode.right = currNode;
        }


    }


    /**
     * 二叉树的前序遍历
     *
     * @param root 树的根节点
     * @param list 结果集合
     */
    public static void preList(TreeNode root, List<TreeNode> list) {

        if (root == null) {
            return;
        }

        //将本节点放入到list中
        list.add(root);

        preList(root.left, list);
        preList(root.right, list);
    }


    //----------------------------------------【寻找重复数字】-----------------------------------------

    /**
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * <p>
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * <p>
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     */
    public static int findDuplicate(int[] nums) {


        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];

        } while (slow != fast);

        slow = 0;

        //向后移动，相等则为入口
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }


    //----------------------------------------【零钱兑换】-----------------------------------------

    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     */
    public static int coinChange(int[] coins, int amount) {


        return 0;
    }


}
