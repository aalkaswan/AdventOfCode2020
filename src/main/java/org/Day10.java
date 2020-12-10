package org;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Day10 {

    public static int[] init(){
        int[] res = new int[115];
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day10"));
            sc.useDelimiter("\r");
            int i = 0;
            while(sc.hasNext()){
                res[i] = (Integer.parseInt(sc.next().trim()));
                i++;
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        Arrays.sort(res);
        return res;
    }
    public static int diff(int[] input){
        int[] diffs = new int[4];
        for(int i = 1; i < input.length; i++){
            int diff = input[i] - input[i-1];
            if(diff > 3) System.out.println("fail");
            diffs[diff]++;
        }
        return (diffs[3] + 1)* (diffs[1] + 1);
    }

    public static long configs(int[] input){
        long[] len = new long[input.length];
        len[0] = 1;
        for(int i = 0; i < input.length; i++){
            for(int j = i-3; j < i; j++){
                if(j >= 0  && input[i] <= 3 + input[j]) len[i] += len[j];
            }
        }
        return len[len.length-1];
    }

    public static void main(String[] args){
        //System.out.println(diff(init()));
        System.out.println(configs(init()));
    }
}
