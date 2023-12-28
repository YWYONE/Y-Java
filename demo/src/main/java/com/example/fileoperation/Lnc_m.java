package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

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

        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/lnc_m_1212.txt");
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
            lnctarnewmap.put(nums[0]+nums[1],bean2);
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
            rlbmap.put(nums[0]+nums[1],bean2);
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
                RIsearchmap.put(nums[0]+nums[3],bean2);
            }

        }

//        HashSet<String> guolv1=new HashSet<>();
//        HashSet<String> guolv2=new HashSet<>();
//        HashSet<String > guolv=new HashSet<>();
//        FileInputStream fileInputStreamguo=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/unionall35.csv");
//        Scanner scannerguo=new Scanner(fileInputStreamguo,"UTF-8");
//        scannerguo.nextLine();
//
//        while(scannerguo.hasNextLine()){
//            String line=scannerguo.nextLine();
//            String[]nums=line.split(",");
//
//            guolv1.add(nums[0]);
//            guolv2.add(nums[1]);
//
//
//        }
//        for(String k:guolv1){
//            for(String v:guolv2){
//                guolv.add(k+v);
//            }
//        }
//        for(String lnc:lnctarnewmap.keySet()){
//            String a=map.get(lnc.substring(0,15));
//            String b=map.get(lnc.substring(15,30));
//            String key=a+b;
//            if(!guolv.contains(key)){
//                lnctarnewmap.remove(lnc);
//            }
//        }
//        for(String lnc:rlbmap.keySet()){
//            String a=map.get(lnc.substring(0,15));
//            String b=map.get(lnc.substring(15,30));
//            String key=a+b;
//            if(!guolv.contains(key)){
//                rlbmap.remove(lnc);
//            }
//        }
//        for(String lnc:RIsearchmap.keySet()){
//            String a=map.get(lnc.substring(0,15));
//            String b=map.get(lnc.substring(15,30));
//            String key=a+b;
//            if(!guolv.contains(key)){
//                RIsearchmap.remove(lnc);
//            }
//        }
//
        FileInputStream resultfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/unionall35.csv");
        Scanner resultscanner=new Scanner(resultfileInputStream,"UTF-8");
        resultscanner.nextLine();
