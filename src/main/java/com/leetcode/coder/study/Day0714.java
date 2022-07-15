package com.leetcode.coder.study;


import com.leetcode.coder.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day0714 {

    public static void main(String[] args) {


    }


    //----------------------------------------【单词搜索】-----------------------------------------

    /**
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     */
    public static boolean exit(char[][] board, String word) {

        int h = board.length;
        int w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }

            }

        }


        return false;
    }

    /**
     * 回溯检查方法
     *
     * @param board   原数组
     * @param visited 存储标记数组
     * @param i       x坐标
     * @param j       y坐标
     * @param s       字符串
     * @param k       使用长度
     * @return 是否是有效的
     */
    private static boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {

        if (board[i][j] != s.charAt(k)) {
            //如果字符不相等，直接返回false
            return false;
        } else if (k == s.length() - 1) {
            //已经最后一个字符，结束
            return true;
        }

        //将此字符标记为已使用过
        visited[i][j] = true;
        //表示可以移动的方向
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        boolean result = false;
        //遍历可能的方向
        for (int[] direction : directions) {
            int newi = direction[0] + i;
            int newj = j + direction[1];

            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                //判断下是否已经被使用过了
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }

            }


        }

        //进行回溯
        visited[i][j] = false;
        return result;

    }


    //----------------------------------------【不同的二叉搜索树】-----------------------------------------

    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */
    public static int numsTree(int n) {

        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        //开始循环n的长度，计算小于n的长度，只是为了方便计算后序的长度，后序计算会用到
        for (int i = 2; i <= n; ++i) {
            //以每个节点作为根节点，进行循环
            for (int j = 1; j <= i; ++j) {

                //+=  j节点一分为2，计算两部分的乘机
                G[i] += G[j - 1] * G[i - j];
            }
        }


        return G[n];
    }


    //----------------------------------------【二叉树的层序遍历】-----------------------------------------

    /**
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {

        //总返回集合
        List<List<Integer>> ansList = new ArrayList<>();

        //辅助层序遍历的队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();

        nodeQueue.add(root);
        int size = 1;

        while (!nodeQueue.isEmpty()) {

            //每一层的分集合
            ArrayList<Integer> levelList = new ArrayList<>();

            int childCount = 0;
            for (int i = 0; i < size; i++) {
                TreeNode poll = nodeQueue.poll();
                levelList.add(poll.val);

                //将不为空的左右子节点放到queue中，下层循环
                if (poll.left != null) {
                    nodeQueue.add(poll.left);
                    childCount++;
                }

                if (poll.right != null) {
                    nodeQueue.add(poll.right);
                    childCount++;
                }
            }
            //将本层放入到总集合汇总
            ansList.add(levelList);

            size = childCount;
        }


        return ansList;
    }


}
