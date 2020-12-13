package org;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13 {

    public static int calc1(){
        ArrayList<Integer> lines = new ArrayList<>();
        int timeStamp = 0;
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day13"));
            sc.useDelimiter("\r");
            timeStamp = Integer.parseInt(sc.next().trim());
            sc.useDelimiter(",");
            while(sc.hasNext()){
                String s = sc.next().trim();
                if(!s.contains("x"))lines.add(Integer.parseInt(s));
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        for(int i = timeStamp; i < Integer.MAX_VALUE; i++){
            for(int bus: lines){
                if(i%bus == 0) return (i-timeStamp) * bus;
            }
        }
        return -1;
    }

    public static long calc2(){
        ArrayList<Integer> lines = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day13"));
            sc.useDelimiter("\r");
            sc.next(); //Toss first line
            sc.useDelimiter(",");
            while(sc.hasNext()){
                String s = sc.next().trim();
                if(!s.contains("x"))lines.add(Integer.parseInt(s));
                else lines.add(-1);
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        long period = lines.get(0);
        long t = 0;
        long offset = 0;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i) == -1) continue;
            offset = 0;
            while(true){
                if((i + t) % lines.get(i) == 0){
                    if(offset == 0){
                        offset = t;
                    } else {
                        period = t - offset;
                        break;
                    }
                }
                t+= period;
            }
        }
        return offset;
    }

    public static void main(String[] args){
        System.out.println(calc1());
        System.out.println(calc2());
    }

}
