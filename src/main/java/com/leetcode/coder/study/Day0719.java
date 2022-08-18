package com.leetcode.coder.study;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchao
 * @date 2022-07-19
 */
public class Day0719 {

    public static void main(String[] args) {


    }


    //list 查看所有软件名字

    //detail  查看某个软件信息，依赖项

    //download 下载安装包

    /**
     * 全局变量，记录下载了哪些软件
     */
    public static List<String> softList = new ArrayList<>();


    /**
     * 安装接口
     *
     * @param softName
     */
    public static void install(String softName) {
        //如果系统中已经安装了，直接返回
        if (softList.contains(softName)) {
            return;
        }


        List<String> tempList = new ArrayList<>();

        loopDown(softName, tempList);


    }

    public static void loopDown(String softName, List<String> tempList) {
        if (softList.contains(softName)) {
            return;
        }

        //下载
        download(softName);

        tempList.add(softName);

        //递归下载所有的组件依赖
        List<String> detail = detail(softName);
        if (detail == null) {
            return;
        }
        for (String s : detail) {

            if (tempList.contains(s)) {
                continue;
            }

            loopDown(s, tempList);
        }

        //1.安装
        softList.add(softName);


    }


    public static List<String> list(String softName) {

        return null;
    }

    public static void download(String softName) {

    }

    public static List<String> detail(String softName) {

        return null;
    }


}
