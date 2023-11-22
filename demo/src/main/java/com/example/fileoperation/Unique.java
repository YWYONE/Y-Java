package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Scanner;

public class Unique {
    public static void main(String[] args) throws  Exception{

        FileInputStream fileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lnc_m.txt");
        Scanner scanner=new Scanner(fileInputStream,"UTF-8");
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/lnc_m1.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        HashSet<String> set =new HashSet<>();
        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line=scanner.nextLine();
            String [] nums=line.split("\t");
            set.add(nums[0]);
        }
        System.out.println(set.size());
    }
}
