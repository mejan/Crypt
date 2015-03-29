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
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mejan
 */
public class Statistik {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Create a instream to read from.
        String file = null;
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream("/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/bibeln.txt"),
                    Charset.forName("UTF-8")));
        //read to c
        int c;
        //total nummber of read elements.
        int total = 0;
        //Change default language to swedish.
        Locale.setDefault(new Locale("sv"));
        //Map to calulate the number of reacurincy.
        Map<String, Integer> letters = new HashMap<String, Integer>();
        
        //Read user input 1 for 1 char, 2 for Diagram och 3 for triagram.
        System.out.println("var god och fyll i moduls värdet.");
        Scanner input = new Scanner(System.in);
        int i = 0;
        i = input.nextInt();
        
        String toMap ="";
        //Check so we arn't trying to devide by zero.
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
                    if(total%i==0){
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
            //Print the map and it's values.
            Iterator<Map.Entry<String, Integer>> entries = letters.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Integer> entry = entries.next();
                System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
            }
        } else{
            System.out.println("Not a valid argument.");
        }
    }
}