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
    public RandomTextPrint(String filePath) throws IOException{
        letters = new HashMap<Character, Double>();
        diagram = new HashMap<String, Double>();
        tregram = new HashMap<String, Double>();
        totalL = 0;
        totalD = 0;
        totalT = 0;
        readFromFile(filePath);
    }
    //Read from file and put in Maps.
    private void readFromFile(String filePath) throws FileNotFoundException, IOException{
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
                    Double tmp = (Double) tmpLet.get(tmpChar);
                    tmp += 1.0;
                    tmpLet.put(tmpChar, tmp);
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
                        Double tmp = (Double) tmpDia.get(toDia);
                        tmp += 1.0;
                        tmpDia.put(toDia, tmp);
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
                        Double tmp = (Double) tmpTre.get(toTre);
                        tmp += 1.0;
                        tmpTre.put(toTre, tmp);
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
    
    public void makeValueInProcent(){
        makeLettersProcent();
        makeDiagramProcent();
        makeTregramProcent();
    }
    
    //Make to procent as value in letters.
    private void makeLettersProcent(){
        Iterator<Map.Entry<Character, Double>> entries = letters.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Character, Double> entry = entries.next();
            Double tmp = (Double) letters.get(entry.getKey());
            tmp /= totalL;
            tmp *= 100;
            letters.put(entry.getKey(), tmp);
        }
    }
    
    //Diagram value to procent value.
    private void makeDiagramProcent(){
        Iterator<Map.Entry<String, Double>> entries = diagram.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<String, Double> entry = entries.next();
            Double tmp = (Double) diagram.get(entry.getKey());
            tmp /= totalD;
            tmp *= 100;
            diagram.put(entry.getKey(), tmp);
        }
    }
    
    //Tregram value to procent value.
    private void makeTregramProcent(){
        Iterator<Map.Entry<String, Double>> entries = tregram.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<String, Double> entry = entries.next();
            Double tmp = (Double) tregram.get(entry.getKey());
            tmp /= totalT;
            tmp *= 100;
            tregram.put(entry.getKey(), tmp);
        }
    }
    
    public void printAllMaps(){
        printMap(0);
        System.out.println(" ");
        printMap(1);
        System.out.println(" ");
        printMap(2);
    }
    
    //Print Map
    public void printMap(int i){
        //Print the map and it's values.
        if(i == 2){
            Iterator<Map.Entry<String, Double>> entries = tregram.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Double> entry = entries.next();
                System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
            }
        } else if (i == 0){
            Iterator<Map.Entry<Character, Double>> entries = letters.entrySet().iterator();
            while (entries.hasNext()){
                Map.Entry<Character, Double> entry = entries.next();
                System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
            }
        } else if (i == 1){
            Iterator<Map.Entry<String, Double>> entries = diagram.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Double> entry = entries.next();
                System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
            }
        } else{
            System.out.println("There is only maps 0, 1 or 2");
        }
    }
    
    private Map letters;
    private Map diagram;
    private Map tregram;
    private int totalL;
    private int totalD;
    private int totalT;
}