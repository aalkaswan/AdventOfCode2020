package org;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day9 {

    public static String[] init(){
        try{
            return new Scanner(new FileInputStream("src/main/resources/Day9")).useDelimiter("\\Z").next().split("\r");
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static int find(String[] input){
        for(int i = 25; i < input.length; i++){
            if(!isValid(i, input)) return Integer.parseInt(input[i].trim());
        }
        return -1;
    }
    public static boolean isValid(int i, String[] input){
        for(int j = i -25; j < i; j++){
            for(int k = i - 25; k < i; k++){
                if(j != k && Integer.parseInt(input[j].trim()) + Integer.parseInt(input[k].trim()) == Integer.parseInt(input[i].trim())){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static int findWeakness(String[] input){
        int invalid = find(input);
        ArrayList<Integer> ints = new ArrayList<>();
        for(int i = 0; i < input.length; i++){
            ints.clear();
            ints.add(Integer.parseInt(input[i].trim()));
            for(int j = i+1; j < input.length; j++){
                int current = Integer.parseInt(input[j].trim());
                ints.add(current);
                if(ints.stream().mapToInt(a -> a).sum() == invalid){
                    Collections.sort(ints);
                    return ints.get(0) + ints.get(ints.size()-1);
                }
                if(ints.stream().mapToInt(a -> a).sum() > invalid) break;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        //System.out.println(find(init()));
        System.out.println(findWeakness(init()));
    }

}
