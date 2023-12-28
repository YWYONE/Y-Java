package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Test1 {
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
//        map.keySet().forEach(key->{
//            String  value=map.get(key);
//            System.out.println(value+"  "+key);
//        });
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/mRNA_1210.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        FileInputStream mirandafileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-mRNA/miranda.txt");
        Scanner mirandascanner=new Scanner(mirandafileInputStream,"UTF-8");
        HashMap<String ,Bean1> mirandamap=new HashMap<>();
        while(mirandascanner.hasNextLine()){
            String line=mirandascanner.nextLine();
            String[]nums=line.split("\t");
            String mirna=nums[0];
            String mrna=nums[1];
            mirna=mirna.trim().substring(1);
           // mrna=map.get(mrna.trim().substring(18));
            mrna=mrna.trim().substring(18);
            //System.out.println(mirna+"\t"+mrna);
            Bean1 bean1=new Bean1();
            bean1.energy=nums[3];
            String []startandEnd=nums[5].split(" ");
            bean1.start=startandEnd[0];
            bean1.end=startandEnd[1];
            mirandamap.put(mirna+mrna,bean1);
        }
        HashMap<String ,Bean1> pitaMap=new HashMap<>();
        FileInputStream pitafileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-mRNA/pita_results1115.txt");
        Scanner pitascanner=new Scanner(pitafileInputStream,"UTF-8");
        while(pitascanner.hasNextLine()){
            String line=pitascanner.nextLine();
            String[]nums=line.split("\t");

            String mirna=nums[1];
            String mrna=nums[0];
            //mrna=map.get(mrna.substring(18));
            mrna=mrna.substring(18);
            //System.out.println(mirna+"\t"+mrna);
            Bean1 bean1=new Bean1();
            bean1.energy=nums[4];
            bean1.start=nums[3];
            bean1.end=nums[2];
            pitaMap.put(mirna+mrna,bean1);
        }
        pitafileInputStream.close();
        HashMap<String,String> RNAhybridchuli=new HashMap<>();
        HashMap<String,Bean1> third=new HashMap<>();
        FileInputStream RNAhybridchulifileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-mRNA/RNAhybridchuli.txt");
        Scanner RNAhybridchuliscanner=new Scanner(RNAhybridchulifileInputStream,"UTF-8");
        while(RNAhybridchuliscanner.hasNextLine()){
            String line=RNAhybridchuliscanner.nextLine();
            String[]nums=line.split(":");
            String hsa=nums[3];
            String st=nums[1];
            String energy=nums[5];
            RNAhybridchuli.put(hsa+st,energy);

        }
        FileInputStream thirdfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-mRNA/RNAhybrid.txt");
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
            third.put(hsa+st,bean1);
        }
