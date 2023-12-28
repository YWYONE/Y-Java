package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class BindTest {
    public static void main(String[] args) throws Exception{
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/miRna1210.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        String title="miRNA"+"\t"+"miRNA_DEG"+"\t"+
                "target"+"\t"+
                "target_type"+"\t"+
                "target_DEG"+"\t"
                ;
        title+="mirandaStart"+"\t";
        title+="mirandaEnd"+"\t";
        title+="mirandaEnergy"+"\t";
        title+="pitaStart"+"\t";
        title+="pitaEnd"+"\t";
        title+="pitaEnergy"+"\t";
        title+="RNAhybridStart"+"\t";
        title+="RNAhybridEnd"+"\t";
        title+="RNAhybridEnergy"+"\n";
        outputStreamWriter.write(title);

        HashMap<String,String> mrnamap=new HashMap<>();
        HashMap<String,String> lncrnamap=new HashMap<>();
        HashMap<String,String> mirnamap=new HashMap<>();
        FileInputStream fileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/S2：差异表达的蛋白质编码基因、lncRNA和miRNA.csv");
        Scanner scanner=new Scanner(fileInputStream,"UTF-8");
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String line=scanner.nextLine();
            String[]nums=line.split(",");
            for(String a:nums){
                System.out.println(a);
            }
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
        fileInputStream.close();
        FileInputStream resultfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA_lncRNA_1210.txt");
        Scanner resultscanner=new Scanner(resultfileInputStream,"UTF-8");
        resultscanner.nextLine();
        while(resultscanner.hasNextLine()){
            String line=resultscanner.nextLine();
            String[]nums=line.split("\t");
            boolean isW=true;
            String rs=null;
            rs=nums[0]+"\t";
            rs+=mirnamap.get(nums[0])+"\t";
            rs+=nums[1]+"\t";
            rs+="lncRNA"+"\t";
            String value=lncrnamap.get(nums[1]);
            if(value==null){
                isW=false;
            }
            rs+=value+"\t";
            for(int i=2;i<10;i++){
                rs+=nums[i]+"\t";
            }
            rs+=nums[10]+"\n";
            if(isW==true){
                outputStreamWriter.write(rs);
            }
            outputStreamWriter.flush();
        }
        FileInputStream resultfileInputStream1=new FileInputStream("/Users/yangweiyi/Desktop/mRNA_1210.txt");
        Scanner resultscanner1=new Scanner(resultfileInputStream1,"UTF-8");
        resultscanner1.nextLine();
        while(resultscanner1.hasNextLine()){
            String line=resultscanner1.nextLine();
            String[]nums=line.split("\t");
            boolean isW=true;
            String rs=null;
            rs=nums[0]+"\t";
            rs+=mirnamap.get(nums[0])+"\t";
            rs+=nums[1]+"\t";
            rs+="mRNA"+"\t";
            String value=mrnamap.get(nums[1]);
            if(value==null){
                isW=false;
            }
            rs+=value+"\t";
            for(int i=2;i<10;i++){
                rs+=nums[i]+"\t";
            }
            rs+=nums[10]+"\n";
            if(isW==true){
                outputStreamWriter.write(rs);
            }
            outputStreamWriter.flush();
        }
    }
}
