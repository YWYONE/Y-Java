package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class T1213_1 {
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
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/1213/last2.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        String title="Symbol"+"\t"+"Deg"+"\n";

        outputStreamWriter.write(title);
        FileInputStream fileInputStream1=new FileInputStream("/Users/yangweiyi/Desktop/1213/last1.txt");

        Scanner scanner1=new Scanner(fileInputStream1,"UTF-8");
        scanner1.nextLine();
        while(scanner1.hasNextLine()) {
            String line = scanner1.nextLine();
            String[] nums = line.split("\t");
            String rs=nums[0]+"\t";
            rs+=nums[1]+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }
        fileInputStream1.close();
        FileInputStream fileInputStream2=new FileInputStream("/Users/yangweiyi/Desktop/1213/last1.txt");

        Scanner scanner2=new Scanner(fileInputStream2,"UTF-8");
        scanner2.nextLine();
        while(scanner2.hasNextLine()) {
            String line = scanner2.nextLine();
            String[] nums = line.split("\t");
            String rs=nums[2]+"\t";
            rs+=nums[3]+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }


    }
}
