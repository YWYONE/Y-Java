package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class T1213_2 {
    public static void main(String[] args) throws  Exception{
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/1213/last3.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        String rs="Symbol"+"\t";
        rs+="Type"+"\t";
        rs+="Symbol"+"\t";
        rs+="Type"+"\t";
        rs+="Pearson"+"\t";
        rs+="Inter"+"\n";
        outputStreamWriter.write(rs);

        FileInputStream fileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/1213/1213/mim.txt");
        Scanner scanner=new Scanner(fileInputStream,"utf-8");
        scanner.nextLine();

        while(scanner.hasNextLine()){
            String line= scanner.nextLine();
            String[] nums=line.split("\t");
//            for(String a:nums){
//                System.out.println(a);
//            }
            String rs1=nums[0]+"\t";
            rs1+="miRNA"+"\t";
            rs1+=nums[2]+"\t";
            rs1+="mRNA"+"\t";
            rs1+=nums[1]+"\t";
            rs1+="miRNA-mRNA"+"\n";
            outputStreamWriter.write(rs1);
            outputStreamWriter.flush();
        }


        FileInputStream fileInputStream1=new FileInputStream("/Users/yangweiyi/Desktop/1213/1213/milnc.txt");
        Scanner scanner1=new Scanner(fileInputStream1,"utf-8");
        scanner1.nextLine();

        while(scanner1.hasNextLine()){
            String line= scanner1.nextLine();
            String[] nums=line.split("\t");
//            for(String a:nums){
//                System.out.println(a);
//            }
            String rs1=nums[0]+"\t";
            rs1+="miRNA"+"\t";
            rs1+=nums[2]+"\t";
            rs1+="lncRNA"+"\t";
            rs1+=nums[1]+"\t";
            rs1+="miRNA-lncRNA"+"\n";
            outputStreamWriter.write(rs1);
            outputStreamWriter.flush();
        }

        FileInputStream fileInputStream2=new FileInputStream("/Users/yangweiyi/Desktop/1213/1213/trans.txt");
        Scanner scanner2=new Scanner(fileInputStream2,"utf-8");
        scanner2.nextLine();
        while(scanner2.hasNextLine()){
            String line= scanner2.nextLine();
            String[] nums=line.split("\t");
            for(String a:nums){
                System.out.println(a);
            }
            String rs1=nums[1]+"\t";
            rs1+="lncRNA"+"\t";
            rs1+=nums[3]+"\t";
            rs1+="mRNA"+"\t";
            rs1+=nums[4]+"\t";
            rs1+="trans"+"\n";

            outputStreamWriter.write(rs1);
            outputStreamWriter.flush();
        }

        FileInputStream fileInputStream3=new FileInputStream("/Users/yangweiyi/Desktop/1213/1213/cislast.txt");
        Scanner scanner3=new Scanner(fileInputStream3,"utf-8");
        scanner3.nextLine();

        while(scanner3.hasNextLine()){
            String line= scanner3.nextLine();
            String[] nums=line.split("\t");
//            for(String a:nums){
//                System.out.println(a);
//            }
            String rs1=nums[2]+"\t";
            rs1+="lncRNA"+"\t";
            rs1+=nums[1]+"\t";
            rs1+="mRNA"+"\t";
            rs1+=nums[0]+"\t";
            rs1+="cislast"+"\n";
            outputStreamWriter.write(rs1);
            outputStreamWriter.flush();
        }

    }
}
