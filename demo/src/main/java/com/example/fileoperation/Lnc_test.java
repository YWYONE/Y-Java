package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Lnc_test {
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
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/lncRNA.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        String title="mirna"+"\t"+"lncrna"+"\t";
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
        FileInputStream mirandafileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-lncRNA/miRNA_lnc_miranda1.txt");
        Scanner mirandascanner=new Scanner(mirandafileInputStream,"UTF-8");
        HashMap<String ,Bean1> mirandamap=new HashMap<>();
        while(mirandascanner.hasNextLine()){
            String line=mirandascanner.nextLine();
            String[]nums=line.split("\t");

            String mirna=nums[0];
            String mrna=nums[1];
            mirna=mirna.trim().substring(1);
            mrna=map.get(mrna);
            //System.out.println(mirna+"\t"+mrna);
            Bean1 bean1=new Bean1();
            bean1.energy=nums[3];
            String []startandEnd=nums[5].split(" ");
            bean1.start=startandEnd[0];
            bean1.end=startandEnd[1];
            mirandamap.put(mirna+mrna,bean1);

        }
        HashMap<String ,Bean1> pitaMap=new HashMap<>();
        FileInputStream pitafileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-lncRNA/lncm_pita_results.txt");
        Scanner pitascanner=new Scanner(pitafileInputStream,"UTF-8");
        while(pitascanner.hasNextLine()){
            String line=pitascanner.nextLine();
            String[]nums=line.split("\t");

            String mirna=nums[1];
            String mrna=nums[0];
            mrna=map.get(mrna);
            //System.out.println(mirna+"\t"+mrna);
            Bean1 bean1=new Bean1();
            bean1.energy=nums[12];
            bean1.start=nums[3];
            bean1.end=nums[2];
            pitaMap.put(mirna+mrna,bean1);
        }
        HashMap<String,String> RNAhybridchuli=new HashMap<>();
        HashMap<String,Bean1> third=new HashMap<>();
        FileInputStream RNAhybridchulifileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-lncRNA/RNAhybrid_lncm.txt");
        Scanner RNAhybridchuliscanner=new Scanner(RNAhybridchulifileInputStream,"UTF-8");
        while(RNAhybridchuliscanner.hasNextLine()){
            String line=RNAhybridchuliscanner.nextLine();
            String[]nums=line.split(":");

            String hsa=nums[2];
            String st=nums[0];
            String energy=nums[4];
            RNAhybridchuli.put(hsa+st,energy);

        }
        FileInputStream thirdfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-lncRNA/RNAhybrid_new.txt");
        Scanner thirdscanner=new Scanner(thirdfileInputStream,"UTF-8");
        String  line11=thirdscanner.nextLine();
        while(thirdscanner.hasNextLine()){
            String line=thirdscanner.nextLine();
            String[]nums=line.split("\t");
            Bean1 bean1=new Bean1();
            String st=nums[0];
            String hsa=nums[1];
            bean1.energy=RNAhybridchuli.get(hsa+st);
            bean1.start=nums[2];
            bean1.end=nums[3];
            third.put(hsa+map.get(st),bean1);
        }
        FileInputStream resultfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-lncRNA/mi-lnclastresult.txt");
        Scanner resultscanner=new Scanner(resultfileInputStream,"UTF-8");
        String  line1=resultscanner.nextLine();
        while(resultscanner.hasNextLine()){
            String line=resultscanner.nextLine();
            String[]nums=line.split("\t");
            String key=nums[0]+nums[1];
            Bean1 bean1=mirandamap.get(key);
            Bean1 bean2=pitaMap.get(key);
            Bean1 bean3=third.get(key);
            String rs=null;
            int cnt=0;
            if(bean1!=null){
                rs=nums[0]+"\t"+nums[1]+"\t"+bean1.start+"\t"+bean1.end+"\t"+bean1.energy+"\t";
            }else{
                cnt++;
                rs=nums[0]+"\t"+nums[1]+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t";
            }
            if(bean2!=null){
                rs+=bean2.start+"\t"+bean2.end+"\t"+bean2.energy+"\t";
            }else{
                cnt++;
                rs+="NA"+"\t"+"NA"+"\t"+"NA"+"\t";
            }
            if(bean3!=null){
                rs+=bean3.start+"\t"+bean3.end+"\t"+bean3.energy+"\n";
            }else{
                cnt++;
                rs+="NA"+"\t"+"NA"+"\t"+"NA"+"\n";
            }
            if(cnt<2){
                outputStreamWriter.write(rs);
            }
            outputStreamWriter.flush();
        }
    }
}
