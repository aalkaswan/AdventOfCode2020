package org;

import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {

    public static List<String> init(){
        ArrayList<String> res = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day6"));
            sc.useDelimiter("\n\r");
            while(sc.hasNext()){
                res.add(sc.next().trim().replaceAll("\\r|\\n", ""));
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static int count(List<String> input){
        int res = 0;
        for(String s : input){
            res += s.chars().distinct().count();
        }
        return res;
    }

    public static List<String> init2(){
        ArrayList<String> res = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day6"));
            sc.useDelimiter("\n\r");
            while(sc.hasNext()){
                res.add(sc.next().trim());
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public static int count2(List<String> input){
        int res = 0;
        for(String s : input){
            String[] parts = s.split("\n");
            Set<Character> chars = new HashSet<>();
            chars.addAll(parts[0].trim().chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
            for(String p : parts){
                chars.retainAll(p.trim().chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
            }
            res += chars.size();
        }
        return res;
    }


    public static void main(String[] args){
        //System.out.println(count(init()));
        System.out.println(count2(init2()));
    }

}