//        FileInputStream resultfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-mRNA/mi-mlastresult.txt");
//        Scanner resultscanner=new Scanner(resultfileInputStream,"UTF-8");
//        String  line1=resultscanner.nextLine();
        String title="mirna"+"\t"+"mrna"+"\t";
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

        HashMap<String ,Bean1 > lnc_lastmap=new HashMap<>();//mirandamap
        HashMap<String ,Bean1 > rlb_lastmap=new HashMap<>();//pitaMap
        HashMap<String,Bean1> RIlastmap=new HashMap<>();//third

        HashMap<String ,Bea> hebing=new HashMap<>();


        HashMap<String ,Bean1 > lncbirlb_lnc_lrnamap=new HashMap<>();
        HashMap<String ,Bean1 > lncbirlb_rlb_lrnanewmap=new HashMap<>();
        for(String lnckey:mirandamap.keySet()){
            if(pitaMap.containsKey(lnckey)){
                Bean1 bean1=mirandamap.get(lnckey);
                Bean1 bean2=pitaMap.get(lnckey);
                double lncend1=Double.parseDouble(bean1.end);
                double lncstart1=Double.parseDouble(bean1.start);
                double lncend2=Double.parseDouble(bean2.end);
                double lncstart2=Double.parseDouble(bean2.start);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    lncbirlb_lnc_lrnamap.put(lnckey,bean1);
                    lncbirlb_rlb_lrnanewmap.put(lnckey,bean2);
                }
            }
        }
        HashMap<String ,Bean1 > lncbirlb_lnc_mrnamap=new HashMap<>();
        HashMap<String ,Bean1 > lncbirlb_rlb_mrnanewmap=new HashMap<>();
        for(String lnckey:mirandamap.keySet()){
            if(pitaMap.containsKey(lnckey)){
                Bean1 bean1=mirandamap.get(lnckey);
                Bean1 bean2=pitaMap.get(lnckey);
                double lncend1=Double.parseDouble(bean1.end);
                double lncstart1=Double.parseDouble(bean1.start);
                double lncend2=Double.parseDouble(bean2.end);
                double lncstart2=Double.parseDouble(bean2.start);

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
                Bean1 bean1=lncbirlb_lnc_lrnamap.get(lnckey);
                Bean1 bean2=lncbirlb_lnc_mrnamap.get(lnckey);
                if(bean1.end.equals(bean2.end)&&
                        bean1.start.equals(bean2.start)&&
                        bean1.end.equals(bean2.end)&&
                        bean1.start.equals(bean2.start)&&
                        bean1.energy.equals(bean2.energy)
                ){
                    Bean1 bean11=lncbirlb_rlb_lrnanewmap.get(lnckey);
                    Bean1 bean22=lncbirlb_rlb_mrnanewmap.get(lnckey);
                    if(bean11.end.equals(bean22.end)&&
                            bean11.start.equals(bean22.start)&&
                            bean11.end.equals(bean22.end)&&
                            bean11.start.equals(bean22.start)&&
                            bean11.energy.equals(bean22.energy)
                    ){
                        lnc_lastmap.put(lnckey,bean1);
                        rlb_lastmap.put(lnckey,bean11);
                    }
                }
            }
        }



        HashMap<String ,Bean1 > lncbirI_lnc_lrnamap=new HashMap<>();
        HashMap<String ,Bean1 > lncbirI_rlb_lrnanewmap=new HashMap<>();
        for(String lnckey:mirandamap.keySet()){
            if(third.containsKey(lnckey)){
                Bean1 bean1=mirandamap.get(lnckey);
                Bean1 bean2=third.get(lnckey);
                double lncend1=Double.parseDouble(bean1.end);
                double lncstart1=Double.parseDouble(bean1.start);
                double lncend2=Double.parseDouble(bean2.end);
                double lncstart2=Double.parseDouble(bean2.start);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    lncbirI_lnc_lrnamap.put(lnckey,bean1);
                    lncbirI_rlb_lrnanewmap.put(lnckey,bean2);
                }
            }
        }
        HashMap<String ,Bean1 > lncbirI_lnc_mrnamap=new HashMap<>();
        HashMap<String ,Bean1 > lncbirI_rlb_mrnanewmap=new HashMap<>();
        for(String lnckey:mirandamap.keySet()){
            if(third.containsKey(lnckey)){
                Bean1 bean1=mirandamap.get(lnckey);
                Bean1 bean2=third.get(lnckey);
                double lncend1=Double.parseDouble(bean1.end);
                double lncstart1=Double.parseDouble(bean1.start);
                double lncend2=Double.parseDouble(bean2.end);
                double lncstart2=Double.parseDouble(bean2.start);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    lncbirI_lnc_mrnamap.put(lnckey,bean1);
                    lncbirI_rlb_mrnanewmap.put(lnckey,bean2);
                }
            }
        }
        for(String lnckey:lncbirI_lnc_lrnamap.keySet()) {
            if (lncbirI_lnc_mrnamap.containsKey(lnckey)) {
                Bean1 bean1 = lncbirI_lnc_lrnamap.get(lnckey);
                Bean1 bean2 = lncbirI_lnc_mrnamap.get(lnckey);
                if (bean1.end.equals(bean2.end) &&
                        bean1.start.equals(bean2.start) &&
                        bean1.end.equals(bean2.end) &&
                        bean1.start.equals(bean2.start) &&
                        bean1.energy.equals(bean2.energy)
                ) {
                    Bean1 bean11 = lncbirI_rlb_lrnanewmap.get(lnckey);
                    Bean1 bean22 = lncbirI_rlb_mrnanewmap.get(lnckey);
                    if (bean11.end.equals(bean22.end) &&
                            bean11.start.equals(bean22.start) &&
                            bean11.end.equals(bean22.end) &&
                            bean11.start.equals(bean22.start) &&
                            bean11.energy.equals(bean22.energy)
                    ) {
                        lnc_lastmap.put(lnckey, bean1);
                        RIlastmap.put(lnckey, bean11);
                    }
                }
            }
        }



        HashMap<String ,Bean1 > rlbbirI_lnc_lrnamap=new HashMap<>();
        HashMap<String ,Bean1 > rlbbirI_rlb_lrnanewmap=new HashMap<>();
        for(String lnckey:pitaMap.keySet()){
            if(third.containsKey(lnckey)){
                Bean1 bean1=pitaMap.get(lnckey);
                Bean1 bean2=third.get(lnckey);
                double lncend1=Double.parseDouble(bean1.end);
                double lncstart1=Double.parseDouble(bean1.start);
                double lncend2=Double.parseDouble(bean2.end);
                double lncstart2=Double.parseDouble(bean2.start);

                double bi1=(lncend1-lncstart2)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                double bi2= (lncend2-lncstart1)/ (((lncend1-lncstart1)+(lncend2-lncstart2))/2.0);
                if( (bi1>0.5&&bi1<1) ||(bi2<1&&bi2>0.5)){
                    rlbbirI_lnc_lrnamap.put(lnckey,bean1);
                    rlbbirI_rlb_lrnanewmap.put(lnckey,bean2);
                }
            }
        }
        HashMap<String ,Bean1 > rlbbirI_lnc_mrnamap=new HashMap<>();
        HashMap<String ,Bean1 > rlbbirI_rlb_mrnanewmap=new HashMap<>();
        for(String lnckey:pitaMap.keySet()){
            if(third.containsKey(lnckey)){
                Bean1 bean1=pitaMap.get(lnckey);
                Bean1 bean2=third.get(lnckey);
                double lncend1=Double.parseDouble(bean1.end);
                double lncstart1=Double.parseDouble(bean1.start);
                double lncend2=Double.parseDouble(bean2.end);
                double lncstart2=Double.parseDouble(bean2.start);

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
                Bean1 bean1=rlbbirI_lnc_lrnamap.get(lnckey);
                Bean1 bean2=rlbbirI_lnc_mrnamap.get(lnckey);
                if(bean1.end.equals(bean2.end)&&
                        bean1.start.equals(bean2.start)&&
                        bean1.end.equals(bean2.end)&&
                        bean1.start.equals(bean2.start)&&
                        bean1.energy.equals(bean2.energy)
                ){
                    Bean1 bean11=rlbbirI_rlb_lrnanewmap.get(lnckey);
                    Bean1 bean22=rlbbirI_rlb_mrnanewmap.get(lnckey);
                    if(bean11.end.equals(bean22.end)&&
                            bean11.start.equals(bean22.start)&&
                            bean11.end.equals(bean22.end)&&
                            bean11.start.equals(bean22.start)&&
                            bean11.energy.equals(bean22.energy)
                    ){
                        rlb_lastmap.put(lnckey,bean1);
                        RIlastmap.put(lnckey,bean11);
                    }
                }
            }
        }

        for(String key:lnc_lastmap.keySet()){
            Bea bea=new Bea();
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
                Bea bea=new Bea();
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
            Bea bea=hebing.get(key1);
            String a=key1.substring(0,key1.length()-15);
            String b=map.get(key1.substring(key1.length()-15,key1.length()));
            FileInputStream resultfileInputStream=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-lncRNA/demirna.txt");
            Scanner resultscanner=new Scanner(resultfileInputStream,"UTF-8");
            boolean isyou=false;
            while(resultscanner.hasNextLine()){
                String  line1=resultscanner.nextLine();
                if(a.equals(line1)){
                    isyou=true;
                    break;
                }
            }
            resultfileInputStream.close();
            if(isyou==false){
                continue;
            }

            resultfileInputStream.close();
            FileInputStream resultfileInputStream1=new FileInputStream("/Users/yangweiyi/Desktop/miRNA-mRNA/demrna.txt");
            Scanner resultscanner1=new Scanner(resultfileInputStream1,"UTF-8");
            boolean isyou1=false;
            while(resultscanner1.hasNextLine()){
                String  line1=resultscanner1.nextLine();
                line1=line1.substring(1,line1.length()-1);
                System.out.println(line1);
                if(b.equals(line1)){
                    isyou1=true;
                    break;
                }
            }
            if(isyou1==false){
                continue;
            }
            resultfileInputStream1.close();

            String key=a+b;
            String []nums=new String[2];
            nums[0]=a;
            nums[1]=b;



//            Bean1 bean1=mirandamap.get(key);
//            Bean1 bean2=pitaMap.get(key);
//            Bean1 bean3=third.get(key);
            Bean1 bean1=bea.bean1;
            Bean1 bean2=bea.bean2;
            Bean1 bean3=bea.bean3;
            if(bean1==null&&bean2==null&&bean3==null){
                continue;
            }


//        while(resultscanner.hasNextLine()){
//            String line=resultscanner.nextLine();
//            String[]nums=line.split("\t");
//            String key=nums[0]+nums[1];
//            Bean1 bean1=mirandamap.get(key);
//            Bean1 bean2=pitaMap.get(key);
//            Bean1 bean3=third.get(key);
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
