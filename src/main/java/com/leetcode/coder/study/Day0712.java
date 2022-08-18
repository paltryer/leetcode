package com.leetcode.coder.study;


import com.leetcode.coder.util.TreeNode;

import java.util.*;

public class Day0712 {

    public static void main(String[] args) {


    }


    //------------------------------【实现LRU缓存】----------------------------------------

    /**
     * 最早使用的放在前面
     */
    public class LRUCache {
        //容量
        int capacity;

        Deque<Integer> queue;

        Map<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.queue = new LinkedList<>();
            this.map = new HashMap<>();
        }

        //get方法
        public int get(int key) {

            if (map.containsKey(key)) {
                //存在了
                queue.remove(key);
                queue.addFirst(key);
                return map.get(key);
            } else {
                //map中没有
                return -1;
            }

        }


        public void put(int key, int value) {
            if (map.containsKey(key)) {
                //已经存在了，进行更新
                queue.remove(key);
                queue.addFirst(key);
                map.put(key, value);
            } else {
                //map中不存在，看是否超过容量
                if (queue.size() >= capacity) {
                    //已经超过容量，移除最后元素
                    queue.removeLast();
                }
                //直接放入到map中
                queue.addFirst(key);
                map.put(key, value);

            }


        }
    }


    //------------------------------【快速排序】----------------------------------------

    public static void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }

        int i, j, base;
        i = start;
        j = end;
        base = nums[start];

        while (i < j) {
            while (i < j && nums[j] >= base) {
                j--;
            }
            while (i < j && nums[i] <= base) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }

        swap(nums, start, i);
        quickSort(nums, start, i - 1);
        quickSort(nums, j + 1, end);


    }


    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    //------------------------------【树的左右视图】----------------------------------------

    public static List<TreeNode> LeftView(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(treeNode);
        LinkedList<TreeNode> ansList = new LinkedList<>();

        int size = 1;
        while (!queue.isEmpty()) {

            //记录一下子节点的数量
            int child = 0;

            //根据size进行循环
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                //如果是第0个，则放入到集合集合中
                if (i == 0) {
                    ansList.add(poll);
                }

                //判断左右子节点是否为空，非空放入到queue中
                if (poll.left != null) {
                    queue.offer(poll.left);
                    child++;
                }

                if (poll.right != null) {
                    queue.offer(poll.right);
                    child++;
                }

            }
            //将子节点个数，赋值给size，用于下次循环
            size = child;

        }

        return ansList;
    }


    //------------------------------【两个节点的最近公共祖先】----------------------------------------

    public static TreeNode getLowestAncestor(TreeNode root, TreeNode node1, TreeNode node2) {

        if (root == null || root == node1 || root == node2) {
            return root;
        }

        //如果是父节点，会返回非空
        TreeNode left = getLowestAncestor(root.left, node1, node2);
        //如果是其中一个父节点，会返回非空
        TreeNode right = getLowestAncestor(root.right, node1, node2);

        //左右节点都是目标节点的父节点
        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;


    }

}
