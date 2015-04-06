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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author mejan
 */
public class RandomTextPrint {
    //constructor
    public RandomTextPrint(String filePath) throws IOException{
        letters = new HashMap<Character, Double>();
        totalL = 0;
        randomGenerator = new Random();
        toFile = "";
        readFromFile(filePath);
        fillString();
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
            }
        }
        letters = sortByValues((HashMap) tmpLet);
        changeValue();
    }
    
    //Change Value
    private void changeValue(){
        Iterator<Map.Entry<Character, Double>> entries = letters.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Character, Double> entry = entries.next();
            Double tmp = (Double) letters.get(entry.getKey());
            tmp /= totalL;
            letters.put(entry.getKey(), tmp);
        }
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
    
    //Make to procent as value in letters.
    public void makeLettersProcent(){
        Iterator<Map.Entry<Character, Double>> entries = letters.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Character, Double> entry = entries.next();
            Double tmp = (Double) letters.get(entry.getKey());
            tmp /= totalL;
            tmp *= 100;
            letters.put(entry.getKey(), tmp);
        }
    }
    
    //Print Map
    public void printMap(int i){
        Iterator<Map.Entry<Character, Double>> entries = letters.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Character, Double> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
        }
    }
    
    
    //How many of a certin letter is needed.
    private int rep(Double i){
        int tmp = (int) (i*2000);
        return  tmp;
    }
    
    //scramble Strings
    private String scramble(){
      if (toFile == null)
         return null;

      char[] arr = toFile.toCharArray();
      List<Character> charList = new ArrayList<Character>(arr.length);
      for (final char c : arr) {
         charList.add(c);
      }
        

      Collections.shuffle(charList, randomGenerator);
      char[] converted = new char[charList.size()];
      for (int i = 0; i < charList.size(); i++) {
         converted[i] = charList.get(i).charValue();
      }

      return new String(converted);
   }
    
   private void fillString(){
       Iterator<Map.Entry<Character, Double>> entries = letters.entrySet().iterator();
       while (entries.hasNext()){
           Map.Entry<Character, Double> entry = entries.next();
           int max=rep(entry.getValue());
           for(int i=0; i<max; i++){
               toFile += entry.getKey();
           }
       }
       toFile = scramble();
   }
   
   //Write a page in txt
   public void printRandomTextToFile(String fileName){
        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"));
                writer.write(toFile);
        } catch (IOException ex){
            System.out.println(ex.toString());
        } finally{
            try {writer.close();} catch (Exception ex){}
        }
   }
    
    private Map letters;
    private int totalL;
    private String toFile;
    private Random randomGenerator;
}
/*a = 9% gånger
2000 chars = 1 sida
0.09 * 2000 = antal gånger bokstaven a ska vara på 1 sida.
Array.push( a ) antal gånger från ovan
samma sak med alla bokstäver sen en shuffle sen printar jag allt*/