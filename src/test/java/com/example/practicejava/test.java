package com.example.practicejava;

public class test {
    public static void main(String[] args) {
        String d = "2024-06-13";
//        System.out.println(d.replaceAll("-", "年"));
        System.out.println(d.replaceFirst("-", "年").replaceFirst("-", "月"));
//        System.out.println(d.replace("-", "年"));

    }
}
