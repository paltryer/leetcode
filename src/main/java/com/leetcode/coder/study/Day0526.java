package com.leetcode.coder.study;

import com.leetcode.coder.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day0526 {

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node3.left = node1;
        node3.right = node5;
        node1.left = node2;
        node1.right = node4;

        List<Integer> integers = inorderTraversal(node3);
        System.out.println(integers);


    }

    /**
     * 爬楼梯
     */
    public int climbStairs(int n) {
        //使用迭代的方式，递归会超时
        //可以简化为第n项等于 n-1项 和n-2 项的和
        int p = 0;
        int q = 1;
        int sum = p + q;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = sum;
            sum = p + q;
        }
        return sum;
    }


    //---------------------------------[二叉树中序遍历]-----------------------------------

    /**
     * 二叉树中序遍历
     * <p>
     * 基于栈
     * todo 时长复习
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodeList = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        //当前节点 或 栈 不为空，则一直循环
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                //压入栈中
                stack.push(current);
                //向左下移动
                current = current.left;
            } else {
                //current为空，需要弹出stack中的作为根节点
                current = stack.pop();
                //将节点放入list
                nodeList.add(current.val);
                //向右下移动
                current = current.right;
            }

        }
        return nodeList;
    }

    /**
     * 二叉树中序遍历
     * <p>
     * 递归方式
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        ArrayList<Integer> nodeList = new ArrayList<>();

        inOrder(root,nodeList);
        return nodeList;
    }

    private static void inOrder(TreeNode root ,List<Integer> nodeList){
        if(root == null){
            return;
        }
        inOrder(root.left,nodeList);
        nodeList.add(root.val);
        inOrder(root.right,nodeList);
    }

    //---------------------------------[二叉树中序遍历]-----------------------------------



}
