package com.leetcode.coder.study;

import com.leetcode.coder.util.ListNode;

import java.util.Stack;


/**
 * @description 五月11日练习
 * @author wangchao
 * @create: 2022-05-11 10:40
 **/
public class Day0511 {

    public static void main(String[] args) {

        //连续最大子数组
//        int[] array = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
//        int maxSumOfSubArray = findMaxSumOfSubArray(array);
//        System.out.println(maxSumOfSubArray);

        int[] A = new int[]{1, 3, 5, 7, 0, 0, 0};
        int[] B = new int[]{2, 4, 10};
        merge(A, 4, B, 3);

    }

    /**
     * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
     * 数据范围:
     * 1 <= n <= 2\times10^51<=n<=2×10
     * 5
     * <p>
     * -100 <= a[i] <= 100−100<=a[i]<=100
     * <p>
     * 要求:时间复杂度为 O(n)O(n)，空间复杂度为 O(n)O(n)
     * 进阶:时间复杂度为 O(n)O(n)，空间复杂度为 O(1)O(1)
     * 示例1
     * 输入：
     * [1,-2,3,10,-4,7,2,-5]
     * 复制
     * 返回值：
     * 18
     * 复制
     * 说明：
     * 经分析可知，输入数组的子数组[3,10,-4,7,2]可以求得最大和为18
     */
    private static int findMaxSumOfSubArray(int[] array) {

        int length = array.length;
        int sumMax = array[0];
        int currentSum = sumMax;
        for (int i = 1; i < length; i++) {
            currentSum = Math.max(currentSum, 0) + array[i];
            sumMax = Math.max(sumMax, currentSum);


        }

        return sumMax;
    }


    //-----------------------------------------【判断链表中是否有环】---------------------------------------------------------------------------------


    /**
     * 快慢指针法
     */
    private static boolean hasCircle(ListNode list) {
        if (list == null) {
            return false;
        }

        ListNode slow = list;
        ListNode fast = list;
        //fast 和 fast.next  不为空
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //比较内存地址
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    //-----------------------------------------【合并两个有序数据】---------------------------------------------------------------------------------

    /**
     * 给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组
     * <p>
     * 数据范围： 0 \le n,m \le 1000≤n,m≤100，|A_i| <=100∣A
     * i
     * ​
     * ∣<=100， |B_i| <= 100∣B
     * i
     * ​
     * ∣<=100
     * <p>
     * 注意：
     * 1.保证 A 数组有足够的空间存放 B 数组的元素， A 和 B 中初始的元素数目分别为 m 和 n，A的数组空间大小为 m+n
     * 2.不要返回合并的数组，将数组 B 的数据合并到 A 里面就好了，且后台会自动将合并后的数组 A 的内容打印出来，所以也不需要自己打印
     * 3. A 数组在[0,m-1]的范围也是有序的
     * 示例1
     * 输入：
     * [4,5,6],[1,2,3]
     * 复制
     * 返回值：
     * [1,2,3,4,5,6]
     * 复制
     * 说明：
     * A数组为[4,5,6]，B数组为[1,2,3]，后台程序会预先将A扩容为[4,5,6,0,0,0]，B还是为[1,2,3]，m=3，n=3，传入到函数merge里面，然后请同学完成merge函数，将B的数据合并A里面，最后后台程序输出A数组
     */

    public static void merge(int A[], int m, int B[], int n) {

        //定义两个数组的长度，和总长度指针
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (k > 0) {
            //取A和B大值放入到A的倒数的位置上
            //防止i被减为负数，减到0时，直接将B元素放到A的位置上
            A[k--] = i >= 0 & A[i] > B[j] ? A[i--] : B[j--];
        }

    }


    //-----------------------------------------【有效括号序列】---------------------------------------------------------------------------------

    /**
     * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
     * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
     * <p>
     * 数据范围：字符串长度 0\le n \le 100000≤n≤10000
     * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
     * 示例1
     * 输入：
     * "()[]{}"
     * 复制
     * 返回值：
     * true
     */
    private static boolean isValid(String str) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (aChar == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (aChar == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            } else if (aChar == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else {
                stack.push(aChar);
            }
        }
        return stack.isEmpty();

    }

    /**
     * 写法二
     */
    private static boolean isValid1(String str) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(') {
                stack.push(')');
            } else if (aChar == '[') {
                stack.push(']');
            } else if (aChar == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != aChar) {
                return false;
            }

        }

        return stack.isEmpty();

    }


}
