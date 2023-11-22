package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Lnc_m {
    public static void main(String[] args) throws Exception{
        HashMap<String,String> map=new HashMap<>();
        FileInputStream fileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/hg38_geneid_tranid.txt");
        Scanner scanner=new Scanner(fileInputStream,"UTF-8");
        while(scanner.hasNextLine()){
            String line=scanner.nextLine();
            String[]nums=line.split("\t");
            String key=nums[1].trim();
            String value=nums[0].trim();
            map.put(key,value);
        }
        fileInputStream.close();

        HashMap<String,String> mrnamap=new HashMap<>();
        HashMap<String,String> lncrnamap=new HashMap<>();
        FileInputStream fileInputStream1=new FileInputStream("/Users/yangweiyi/Desktop/S2：差异表达的蛋白质编码基因、lncRNA和miRNA.csv");
        Scanner scanner1=new Scanner(fileInputStream1,"UTF-8");
        scanner1.nextLine();
        while(scanner1.hasNextLine()){
            String line=scanner1.nextLine();
            String[]nums=line.split(",");

            String key=nums[0].substring(1,nums[0].length()-1);
            String type=nums[2].substring(1,nums[2].length()-1);
            String deg=nums[5].substring(1,nums[5].length()-1);
            if(type.equals("lncRNA")){
                lncrnamap.put(key,deg);
            }else if(type.equals("protein_coding")){
                mrnamap.put(key,deg);
            }
        }

        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/lnc_m.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        String title="lncRNA"+"\t";
        title+="lncRNA_DEG"+"\t";
        title+="mRNA"+"\t";
        title+="mRNA_DEG"+"\t";
        title+="lnctarlncRNAStart"+"\t";
        title+="lnctarlncRNAEnd"+"\t";
        title+="lnctarmRNAStart"+"\t";
        title+="lnctarmRNAEnd"+"\t";
        title+="lnctarEnergy"+"\t";
        title+="RIblastlncRNAStart"+"\t";
        title+="RIblastlncRNAEnd"+"\t";
        title+="RIblastmRNAStart"+"\t";
        title+="RIblastmRNAEnd"+"\t";
        title+="RIblastEnergy"+"\t";
        title+="RIsearchlncRNAStart"+"\t";
        title+="RIsearchlncRNAEnd"+"\t";
        title+="RIsearchmRNAStart"+"\t";
        title+="RIsearchmRNAEnd"+"\t";
        title+="RIsearchEnergy"+"\n";
        outputStreamWriter.write(title);


        FileInputStream lnctarfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/lnctar.txt");
        Scanner lnctarscanner=new Scanner(lnctarfileInputStream,"UTF-8");
        HashMap<String ,String > lnctarmap=new HashMap<>();
        lnctarscanner.nextLine();
        while(lnctarscanner.hasNextLine()){
            String line=lnctarscanner.nextLine();
            String[]nums=line.split("\t");
            String key=nums[0]+nums[2];
            String value=nums[4];
            lnctarmap.put(key,value);
        }
        lnctarfileInputStream.close();
        FileInputStream lnctarnewfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/lnctar_new.txt");
        Scanner lnctarnewscanner=new Scanner(lnctarnewfileInputStream,"UTF-8");
        HashMap<String ,Bean2 > lnctarnewmap=new HashMap<>();
        lnctarnewscanner.nextLine();
        while(lnctarnewscanner.hasNextLine()){
            String line=lnctarnewscanner.nextLine();
            String[]nums=line.split("\t");

            Bean2 bean2=new Bean2();
            bean2.energy=lnctarmap.get(nums[0]+nums[1]);
            bean2.lncstart=nums[2];
            bean2.lncend=nums[3];
            bean2.mstart=nums[4];
            bean2.mend=nums[5];
            lnctarnewmap.put(map.get(nums[0])+map.get(nums[1]),bean2);
        }
        FileInputStream test30fileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/test30.txt");
        Scanner test30scanner=new Scanner(test30fileInputStream,"UTF-8");
        HashMap<String ,String > test30map=new HashMap<>();
        while(test30scanner.hasNextLine()){
            String line=test30scanner.nextLine();
            String[]nums=line.split("\t");

            String key=nums[0]+nums[1];
            String value=nums[2];
            test30map.put(key,value);
        }
        test30fileInputStream.close();
        FileInputStream rlbfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/RIblast30.txt");
        Scanner rlbscanner=new Scanner(rlbfileInputStream,"UTF-8");
        HashMap<String ,Bean2 > rlbmap=new HashMap<>();
        rlbscanner.nextLine();
        while(rlbscanner.hasNextLine()){
            String line=rlbscanner.nextLine();
            String[]nums=line.split("\t");
            Bean2 bean2=new Bean2();
            bean2.energy=test30map.get(nums[0]+nums[1]);
            bean2.lncstart=nums[2];
            bean2.lncend=nums[3];
            bean2.mstart=nums[5];
            bean2.mend=nums[4];
            rlbmap.put(map.get(nums[0])+map.get(nums[1]),bean2);
        }
        FileInputStream uniqfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/RIsearch_unique.txt");
        Scanner uniqscanner=new Scanner(uniqfileInputStream,"UTF-8");
        HashSet<String> uniqmap=new HashSet<>();
        uniqscanner.nextLine();
        while(uniqscanner.hasNextLine()){
            String line=uniqscanner.nextLine();
            String[]nums=line.split("\t");
            uniqmap.add(nums[0]+nums[3]);

        }
        FileInputStream RIsearchInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/RIsearch.txt");
        Scanner RIsearchscanner=new Scanner(RIsearchInputStream,"UTF-8");
        HashMap<String ,Bean2 > RIsearchmap=new HashMap<>();
        while(RIsearchscanner.hasNextLine()){
            String line=RIsearchscanner.nextLine();
            String[]nums=line.split("\t");

            if(uniqmap.contains(nums[0]+nums[3])){
                Bean2 bean2=new Bean2();
                bean2.energy=nums[7];
                bean2.lncstart=nums[1];
                bean2.lncend=nums[2];
                bean2.mstart=nums[4];
                bean2.mend=nums[5];
                RIsearchmap.put(map.get(nums[0])+map.get(nums[3]),bean2);
            }

        }
        FileInputStream resultfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/unionall35.csv");
        Scanner resultscanner=new Scanner(resultfileInputStream,"UTF-8");
        resultscanner.nextLine();
        while(resultscanner.hasNextLine()){
            String line=resultscanner.nextLine();
            String[]nums=line.split(",");
            String key=nums[0]+nums[1];
            Bean2 bean1=lnctarnewmap.get(key);
            Bean2 bean2=rlbmap.get(key);
            Bean2 bean3=RIsearchmap.get(key);
            String rs=null;
            if(lncrnamap.get(nums[0])!=null){
                if(bean1!=null){
                    rs=nums[0]+"\t"+lncrnamap.get(nums[0])+"\t"+nums[1]+"\t"+mrnamap.get(nums[1])+"\t"+bean1.lncstart+"\t"+bean1.lncend+"\t"+bean1.mstart+"\t"+bean1.mend+"\t"+bean1.energy+"\t";
                }else{
                    //cnt++;
                    rs=nums[0]+"\t"+lncrnamap.get(nums[0])+"\t"+nums[1]+"\t"+mrnamap.get(nums[1])+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t";
                }
                if(bean2!=null){
                    rs+=bean2.lncstart+"\t"+bean2.lncend+"\t"+bean2.mstart+"\t"+bean2.mend+"\t"+bean2.energy+"\t";
                }else{
                    //cnt++;
                    rs+="NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t";
                }
                if(bean3!=null){
                    rs+=bean3.lncstart+"\t"+bean3.lncend+"\t"+bean3.mstart+"\t"+bean3.mend+"\t"+bean3.energy+"\n";
                }else{
                    //cnt++;
                    rs+="NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\n";
                }
//            if(cnt<2){
//                outputStreamWriter.write(rs);
//            }
                outputStreamWriter.write(rs);
            }
            //int cnt=0;

            outputStreamWriter.flush();
        }

    }

}
