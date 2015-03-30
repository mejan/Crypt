/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
public class StatLetter {
    public StatLetter(String fileName){
        letters = new HashMap<String, Integer>();
        total=0;
        readFileName = fileName;
    }
    
    public void readFromFile(int i) throws FileNotFoundException, IOException{
        //Create a instream to read from.
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(readFileName),
                    Charset.forName("UTF-8")));
        //Read in to c.
        int c;
        
        String toMap ="";
        Map test = new HashMap<String, Integer>();
        //Check so that we are not trying to get NULL key to the map.
        if(i != 0){
            //reading text.
            while((c = reader.read()) != -1) {
                //Convert to char in UpperCase
                char tmp = (char) Character.toUpperCase(c);
                //Check so the char is a letter
                if (isLetter(tmp)){
                    //Combine letters in a string.
                    toMap += tmp;

                    //To see when we should do a map out of it.
                    if(i == toMap.length()){
                        //if we allready have a key with just change the value otherwise make new map node.
                        if(!test.containsKey(toMap)){
                            int newest = 1;
                            test.put(toMap, newest);
                        } else{
                            test.put(toMap, test.get(toMap).hashCode() +1);
                        }
                        //Reset the string when it been used.
                        toMap = "";
                    }
                    //say the total of added values.
                    total++;
                }
            }
            letters = sortByValues((HashMap) test);
        } else{
            System.out.println("It's is not possible to have a map with nothing as key.");
        }
    }
    
    public void printMap(){
        //Print the map and it's values.
        Iterator<Map.Entry<String, Integer>> entries = letters.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
        }
    }
    
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
    
    //Will be change from void.
    public void printInFileLetterUseage(){
        Writer writer = null;
        Iterator<Map.Entry<String, Integer>> toString = letters.entrySet().iterator();
        String tmp = "";
        
        while(toString.hasNext()){
            Map.Entry<String, Integer> entry = toString.next();
            tmp += entry.getKey() + " ";
            double tmpProc = round((double) entry.getValue()/total, 3);
            tmp += " Har i decimalform: ";
            tmp += tmpProc + " Procent.\n";
        }
        
        try{
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/Result.txt"), "utf-8"));
                writer.write(tmp);
        } catch (IOException ex){
            System.out.println(ex.toString());
        } finally{
            try {writer.close();} catch (Exception ex){}
        }
    }
    
    //Round of a number.
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    //Store letters in map.
    private Map letters;
    //total nummber of read elements.
    private int total;
    //read from file
    String readFileName;
}
