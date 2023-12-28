package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class T1213 {
    public static void main(String[] args)throws Exception {
        HashMap<String,String> mrnamap=new HashMap<>();
        HashMap<String,String> lncrnamap=new HashMap<>();
        HashMap<String,String> mirnamap=new HashMap<>();
        FileInputStream fileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/S2：差异表达的蛋白质编码基因、lncRNA和miRNA.csv");
        Scanner scanner=new Scanner(fileInputStream,"UTF-8");
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String line=scanner.nextLine();
            String[]nums=line.split(",");

            String key=nums[0].substring(1,nums[0].length()-1);
            String type=nums[2].substring(1,nums[2].length()-1);
            String deg=nums[5].substring(1,nums[5].length()-1);
            if(type.equals("miRNA")){
                mirnamap.put(key,deg);
            }else if(type.equals("lncRNA")){
                lncrnamap.put(key,deg);
            }else if(type.equals("protein_coding")){
                mrnamap.put(key,deg);
            }
        }
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/1213/last1.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        String title="Symbol"+"\t"+"Deg"+"\t";
        title+="Symbol"+"\t";
        title+="Deg"+"\n";
        outputStreamWriter.write(title);
        FileInputStream fileInputStream1=new FileInputStream("/Users/yangweiyi/Desktop/1213/mim属性.txt");

        Scanner scanner1=new Scanner(fileInputStream1,"UTF-8");
        scanner1.nextLine();
        while(scanner1.hasNextLine()) {
            String line = scanner1.nextLine();
            String[] nums = line.split("\t");
            String rs=nums[0]+"\t";
            rs+=mirnamap.get(nums[0])+"\t";
            rs+=nums[2]+"\t";
            rs+=mrnamap.get(nums[1])+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }
        FileInputStream fileInputStream2=new FileInputStream("/Users/yangweiyi/Desktop/1213/milnclast属性.txt");

        Scanner scanner2=new Scanner(fileInputStream2,"UTF-8");
        scanner2.nextLine();
        while(scanner2.hasNextLine()) {
            String line = scanner2.nextLine();
            String[] nums = line.split("\t");
            String rs=nums[0]+"\t";
            rs+=mirnamap.get(nums[0])+"\t";
            rs+=nums[2]+"\t";
            rs+=lncrnamap.get(nums[1])+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }

        FileInputStream fileInputStream3=new FileInputStream("/Users/yangweiyi/Desktop/1213/trans.txt");

        Scanner scanner3=new Scanner(fileInputStream3,"UTF-8");
        scanner3.nextLine();
        while(scanner3.hasNextLine()) {
            String line = scanner3.nextLine();
            String[] nums = line.split("\t");
            String rs=nums[1]+"\t";
            rs+=lncrnamap.get(nums[0])+"\t";
            rs+=nums[3]+"\t";
            rs+=mrnamap.get(nums[2])+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }

    }
}
