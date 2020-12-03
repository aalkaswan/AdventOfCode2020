package org;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day3 {

    public static String[] init() throws Exception {
        String[] res = new String[324];
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Day3"));
        try {
            for(int i = 0; i < 324; i++){
                res[i] = br.readLine();
            }
        } finally{
            br.close();
            return res;
        }
    }

    public static int path(String[] map, int right, int down){
        int x = 0;
        int y = 0;
        int path = 0;
        while (y < 323){
            if(x > 30) x = x % 31;
            if(map[y].charAt(x) == '#') path ++;
            x += right;
            y += down;
        }
        return path;
    }

    public static void main(String[] args){
        try{
            System.out.println(path(init(), 1, 1) * path(init(), 3, 1) *
                    path(init(), 5, 1) * path(init(), 7, 1) * (long) path(init(), 1, 2));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
