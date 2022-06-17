package com.leetcode.coder.study;


import java.util.*;

public class Day0617 {

    public static void main(String[] args) {

    }


    //------------------------------【最长回文子串】----------------------------------------

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案
     * <p>
     * 使用二元数组来存储 i -j 位字符串 是否为回文串
     */
    public static String longestPalindrome(String s) {

        int length = s.length();
        if (length < 2) {
            return s;
        }

        int begin = 0;
        int max = 1;

        //初始化一个二元数组
        boolean[][] dp = new boolean[length][length];
        //将i-i 表示为第i个字符，将数组的一个字符初始化为true
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();

        //进行循环，先枚举子串长度
        for (int L = 2; L <= length; L++) {

            //开始循环 每个字符
            for (int left = 0; left < length; left++) {
                //计算字符串的右边界
                int right = L + left - 1;

                //看下是否越界
                if (right >= length) {
                    break;
                }

                if (charArray[left] != charArray[right]) {
                    //不相等
                    dp[left][right] = false;
                } else {
                    //相等，判断前内圈是否是
                    if (right - left < 3) {
                        //只有三个字符串，则一定是回文的
                        dp[left][right] = true;
                    } else {
                        //向内移动一位，看内圈是否是回文的
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                }

                //进行判断，如果超过最大长度，则记录一下
                if (dp[left][right] && right - left + 1 > max) {
                    max = right - left + 1;
                    //记录下字符串开始的位置，用于返回时确定起始
                    begin = left;
                }

            }

        }

        return s.substring(begin, begin + max);
    }


    //------------------------------【盛最多水的容器】----------------------------------------

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * <p>
     * 使用双指针解法
     */
    public static int maxArea(int[] height) {

        int right = height.length - 1;
        int left = 0;
        int maxArea = 0;
        //主要思路，比较左右指针的height，较小的边界进行移动
        while (left < right) {
            //计算最大值
            int low = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, low * (right - left));

            //较矮的边界向内移动
            if (height[left] > height[right]) {
                //右侧向左移动
                right--;
            } else {
                //左侧向右移动
                left++;
            }

        }


        return maxArea;
    }


    //------------------------------【三数之和】----------------------------------------

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        //先对数组进行排序，然后使用两层循环，
        Arrays.sort(nums);

        int length = nums.length;
        for (int first = 0; first < length; ++first) {
            //需要保证和上次循环的first不同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            //退化为两数之和
            int target = -nums[first];
            int third = length - 1;
            //第二层循环
            for (int second = first + 1; second < length; ++second) {
                //确保和上次循环的second不同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                //将third向左移动
                while (third > second && nums[second] + nums[third] > target) {
                    third--;
                }

                if (third == second) {
                    break;
                }
                //将集合放入到返回结果中
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    //------------------------------【电话号码的字母组合】----------------------------------------

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }


        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }

    }

}
