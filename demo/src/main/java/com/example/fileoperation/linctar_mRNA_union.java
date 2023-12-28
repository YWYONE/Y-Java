package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class linctar_mRNA_union {
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

        HashSet<String> lnc=new HashSet<>();

        FileInputStream fileInputStream1=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/unionall35.csv");
        Scanner scanner1=new Scanner(fileInputStream1,"UTF-8");
        scanner1.nextLine();
        FileOutputStream fileOutputStream1=new FileOutputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/RIsearch_ensg_sss.txt");
        OutputStreamWriter outputStreamWriter1=new OutputStreamWriter(fileOutputStream1);
        while(scanner1.hasNextLine()){
            String line=scanner1.nextLine();
            String[]nums=line.split(",");
            outputStreamWriter1.write(nums[0]+nums[1]+"\n");
            lnc.add(nums[0]+nums[1]);
            outputStreamWriter1.flush();

        }
//        for(String key:lnc){
//            System.out.println(key);
//            break;
//        }

        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/RIsearch_ensg_filter.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);

        String title="mRNA"+"\t";
        title+="lncRNA"+"\t";
        title+="energe"+"\t";
        title+="mRNAStart"+"\t";
        title+="mRNAEnd"+"\t";
        title+="lncRNAStart"+"\t";
        title+="lncRNAEnd"+"\t";
        title+="\n";

        outputStreamWriter.write(title);

        FileInputStream fileInputStream2=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/RIblast_ensg.txt");

        Scanner scanner2=new Scanner(fileInputStream2,"UTF-8");
        scanner2.nextLine();
        while(scanner2.hasNextLine()) {
            String line = scanner2.nextLine();
            String[] nums = line.split("\t");
            outputStreamWriter.write(nums[1]+nums[0]+"\n");
            if(lnc.contains(nums[1]+nums[0])){
                System.out.println("find");
            }
            outputStreamWriter.flush();
            //outputStreamWriter.flush();
            //System.out.println(nums[1]+nums[0]);
//            if (lnc.contains(nums[1] + nums[0])) {
//                System.out.println("find");
//                outputStreamWriter.write(line);
//                outputStreamWriter.flush();
//            }
        }

//            String rs=
//                    map.get(nums[0])+"\t"+map.get(nums[3])+"\t"+nums[7]+"\t";
//            rs+=nums[1]+"\t"+nums[2]+"\t"+nums[4]+"\t"+nums[5]+"\n";
//            String p=nums[3];
//            String dui1=p.split(":")[0].substring(1);
//            String dui2=p.split(":")[1];
//            dui2=dui2.substring(0,dui2.length()-2);
//            rs+=dui1.split("-")[0]+"\t";
//            rs+=dui1.split("-")[1]+"\t";
//            rs+=dui2.split("-")[0]+"\t";
//            rs+=dui2.split("-")[1]+"\n";




//            String rs=
//                    map.get(nums[0])+"\t"+map.get(nums[2])+"\t"+nums[4]+"\t"+nums[6]+'\t'+
//                            nums[7]+"\t"+nums[8]+'\t'+nums[9]+"\n";
//            outputStreamWriter.write(rs);
//            outputStreamWriter.flush();
    }
}
