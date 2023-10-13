package com.example.apkfuzz.util;

public class Test {
    public static void Log(String title, String msg, boolean addSign){
        System.out.println(title + ":" + msg);
    }

    public static void Log(String title, String msg){
        System.out.println("---------------- " + title + ":" + msg + " ---------------- ");
    }

    public static void Log(String msg){
        System.out.println("---------------- " + msg + " ---------------- ");
    }
}
