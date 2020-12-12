package org;

import java.io.FileInputStream;
import java.util.Scanner;

public class Day12 {

    public static String[] init(){
        String[] res = new String[775];
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day12"));
            sc.useDelimiter("\r");
            for(int i = 0; i <res.length ; i++){
                res[i] = sc.next().trim();
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public static int calcDistance1(String[] input){
        int x = 0;
        int y = 0;
        int dir = 90;
        for(String s: input){
            switch(s.charAt(0)){
                case 'N':{
                    y += parse(s);
                    break;
                }
                case 'S': {
                    y -= parse(s);
                    break;
                }
                case 'E': {
                    x += parse(s);
                    break;
                }
                case 'W': {
                    x -= parse(s);
                    break;
                }
                case 'L' : {
                    dir = Math.floorMod(dir - parse(s), 360);
                    break;
                }
                case 'R' : {
                    dir = Math.floorMod(dir + parse(s), 360);
                    break;
                }
                case 'F' : {
                    if(dir == 0) y += parse(s);
                    if(dir == 90) x += parse(s);
                    if(dir == 180) y -= parse(s);
                    if(dir == 270) x -= parse(s);
                    break;
                }
                default: {
                    System.out.println("Fail");
                    break;
                }
            }
        }
        return Math.abs(x) + Math.abs(y);
    }


    public static int calcDistance2(String[] input){
        int x = 10;
        int y = 1;
        int px = 0;
        int py = 0;
        for(String s: input){
            switch(s.charAt(0)){
                case 'N':{
                    y += parse(s);
                    break;
                }
                case 'S': {
                    y -= parse(s);
                    break;
                }
                case 'E': {
                    x += parse(s);
                    break;
                }
                case 'W': {
                    x -= parse(s);
                    break;
                }
                case 'L' : {
                    for(int i = 0; i < parse(s)/90; i++){
                        int old = x;
                        x=-y;
                        y= old;
                    }
                    break;
                }
                case 'R' : {
                    for(int i = 0; i < parse(s)/90; i++){
                        int old = x;
                        x= y;
                        y= -old;
                    }
                    break;
                }
                case 'F' : {
                    px += x*parse(s);
                    py += y*parse(s);
                    break;
                }
                default: {
                    System.out.println("Fail");
                    break;
                }
            }
        }
        return Math.abs(px) + Math.abs(py);
    }


    public static int parse(String s) {return Integer.parseInt(s.substring(1));}

    public static void main(String[] args){
        System.out.println(calcDistance2(init()));
    }
}