//        while(resultscanner.hasNextLine()) {
//            String line = resultscanner.nextLine();
//            String[] nums = line.split(",");
//            String key = nums[0] + nums[1];
//            if(!lnctarnewmap.containsKey(key)){
//                lnctarnewmap.remove(key);
//            }
//            if(!rlbmap.containsKey(key)){
//                rlbmap.remove(key);
//            }
//            if(!RIsearchmap.containsKey(key)){
//                RIsearchmap.remove(key);
//            }
//        }



        HashMap<String ,Bean2 > lnc_lastmap=new HashMap<>();
        HashMap<String ,Bean2 > rlb_lastmap=new HashMap<>();
        HashMap<String,Bean2> RIlastmap=new HashMap<>();


        HashMap<String ,Bean2 > lncbirlb_lnc_lrnamap=new HashMap<>();
        HashMap<String ,Bean2 > lncbirlb_rlb_lrnanewmap=new HashMap<>();
        for(String lnckey:lnctarnewmap.keySet()){
            if(rlbmap.containsKey(lnckey)){
                Bean2 bean1=lnctarnewmap.get(lnckey);
                Bean2 bean2=rlbmap.get(lnckey);
                double lncend1=Double.parseDouble(bean1.lncend);
                double lncstart1=Double.parseDouble(bean1.lncstart);
                double lncend2=Double.parseDouble(bean2.lncend);
                double lncstart2=Double.parseDouble(bean2.lncstart);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    lncbirlb_lnc_lrnamap.put(lnckey,bean1);
                    lncbirlb_rlb_lrnanewmap.put(lnckey,bean2);
                }
            }
        }
        HashMap<String ,Bean2 > lncbirlb_lnc_mrnamap=new HashMap<>();
        HashMap<String ,Bean2 > lncbirlb_rlb_mrnanewmap=new HashMap<>();
        for(String lnckey:lnctarnewmap.keySet()){
            if(rlbmap.containsKey(lnckey)){
                Bean2 bean1=lnctarnewmap.get(lnckey);
                Bean2 bean2=rlbmap.get(lnckey);
                double lncend1=Double.parseDouble(bean1.mend);
                double lncstart1=Double.parseDouble(bean1.mstart);
                double lncend2=Double.parseDouble(bean2.mend);
                double lncstart2=Double.parseDouble(bean2.mstart);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    lncbirlb_lnc_mrnamap.put(lnckey,bean1);
                    lncbirlb_rlb_mrnanewmap.put(lnckey,bean2);
                }
            }
        }
        for(String lnckey:lncbirlb_lnc_lrnamap.keySet()){
            if(lncbirlb_lnc_mrnamap.containsKey(lnckey)){
                Bean2 bean1=lncbirlb_lnc_lrnamap.get(lnckey);
                Bean2 bean2=lncbirlb_lnc_mrnamap.get(lnckey);
                if(bean1.lncend.equals(bean2.lncend)&&
                        bean1.lncstart.equals(bean2.lncstart)&&
                        bean1.mend.equals(bean2.mend)&&
                        bean1.mstart.equals(bean2.mstart)&&
                        bean1.energy.equals(bean2.energy)
                ){
                    Bean2 bean11=lncbirlb_rlb_lrnanewmap.get(lnckey);
                    Bean2 bean22=lncbirlb_rlb_mrnanewmap.get(lnckey);
                    if(bean11.lncend.equals(bean22.lncend)&&
                            bean11.lncstart.equals(bean22.lncstart)&&
                            bean11.mend.equals(bean22.mend)&&
                            bean11.mstart.equals(bean22.mstart)&&
                            bean11.energy.equals(bean22.energy)
                    ){
                        lnc_lastmap.put(lnckey,bean1);
                        rlb_lastmap.put(lnckey,bean11);
                    }
                }
            }
        }

        //
        HashMap<String ,Bean2 > lncbirI_lnc_lrnamap=new HashMap<>();
        HashMap<String ,Bean2 > lncbirI_rlb_lrnanewmap=new HashMap<>();
        for(String lnckey:lnctarnewmap.keySet()){
            if(RIsearchmap.containsKey(lnckey)){
                Bean2 bean1=lnctarnewmap.get(lnckey);
                Bean2 bean2=RIsearchmap.get(lnckey);
                double lncend1=Double.parseDouble(bean1.lncend);
                double lncstart1=Double.parseDouble(bean1.lncstart);
                double lncend2=Double.parseDouble(bean2.lncend);
                double lncstart2=Double.parseDouble(bean2.lncstart);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    lncbirI_lnc_lrnamap.put(lnckey,bean1);
                    lncbirI_rlb_lrnanewmap.put(lnckey,bean2);
                }
            }
        }
        HashMap<String ,Bean2 > lncbirI_lnc_mrnamap=new HashMap<>();
        HashMap<String ,Bean2 > lncbirI_rlb_mrnanewmap=new HashMap<>();
        for(String lnckey:lnctarnewmap.keySet()){
            if(RIsearchmap.containsKey(lnckey)){
                Bean2 bean1=lnctarnewmap.get(lnckey);
                Bean2 bean2=RIsearchmap.get(lnckey);
                double lncend1=Double.parseDouble(bean1.mend);
                double lncstart1=Double.parseDouble(bean1.mstart);
                double lncend2=Double.parseDouble(bean2.mend);
                double lncstart2=Double.parseDouble(bean2.mstart);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    lncbirI_lnc_mrnamap.put(lnckey,bean1);
                    lncbirI_rlb_mrnanewmap.put(lnckey,bean2);
                }
            }
        }
        for(String lnckey:lncbirI_lnc_lrnamap.keySet()){
            if(lncbirI_lnc_mrnamap.containsKey(lnckey)){
                Bean2 bean1=lncbirI_lnc_lrnamap.get(lnckey);
                Bean2 bean2=lncbirI_lnc_mrnamap.get(lnckey);
                if(bean1.lncend.equals(bean2.lncend)&&
                        bean1.lncstart.equals(bean2.lncstart)&&
                        bean1.mend.equals(bean2.mend)&&
                        bean1.mstart.equals(bean2.mstart)&&
                        bean1.energy.equals(bean2.energy)
                ){
                    Bean2 bean11=lncbirI_rlb_lrnanewmap.get(lnckey);
                    Bean2 bean22=lncbirI_rlb_mrnanewmap.get(lnckey);
                    if(bean11.lncend.equals(bean22.lncend)&&
                            bean11.lncstart.equals(bean22.lncstart)&&
                            bean11.mend.equals(bean22.mend)&&
                            bean11.mstart.equals(bean22.mstart)&&
                            bean11.energy.equals(bean22.energy)
                    ){
                        lnc_lastmap.put(lnckey,bean1);
                        RIlastmap.put(lnckey,bean11);
                    }
                }
            }
        }

        //
        HashMap<String ,Bean2 > rlbbirI_lnc_lrnamap=new HashMap<>();
        HashMap<String ,Bean2 > rlbbirI_rlb_lrnanewmap=new HashMap<>();
        for(String lnckey:rlbmap.keySet()){
            if(RIsearchmap.containsKey(lnckey)){
                Bean2 bean1=rlbmap.get(lnckey);
                Bean2 bean2=RIsearchmap.get(lnckey);
                double lncend1=Double.parseDouble(bean1.lncend);
                double lncstart1=Double.parseDouble(bean1.lncstart);
                double lncend2=Double.parseDouble(bean2.lncend);
                double lncstart2=Double.parseDouble(bean2.lncstart);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    rlbbirI_lnc_lrnamap.put(lnckey,bean1);
                    rlbbirI_rlb_lrnanewmap.put(lnckey,bean2);
                }
            }
        }
        HashMap<String ,Bean2 > rlbbirI_lnc_mrnamap=new HashMap<>();
        HashMap<String ,Bean2 > rlbbirI_rlb_mrnanewmap=new HashMap<>();
        for(String lnckey:rlbmap.keySet()){
            if(RIsearchmap.containsKey(lnckey)){
                Bean2 bean1=rlbmap.get(lnckey);
                Bean2 bean2=RIsearchmap.get(lnckey);
                double lncend1=Double.parseDouble(bean1.mend);
                double lncstart1=Double.parseDouble(bean1.mstart);
                double lncend2=Double.parseDouble(bean2.mend);
                double lncstart2=Double.parseDouble(bean2.mstart);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    rlbbirI_lnc_mrnamap.put(lnckey,bean1);
                    rlbbirI_rlb_mrnanewmap.put(lnckey,bean2);
                }
            }
        }
        for(String lnckey:rlbbirI_lnc_lrnamap.keySet()){
            if(rlbbirI_lnc_mrnamap.containsKey(lnckey)){
                Bean2 bean1=rlbbirI_lnc_lrnamap.get(lnckey);
                Bean2 bean2=rlbbirI_lnc_mrnamap.get(lnckey);
                if(bean1.lncend.equals(bean2.lncend)&&
                        bean1.lncstart.equals(bean2.lncstart)&&
                        bean1.mend.equals(bean2.mend)&&
                        bean1.mstart.equals(bean2.mstart)&&
                        bean1.energy.equals(bean2.energy)
                ){
                    Bean2 bean11=rlbbirI_rlb_lrnanewmap.get(lnckey);
                    Bean2 bean22=rlbbirI_rlb_mrnanewmap.get(lnckey);
                    if(bean11.lncend.equals(bean22.lncend)&&
                            bean11.lncstart.equals(bean22.lncstart)&&
                            bean11.mend.equals(bean22.mend)&&
                            bean11.mstart.equals(bean22.mstart)&&
                            bean11.energy.equals(bean22.energy)
                    ){
                        rlb_lastmap.put(lnckey,bean1);
                        RIlastmap.put(lnckey,bean11);
                    }
                }
            }
        }
