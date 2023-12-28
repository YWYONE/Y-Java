package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Tihuan {
    public static void main(String[] args) throws Exception{
        HashMap<String,String> map=new HashMap<>();
        FileInputStream fileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/transresult1212.txt");
        Scanner scanner=new Scanner(fileInputStream,"UTF-8");
        String line1=scanner.nextLine();
        String[]nums1=line1.split("\t");
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/qian51212.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        for(int i=0;i<5;i++){
            outputStreamWriter.write(nums1[i]+"\t");
        }
        outputStreamWriter.write(nums1[5]+"\n");
        outputStreamWriter.flush();
        while(scanner.hasNextLine()){
            String line=scanner.nextLine();
            System.out.println(line);
            String[]nums=line.split("\t");
            String x=nums[1];
            nums[1]=x.replace("SG00000","..");
            for(int i=0;i<5;i++){
                outputStreamWriter.write(nums[i]+"\t");
            }
            outputStreamWriter.write(nums[5]+"\n");
            outputStreamWriter.flush();

        }
        fileInputStream.close();
    }
}
