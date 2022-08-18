package com.leetcode.coder.study;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchao
 * @date 2022-07-19
 */
public class Day0802 {

    public static void main(String[] args) {
        char[] ss = new  char[]{'h','e','l','l','o'};
        SubIterator subIterator = new SubIterator(ss);

        while (subIterator.hasNext()){

            String next = subIterator.next();
            System.out.println(next);

        }


    }



    public static class SubIterator implements java.util.Iterator<String> {


        List<String> strList;

        int index;

        public SubIterator(char[] chars){
            strList = new ArrayList<>();
            int length = chars.length;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                char temp = chars[i];


                //如果是结尾符号，放入到返回集合中
                if('\0' == temp){
                    strList.add(stringBuilder.toString());

                    //清空拼接内容
                    stringBuilder.delete(0,stringBuilder.length());
                    continue;
                }

                if( i == length-1){
                    stringBuilder.append(chars[length-1]);
                    strList.add(stringBuilder.toString());
                }


                stringBuilder.append(temp);
            }


        }

        @Override
        public boolean hasNext() {
            if(strList == null){
                return false;
            }
            int size =strList.size();
            return size >index;
        }


        @Override
        public String next() {

            if(!hasNext()){
                index = 0;
                return null;
            }

            return strList.get(index++);
        }
    }




}
