/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistik;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Character.isLetter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mejan
 */
public class RandomTextPrint {
    //constructor
    public RandomTextPrint(){
        letters = new HashMap<Character, Double>();
        diagram = new HashMap<String, Double>();
        tregram = new HashMap<String, Double>();
        totalL = 0;
        totalD = 0;
        totalT = 0;
    }
    //Read from file and put in Maps.
    public void readFromFile(String filePath) throws FileNotFoundException, IOException{
        //Create reader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(filePath),
                    Charset.forName("UTF-8")));
        
        //Save the chars for dia and tre Map.
        String toDia = "";
        String toTre = "";
        
        //Put input in.
        int c;
        
        //Make tmp maps for easyer sort.
        Map tmpLet = new HashMap<Character, Double>();
        Map tmpDia = new HashMap<String, Double>();
        Map tmpTre = new HashMap<String, Double>();
        //Read the reader loop
        while((c = reader.read()) != -1) {
            //convert int to char.
            char tmpChar = (char) Character.toLowerCase(c);
            //add to letter map.
            if(isLetter(tmpChar) || tmpChar == ' '){
                if(!tmpLet.containsKey(tmpChar)){
                    double tmp = 1;
                    tmpLet.put(tmpChar, tmp);
                    totalL += 1;
                } else{
                    tmpLet.put(tmpChar, tmpLet.get(tmpChar).hashCode() +1);
                    totalL += 1;
                }
                //add char to string.
                toDia += tmpChar;
                //Check so it is the right length.
                if(toDia.length() == 2){
                    if(!tmpDia.containsKey(toDia)){
                        double tmp = 1;
                        tmpDia.put(toDia, tmp);
                        totalD += 1;
                    } else{
                        tmpDia.put(toDia, tmpDia.get(toDia).hashCode()+1);
                        totalD += 1;
                    }
                    //clear String.
                    toDia = "";
                }
                //Add har to String
                toTre += tmpChar;
                //Check so it is the right length.
                if(toTre.length() == 3){
                    if(!tmpTre.containsKey(toTre)){
                        double tmp = 1;
                        tmpTre.put(toTre, tmp);
                        totalT += 1;
                    } else{
                        tmpTre.put(toTre, tmpTre.get(toTre).hashCode()+1);
                        totalT += 1;
                    }
                    //Clear String.
                    toTre = "";
                }
            }
        }
        letters = sortByValues((HashMap) tmpLet);
        diagram = sortByValues((HashMap) tmpDia);
        tregram = sortByValues((HashMap) tmpTre);
        //printMap(letters, 1);
        //printMap(diagram, 0);
        //printMap(tregram, 0);
    }
    
    //Round of a number.
    private double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    //Sort HashMap by Value.
    private static HashMap sortByValues(HashMap map){
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator(){
            @Override
            public int compare(Object o1, Object o2){
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();){
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
    
    //Print Map
    public void printMap(Map toPrint, int i){
        //Print the map and it's values.
        if(i == 0){
            Iterator<Map.Entry<String, Double>> entries = toPrint.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Double> entry = entries.next();
                System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
            }
        } else if (i == 1){
            Iterator<Map.Entry<Character, Double>> entries = toPrint.entrySet().iterator();
            while (entries.hasNext()){
                Map.Entry<Character, Double> entry = entries.next();
                System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
            }
        }
    }
    
    private Map letters;
    private Map diagram;
    private Map tregram;
    private int totalL;
    private int totalD;
    private int totalT;
}
