package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Pcc {
    public static void main(String[] args) throws  Exception{
        String reg="\t";
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/yangweiyi/Desktop/pcc.txt");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        String title="Symbol1"+"\t";
        title+="Type1"+"\t";
        title+="Symbol2"+"\t";
        title+="Type2"+"\t";
        title+="Pearson"+"\n";
        outputStreamWriter.write(title);

        FileInputStream stream1=new FileInputStream("/Users/yangweiyi/Desktop/PCC/cislast.txt");
        Scanner scanner1=new Scanner(stream1,"UTF-8");
        scanner1.nextLine();
        while(scanner1.hasNextLine()){
            String line=scanner1.nextLine();
            String[]nums=line.split("\t");
            String rs=null;
            rs=nums[2]+reg;
            rs+="lncRNA"+reg;
            rs+=nums[1]+reg;
            rs+="mRNA"+reg;
            rs+=nums[0]+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }
        FileInputStream stream2=new FileInputStream("/Users/yangweiyi/Desktop/PCC/milnclast.txt");
        Scanner scanner2=new Scanner(stream2,"UTF-8");
        scanner2.nextLine();
        while(scanner2.hasNextLine()){
            String line=scanner2.nextLine();
            String[]nums=line.split("\t");
            String rs=null;
            rs=nums[0]+reg;
            rs+="miRNA"+reg;
            rs+=nums[2]+reg;
            rs+="lncRNA"+reg;
            rs+=nums[1]+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }
        FileInputStream stream3=new FileInputStream("/Users/yangweiyi/Desktop/PCC/mimlast.txt");
        Scanner scanner3=new Scanner(stream3,"UTF-8");
        scanner3.nextLine();
        while(scanner3.hasNextLine()){
            String line=scanner3.nextLine();
            String[]nums=line.split("\t");
            String rs=null;
            rs=nums[0]+reg;
            rs+="miRNA"+reg;
            rs+=nums[2]+reg;
            rs+="mRNA"+reg;
            rs+=nums[1]+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }
        FileInputStream stream4=new FileInputStream("/Users/yangweiyi/Desktop/PCC/translast.txt");
        Scanner scanner4=new Scanner(stream4,"UTF-8");
        scanner4.nextLine();
        while(scanner4.hasNextLine()){
            String line=scanner4.nextLine();
            String[]nums=line.split("\t");
            String rs=null;
            rs=nums[2]+reg;
            rs+="lncRNA"+reg;
            rs+=nums[1]+reg;
            rs+="mRNA"+reg;
            rs+=nums[0]+"\n";
            outputStreamWriter.write(rs);
            outputStreamWriter.flush();
        }


    }
}
