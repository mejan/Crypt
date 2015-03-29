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
import java.util.HashMap;
import java.util.Iterator;
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
                        if(!letters.containsKey(toMap)){
                            int newest = 1;
                            letters.put(toMap, newest);
                        } else{
                            letters.put(toMap, letters.get(toMap).hashCode() +1);
                        }
                        //Reset the string when it been used.
                        toMap = "";
                    }
                    //say the total of added values.
                    total++;
                }
            }
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
    
    //Will be change from void.
    public void printInFileLetterUseage(){
        
    }
    //Store letters in map.
    private Map letters;
    //total nummber of read elements.
    private int total;
    //read from file
    String readFileName;
}
