package com.leetcode.coder.study;

public class Day0428 {


    public static void main(String[] args) {

        //反转字符串
//        String ss = "abcdefg";
//        System.out.print(reverseStr(ss));

        //斐波那契数列
//        int fibonacci = fibonacci(4);
//        System.out.println(fibonacci);


        //是否为回文结构
//        System.out.println(isPalindrome("ab1sba"));


        //最大公约数
        int i = maxDivisor(6, 3);
        System.out.println(i);

    }


    /**
     * 反转字符串
     *
     * @param str 原字符串
     * @return 反转后字符串
     *
     * i循环，从后往前
     * 使用StringBuilder进行拼接
     */
    private static String reverseStr(String str) {

        if (str == null || str.equals("")) {
            return str;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }

        return stringBuilder.toString();
    }


    /**
     * 斐波那契额数列
     *
     * @param n 第n项
     * @return 第n项的值
     *
     * 第n项的值等于 前两项的和
     * 使用递归方式
     */
    private static int fibonacci(int n) {

        //前两项为 1
        if (n == 1 || n == 2) {
            return 1;
        }

        //递归调用
        return fibonacci(n - 1) + fibonacci(n - 2);
    }


    /**
     * 判断字符串是否是回文序列
     *
     * @param str 要判断的字符串
     * @return 是否为回文结构
     *
     * 循环长度的一半
     * 判断第i个字符和 length-i-1 个字符是否相同
     * 不相同则不是回文序列
     */
    private static boolean isPalindrome(String str) {
        int strLength = str.length();

        //循环前半段， i< length/2  即可
        for (int i = 0; i < strLength / 2; i++) {

            //判断前一个字符，和对应位置的是否相同
            if (str.charAt(i) != str.charAt(strLength - i - 1)) {
                return false;
            }

        }

        return true;
    }


    /**
     * 求两个数的最大公约数
     *
     * @param a 第一个数
     * @param b 第二个数
     * @return 最大公约数
     *
     * 1.求出 a、b中的较小值，作为循环条件
     * 2.判断 0< i < min  是否同时可以被除尽，  小技巧，从后往前除，第一个数就是最大的
     * 3.如果同时被除尽，则返回i
     */
    private static int maxDivisor(int a, int b) {

        //取a和b中较小的一个进行递减，相除
        int min = Math.min(a, b);

        //i从min -- 开始循环，找出第一个数就是最大公约数
        for (int i = min; i > 0; i--) {

            //用a、b相除，余数都为0，则为公约数
            if (a % i == 0 && b % i == 0) {
                return i;
            }

        }

        return 0;
    }
}