//        HashMap<String ,Bean2 > map1=new HashMap<>();
//        HashMap<String ,Bean2 > map2=new HashMap<>();
//        HashMap<String ,Bean2 > map3=new HashMap<>();

//        for(String lnc:lnc_lastmap.keySet()){
//            String a=map.get(lnc.substring(0,15));
//            String b=map.get(lnc.substring(15,30));
//            String key=a+b;
//            System.out.println(key);
//            map1.put(key,lnc_lastmap.get(lnc));
//        }
//        for(String lnc:rlb_lastmap.keySet()){
//            String a=map.get(lnc.substring(0,15));
//            String b=map.get(lnc.substring(15,30));
//            String key=a+b;
//            map2.put(key,rlb_lastmap.get(lnc));
//        }
//        for(String lnc:RIlastmap.keySet()){
//            String a=map.get(lnc.substring(0,15));
//            String b=map.get(lnc.substring(15,30));
//            String key=a+b;
//            map3.put(key,RIlastmap.get(lnc));
//        }

        HashMap<String ,Bea2> hebing=new HashMap<>();
        for(String key:lnc_lastmap.keySet()){
            Bea2 bea=new Bea2();
            bea.bean1=lnc_lastmap.get(key);
            if(rlb_lastmap.containsKey(key)){
                bea.bean2=rlb_lastmap.get(key);
            }
            if(RIlastmap.containsKey(key)){
                bea.bean3=RIlastmap.get(key);
            }
            hebing.put(key,bea);
        }
        for(String key:rlb_lastmap.keySet()){
            if(!hebing.containsKey(key)){
                Bea2 bea=new Bea2();
                bea.bean2=rlb_lastmap.get(key);
                if(RIlastmap.containsKey(key)){
                    bea.bean3=RIlastmap.get(key);
                }
                hebing.put(key,bea);
            }
        }

        for(String key1:hebing.keySet()){
            //String line=resultscanner.nextLine();
            //String[]nums=line.split("\t");
            //String key=nums[0]+nums[1];
            String []nums=new String[2];
            nums[0]=map.get(key1.substring(0,15));
            nums[1]=map.get(key1.substring(15,30));
            String key=key1;
            Bea2 bea=hebing.get(key1);
            String a=nums[0];
            String b=nums[1];
            FileInputStream resultfileInputStream11=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/demrna_top35.txt");
            Scanner resultscanner11=new Scanner(resultfileInputStream11,"UTF-8");
            boolean isyou=false;
            while(resultscanner11.hasNextLine()){
                String  line1=resultscanner11.nextLine();
                line1=line1.substring(1,line1.length()-1);
                if(b.equals(line1)){
                    isyou=true;
                    break;
                }
            }
            if(isyou==false){
                continue;
            }
            resultfileInputStream11.close();
            FileInputStream resultfileInputStream1=new FileInputStream("/Users/yangweiyi/Desktop/lncRNA-mRNA/delncrna35.txt");
            Scanner resultscanner1=new Scanner(resultfileInputStream1,"UTF-8");
            boolean isyou1=false;
            while(resultscanner1.hasNextLine()){
                String  line1=resultscanner1.nextLine();
                line1=line1.substring(1,line1.length()-1);
                System.out.println(line1);
                if(a.equals(line1)){
                    isyou1=true;
                    break;
                }
            }
            if(isyou1==false){
                continue;
            }
            resultfileInputStream1.close();
            Bean2 bean1=bea.bean1;
            Bean2 bean2=bea.bean2;
            Bean2 bean3=bea.bean3;
//        for(String x:guolv){
////            String line=resultscanner.nextLine();
////            String[]nums=line.split(",");
//            String []nums=new String[2];
//            nums[0]=x.substring(0,15);
//            nums[1]=x.substring(15,30);
//            String key=x;
//            Bean2 bean1=null;
//            Bean2 bean2=null;
//            Bean2 bean3=null;
//            for(String lnc:lnc_lastmap.keySet()){
//            String a=map.get(lnc.substring(0,15));
//            String b=map.get(lnc.substring(15,30));
//            String key1=a+b;
//            if(key1.equals(key)){
//                bean1=lnc_lastmap.get(lnc);
//                break;
//            }
//        }
//            for(String lnc:rlb_lastmap.keySet()){
//                String a=map.get(lnc.substring(0,15));
//                String b=map.get(lnc.substring(15,30));
//                String key1=a+b;
//                if(key1.equals(key)){
//                    bean2=rlb_lastmap.get(lnc);
//                    break;
//                }
//            }
//            for(String lnc:RIlastmap.keySet()){
//                String a=map.get(lnc.substring(0,15));
//                String b=map.get(lnc.substring(15,30));
//                String key1=a+b;
//                if(key1.equals(key)){
//                    bean3=RIlastmap.get(lnc);
//                    break;
//                }
//            }
            String rs=null;
            if(bean1==null&&bean2==null&&bean3==null){
                continue;
            }
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
