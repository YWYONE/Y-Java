package com.example.fileoperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class TxtOperation {

    public static void main(String[] args) throws Exception{
        HashMap<String,String> map1=new HashMap<>();
        HashMap<String,Integer> map2=new HashMap<String, Integer>();
        FileInputStream fileInputStream=new FileInputStream("");
        Scanner scanner=new Scanner(fileInputStream,"UTF-8");
        while(scanner.hasNextLine()){
            String line= scanner.nextLine();
            String[] nums=line.split(" ");
            String key=nums[2];
            int de=Integer.parseInt(nums[3]);
            if(map1.containsKey(key)){
                if(de>map2.get(key)){
                    map1.put(key,line);
                    map2.put(key,de);
                }
            }else{
                map1.put(key,line);
                map2.put(key,de);
            }
        }
        FileOutputStream fileOutputStream=new FileOutputStream("");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        for(String line:map1.values()){
            outputStreamWriter.write(line+"\n");
        }
    }
}
