package org;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day5 {

    public static List<String> init(){
        ArrayList<String> res = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day5"));
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                res.add(sc.next().trim());
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static List<Integer> calcIds(List<String> input){
        ArrayList<Integer> res = new ArrayList<>();
        for(String s: input){
            int row = new BigInteger(s.substring(0,7).replace("F", "0").replace("B","1"), 2).intValueExact();
            int col = new BigInteger(s.substring(7,10).replace("L", "0").replace("R","1"), 2).intValueExact();
            res.add((row * 8 + col));
        }
        return res;
    }
    public static int findSeat(List<Integer> input){
        Collections.sort(input);
        for(int i = 0; i < input.size() - 1; i++){
            if(input.get(i) != input.get(i+1) -1) return input.get(i) + 1;
        }
        return 0;
    }

    public static void main(String[] args){
        System.out.println(Collections.max(calcIds(init())));
        System.out.println(findSeat(calcIds(init())));
    }
}
