package org;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day8 {

    public static String[] init(){
        try{
            return new Scanner(new FileInputStream("src/main/resources/Day8")).useDelimiter("\\Z").next().split("\r");
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static int exec(String[] s){
        int acc = 0;
        Set<Integer> set = new HashSet<>();
            int i = 0;
            while(i < s.length){
                String comm = s[i];
                if(set.contains(i)){
                    break;
                }
                set.add(i);
                if(comm.contains("nop")) i++;
                else if(comm.contains("acc")){
                    acc += Integer.parseInt(comm.split(" ")[1]);
                    i++;
                } else if(comm.contains("jmp")){
                    i += Integer.parseInt(comm.split(" ")[1]);
                }
            }
        return acc;
    }
    public static int exec2(String[] s){
        for(int i = 0; i < s.length; i++){
            if(s[i].contains("nop")){
                s[i] = s[i].replace("nop", "jmp");
                if(terminates(s)){
                    return exec(s);
                }
                s[i] = s[i].replace("jmp", "nop");
            }
            if(s[i].contains("jmp")){
                s[i] = s[i].replace("jmp", "nop");
                if(terminates(s)){
                    return exec(s);
                }
                s[i] = s[i].replace("nop", "jmp");
            }
        }
        return 0;
    }

    public static boolean terminates(String[] s){
        Set<Integer> set = new HashSet<>();
        int i = 0;
        while(i < s.length){
            String comm = s[i];
            if(set.contains(i)){
                return false;
            }
            set.add(i);
            if(comm.contains("nop")) i++;
            else if(comm.contains("acc")){
                i++;
            } else if(comm.contains("jmp")){
                    i += Integer.parseInt(comm.split(" ")[1]);
                }
            }
        return true;
    }


    public static void main(String[] args){
        System.out.println(exec(init()));
        System.out.println(exec2(init()));
    }

}
