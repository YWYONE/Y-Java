package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Strand {
    public static void main(String[] args) throws Exception{
        HashSet<String> set=new HashSet<>();
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
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/third.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        String title="chr"+"\t";
        title+="start"+"\t";
        title+="end"+"\t";
        title+="lnc"+"\t";
        title+="lncRNA_DEG"+"\t";
        title+="lncRNA_Strand"+"\t";
        title+="chr"+"\t";
        title+="start"+"\t";
        title+="end"+"\t";
        title+="mrna"+"\t";
        title+="mRNA_DEG"+"\t";
        title+="mRNA_Strand"+"\n";
        outputStreamWriter.write(title);

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
        HashMap<String,String> mrnamap1=new HashMap<>();
        HashMap<String,String> lncrnamap1=new HashMap<>();
        FileInputStream mrnamap1Stream1=new FileInputStream("/Users/yangweiyi/Desktop/cislnc-mRNA/mrnafilter.bed");
        Scanner mrnamap1scanner1=new Scanner(mrnamap1Stream1,"UTF-8");
        mrnamap1scanner1.nextLine();
        while(mrnamap1scanner1.hasNextLine()){
            String line=mrnamap1scanner1.nextLine();
            String[]nums=line.split("\t");
            mrnamap1.put(nums[3],nums[5]);
        }
        FileInputStream lncrnamap1Stream1=new FileInputStream("/Users/yangweiyi/Desktop/cislnc-mRNA/lncfilter.bed");
        Scanner lncrnamap1scanner1=new Scanner(lncrnamap1Stream1,"UTF-8");
        lncrnamap1scanner1.nextLine();
        while(lncrnamap1scanner1.hasNextLine()){
            String line=lncrnamap1scanner1.nextLine();
            String[]nums=line.split("\t");
            lncrnamap1.put(nums[3],nums[5]);
        }
        FileInputStream resultfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/cislnc-mRNA/cisresult.txt");
        Scanner resultscanner=new Scanner(resultfileInputStream,"UTF-8");
        resultscanner.nextLine();
        while(resultscanner.hasNextLine()){
            String line=resultscanner.nextLine();
            String[]nums=line.split(" ");
            for(String a:nums){
                System.out.println(a);
            }
            String rs=null;
            String reg="\t";
            nums[3]=nums[3].substring(1,nums[3].length()-1);
            nums[7]=nums[7].substring(1,nums[7].length()-1);
            String lncg=map.get(nums[3]);
            String mg=map.get(nums[7]);
            rs=nums[0].substring(1,nums[0].length()-1)+reg;
            rs+=nums[1]+reg+nums[2]+reg;
            rs+=lncg+reg;
            rs+=lncrnamap.get(lncg)+reg;
            rs+=lncrnamap1.get(nums[3])+reg;
            rs+=nums[4].substring(1,nums[4].length()-1)+reg+nums[5]+reg+nums[6]+reg;
            rs+=mg+reg;
            rs+=mrnamap.get(mg)+reg;
            rs+=mrnamap1.get(nums[7])+"\n";
            if(!set.contains(rs)){
                outputStreamWriter.write(rs);
                set.add(rs);
            }

            outputStreamWriter.flush();



//            String key=nums[0]+nums[1];
//            Bean2 bean1=lnctarnewmap.get(key);
//            Bean2 bean2=rlbmap.get(key);
//            Bean2 bean3=RIsearchmap.get(key);
//            String rs=null;
//            if(lncrnamap.get(nums[0])!=null){
//                if(bean1!=null){
//                    rs=nums[0]+"\t"+lncrnamap.get(nums[0])+"\t"+nums[1]+"\t"+mrnamap.get(nums[1])+"\t"+bean1.lncstart+"\t"+bean1.lncend+"\t"+bean1.mstart+"\t"+bean1.mend+"\t"+bean1.energy+"\t";
//                }else{
//                    //cnt++;
//                    rs=nums[0]+"\t"+lncrnamap.get(nums[0])+"\t"+nums[1]+"\t"+mrnamap.get(nums[1])+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t";
//                }
//                if(bean2!=null){
//                    rs+=bean2.lncstart+"\t"+bean2.lncend+"\t"+bean2.mstart+"\t"+bean2.mend+"\t"+bean2.energy+"\t";
//                }else{
//                    //cnt++;
//                    rs+="NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t";
//                }
//                if(bean3!=null){
//                    rs+=bean3.lncstart+"\t"+bean3.lncend+"\t"+bean3.mstart+"\t"+bean3.mend+"\t"+bean3.energy+"\n";
//                }else{
//                    //cnt++;
//                    rs+="NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\t"+"NA"+"\n";
//                }
////            if(cnt<2){
////                outputStreamWriter.write(rs);
////            }
//                outputStreamWriter.write(rs);
//            }
//            //int cnt=0;
//
//            outputStreamWriter.flush();
        }

    }
}
