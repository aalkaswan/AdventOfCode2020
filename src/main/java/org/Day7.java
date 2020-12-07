package org;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day7 {

    public static int find(String f, Set<String> res){
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day7"));
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                if(s.contains(f) && !s.startsWith(f)){
                    String[] list = s.split(" ");
                    String container = list[0] + ' ' + list[1];
                    if(!res.contains(container)){
                        res.add(container);
                        find(container, res);
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res.size();
    }

    public static int count(String f){
        int res = 0;
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day7"));
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                if(s.contains("no other bags")) continue;
                if(s.startsWith(f)){
                    String[] list = s.split(" ");
                    for(int i = 4; i < list.length; i=i+4){
                        res += Integer.parseInt(list[i]) * (count(list[i+1] + " " + list[i+2]) + 1);

                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args){
        Set<String> res = new HashSet<>();
        System.out.println(find("shiny gold", res));
        System.out.println(count("shiny gold"));
    }
}
