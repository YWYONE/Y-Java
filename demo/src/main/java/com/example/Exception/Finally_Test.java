package com.example.Exception;

import java.io.FileInputStream;

public class Finally_Test {
    public static void main(String[] args) {
        System.out.println(foo());
    }
    public static int foo() {
        int x;
        try {
            System.out.println("try exe ");
            FileInputStream fileInputStream=new FileInputStream("sd");//sd not exists
            x = 1;
            //finally insert
            return x;
        } catch (Exception e) {
            System.out.println("catch exe ");
            x = 2;
            //finally insert
            return x;
        } finally {
            System.out.println("finally exe ");
        }
    }
}
