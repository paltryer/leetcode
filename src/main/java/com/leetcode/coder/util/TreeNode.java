package com.leetcode.coder.util;

/**
 * @description 二叉树类
 * @author: wangchao
 * @create: 2022-05-11 11:45
 **/
public class TreeNode {

    /**
     * 节点值
     */
    public int val;

    /**
     * 左子树
     */
    public TreeNode left;

    /**
     * 右子树
     */
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
