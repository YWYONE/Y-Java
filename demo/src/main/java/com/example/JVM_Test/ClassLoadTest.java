package com.example.JVM_Test;

public class ClassLoadTest {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = ClassLoadTest.class.getClassLoader();
        System.out.println(loader);
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        //loader.loadClass("com.example.JVM_Test.CLassLoadTest1");
        //使用Class.forName()来加载类，默认会执行初始化块
              //  Class.forName("com.example.JVM_Test.CLassLoadTest1");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
             Class.forName("com.example.JVM_Test.CLassLoadTest1", false, loader);
    }
}

