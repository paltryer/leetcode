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
     * 爬楼梯、跳台阶都是一样的问题
     * 斐波那契数列问题
     * 递归方式 ： x = fun(x-1) + fun（x-2）
     * 迭代方式： 声明初始值，然后相加，进行值移动
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
     * <p>
     * 递归方式遍历二叉树比较简单
     * 三种方式，对应的区别就是加入到list的时机不同
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        ArrayList<Integer> nodeList = new ArrayList<>();

        inOrder(root, nodeList);
        return nodeList;
    }

    /**
     * 【中序】遍历
     */
    private static void inOrder(TreeNode root, List<Integer> nodeList) {
        if (root == null) {
            return;
        }
        //先左节点
        inOrder(root.left, nodeList);

        //根节点
        nodeList.add(root.val);

        //右节点
        inOrder(root.right, nodeList);
    }


    /**
     * 【前序】遍历
     */
    private static void beforeOrder(TreeNode root, List<Integer> nodeList) {
        if (root == null) {
            return;
        }

        //前序遍历，将节点放入到list中
        nodeList.add(root.val);

        //递归调用，打印节点
        beforeOrder(root.left, nodeList);
        beforeOrder(root.right, nodeList);

    }


    /**
     * 【后序】遍历
     */
    private static void afterOrder(TreeNode root, List<Integer> nodeList) {
        if (root == null) {
            return;
        }

        //递归调用，打印节点
        afterOrder(root.left, nodeList);
        afterOrder(root.right, nodeList);

        //后序遍历，将节点放入到list中
        nodeList.add(root.val);
    }

}
