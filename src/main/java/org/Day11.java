package org;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Day11 {

    public static char[][] init(){
        char[][] res = new char[99][95];
        try{
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day11"));
            sc.useDelimiter("");
            for(int i = 0; i <res.length ; i++){
                for(int j = 0; j <res[0].length ; j++){
                    res[i][j] = sc.next().charAt(0);
                }
                if(i != res.length-1) sc.nextLine();
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public static int run(char[][] input){
        char[][] oldboard = input;
        char[][] newboard;
        for(int i = 0; i < 100; i++){
            newboard = Arrays.stream(oldboard).map(char[]::clone).toArray(char[][]::new);
            //add new seats
            for(int j = 0; j < input.length; j++){
                for(int k = 0; k < input[0].length; k++){
                    if(oldboard[j][k] == 'L' && noAdj(j,k,oldboard)) newboard[j][k] = '#';
                }
            }
            oldboard = newboard;
            newboard = Arrays.stream(oldboard).map(char[]::clone).toArray(char[][]::new);
            // remove seats;
            for(int j = 0; j < input.length; j++){
                for(int k = 0; k < input[0].length; k++){
                    if(oldboard[j][k] == '#' && cantSeat(j,k,oldboard)) newboard[j][k] = 'L';
                }
            }
            oldboard = newboard;
        }
        int count = 0;
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                if(oldboard[i][j] == '#') count++;
            }
        }
        return count;
    }

    private static boolean cantSeat(int i, int j, char[][] oldboard){
        int count = 0;
        int[][] dir = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        for(int[] d : dir){
            for(int k = 1; k < oldboard.length; k++){
                if(i + d[0]*k >= 0 && i + d[0]*k < oldboard.length && j + d[1]*k >= 0 &&
                        j + d[1]*k < oldboard[0].length){
                    if(oldboard[i + d[0]*k][j + d[1]*k] == '#'){
                        count ++;
                        break;
                    }
                    if(oldboard[i + d[0]*k][j + d[1]*k] == 'L') break;
                } else break;
            }
        }
        return count > 4;
    }

    public static void print(char[][] newboard){
        for(int j = 0; j < newboard.length; j++){
            for(int k = 0; k < newboard[0].length; k++){
                System.out.print(newboard[j][k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean noAdj(int i, int j, char[][] oldboard){
        int[][] dir = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        for(int[] d : dir){
            for(int k = 1; k < oldboard.length; k++){
                if(i + d[0]*k >= 0 && i + d[0]*k < oldboard.length && j + d[1]*k >= 0 &&
                        j + d[1]*k < oldboard[0].length){
                    if(oldboard[i + d[0]*k][j + d[1]*k] == '#'){
                        return false;
                    }
                    if(oldboard[i + d[0]*k][j + d[1]*k] == 'L') break;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(run(init()));
    }
}
