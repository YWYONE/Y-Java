package com.example.JVM_Test;

public class CLassLoadTest1 {

    static {
        System.out.println("静态初始化块执行了！");
    }
    static int a=output();
    static int output(){
        System.out.println("静态变量初始化");
        return 5;
    }

}
