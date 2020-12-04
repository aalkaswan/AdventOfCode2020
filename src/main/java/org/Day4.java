package org;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.google.common.base.Splitter;

public class Day4 {

    public static String[] fields = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    public static List<String> init() {
        ArrayList<String> res = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/Day4"));
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

    public static int count(){
        int res = 0;
        List<String> records = init();
        for(String rec : records){
            boolean contains = true;
            for(String field : fields){
                contains = rec.contains(field);
                if(!contains) break;
            }
            if(contains) res++;
        }
        return res;
    }

    // Most beautiful method ever written
    public static int isValid(){
        int res = 0;
        List<String> records = init();
        for(String rec : records){
            try{
                Map<String, String> map = Splitter.on(' ').withKeyValueSeparator(':').split(rec.replace("\r\n"," "));
                if(Integer.parseInt(map.get("byr")) <= 2020 && Integer.parseInt(map.get("byr")) >= 1920){
                    if(Integer.parseInt(map.get("iyr")) <= 2020 && Integer.parseInt(map.get("iyr")) >= 2010){
                        if(Integer.parseInt(map.get("eyr")) <= 2030 && Integer.parseInt(map.get("eyr")) >= 2020){
                            if(map.get("hcl").matches("[#][a-z0-9]{6}")){
                                if(map.get("ecl").matches("^(amb|blu|brn|gry|grn|hzl|oth)$")){
                                    if(map.get("pid").matches("[0-9]{9}")){
                                        if(map.get("hgt").matches("[0-9]{3}(cm)")){
                                            int i = Integer.parseInt(map.get("hgt").substring(0,3));
                                            if(i >= 150 && i<=193) res++;
                                        } else if (map.get("hgt").matches("[0-9]{2}(in)")){
                                            int i = Integer.parseInt(map.get("hgt").substring(0,2));
                                            if(i >= 59 && i<=76) res++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e){
                //Failed
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(isValid());
    }
}
