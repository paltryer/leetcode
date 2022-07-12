package com.leetcode.coder.study;


import com.leetcode.coder.util.ListNode;
import com.leetcode.coder.util.TreeNode;


public class Day0602 {

    public static void main(String[] args) {
        int[] a = new int[]{2, 1};
        moveZeroes(a);

    }


    //------------------------------【多数元素】----------------------------------------

    /**
     * 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素
     * 解体思路：因为超过总数的一半，所以记录下index和count，
     * 如果相同，就count+1，否则减1，减到负数后，从新开始
     */
    public static int majorityElement(int[] nums) {
        int current = -1;
        int count = 0;

        for (int num : nums) {
            //计算count的值
            if (num == current) {
                count++;
            } else {
                count--;
            }
            //如果count的值小于0，从新开始计算
            if (count < 0) {
                current = num;
                count = 1;
            }
        }

        return current;
    }


    //------------------------------【反转链表】----------------------------------------

    /**
     * 【复习】
     * 反转链表
     * 迭代方式
     */
    public static ListNode reverseList1(ListNode head) {

        if (head == null) {
            return head;
        }


        ListNode pre = null;

        ListNode current = head;

        while (current != null) {
            //反转
            ListNode next = current.next;
            current.next = pre;

            //向后移动
            pre = current;
            current = next;
        }
        return pre;
    }

    /**
     * 【复习】
     * 反转链表
     * 递归的方式
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode listNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return listNode;
    }


    //------------------------------【翻转二叉树】----------------------------------------

    /**
     * 使用递归调用
     * 可以简化为，反转每一个节点的左右两个节点
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        //将左右节点调换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //递归将两个子节点进行递归反转
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }


    //------------------------------【回文链表】----------------------------------------

    /**
     * 判断该链表是否是回文链表
     * <p>
     * 使用快慢指针，将慢指针走到一半位置
     * 将后半部分反转
     * 进行一步步比对
     */
    public static boolean isPalindrome(ListNode head) {

        //使用快慢指针，将满指针走到一半的位置
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //原链表的后半部分
        ListNode afterHead = slow;
        //将后半部分进行反转
        ListNode newHead = reverseList(afterHead);

        //循环去比较每一个节点是否相等
        while (newHead != null) {
            if (head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }

        return true;
    }

    //------------------------------【移动零】----------------------------------------

    /**
     * 将所有0移动到数组的末尾，同时保证非零元素的相对顺序
     * 对原数组进行操作
     */
    public static void moveZeroes1(int[] nums) {
        //维护一个最小的非零 index
        int index = 0;
        for (int i = 0; i < nums.length; i++) {

            //如果第i个不为零，则将它移动到index位置上，将原本的i变为0，继续向后循环
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        //不能同时将0覆盖，需要另外起一个循环来
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 使用官方的双指针方法
     */
    public static void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        int length = nums.length;
        while (fast < length) {
            if (nums[fast] != 0) {
                swap(nums, slow, fast);
                //如果发生交换，slow++
                slow++;
            }
            //无论怎样，fast++
            fast++;
        }
    }

    /**
     * 交换数组元素
     */
    private static void swap(int[] nums, int left, int right) {
        int temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;
    }

}
