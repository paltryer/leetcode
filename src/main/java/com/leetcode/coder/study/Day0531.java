package com.leetcode.coder.study;

import com.leetcode.coder.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Day0531 {

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


    }

    /**
     * 是否是对称二叉树
     * 使用递归的方式
     */
    public boolean isSymmetric1(TreeNode root) {

        return isSymmetric(root.left, root.right);
    }

    /**
     * 递归判断左右节点是否对称
     */
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        //两个都为空，说明是对称的
        if (left == null && right == null) {
            return true;
        }

        //其中一个为空，说明不是对称的
        if (left == null || right == null) {
            return false;
        }

        //如果两个节点的值相同，判断他们的字节点是否对称
        if (left.val == right.val) {
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }

        //值不相等，返回false
        return false;

    }

    /**
     * 是否是对称二叉树
     * 使用迭代的方式
     */
    public boolean isSymmetric(TreeNode root) {

        return check(root.left, root.right);
    }

    /**
     * 迭代方式，
     * 需要借助队列
     */
    private boolean check(TreeNode left, TreeNode right) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.offer(left);
        queue.offer(right);

        while (!queue.isEmpty()) {
            //将队列的值弹出
            left = queue.poll();
            right = queue.poll();

            //进行判断
            if (left == null && right == null) {
                continue;
            }
            //其中一个为空，或者值不相等，说明不是对称的
            if ((left == null || right == null) || (left.val != right.val)) {
                return false;
            }

            //如果走到此处说明该对相等，将他们的子节点放入到队列中
            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }


    //-----------------------------------------------[二叉树的最大深度]----------------------------------------------------------

    /**
     * 二叉树最大深度，
     * 递归方式
     */
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }


        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 求树的最大深度
     * 使用迭代的方式
     */
    public int maxDepth1(TreeNode root){

        //借助额外的队列来存储每层的节点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        //定义总层数
        int ans = 0;
        queue.offer(root);

        while (!queue.isEmpty()){
            //因为队列中会出现两层的几点都存在的情况，使用size记录下本层循环还剩的节点
            int size  = queue.size();
            while (size>0){
                //将上层节点的子节点放入队列中
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
                //将size减到0时，那么队列中全是树的下一层节点
                size--;
            }

            //每循环一次，将层数加1
            ans++;
        }


        return ans;
    }




















}
