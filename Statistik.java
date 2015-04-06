/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistik;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
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
        //Change default language to swedish.
        Locale.setDefault(new Locale("sv"));
        
        
        int i = 1;
        //new object of class StatLetter, par is file name/location.
        StatLetter test= new StatLetter();
        //read file, for 1 letter.
        test.readFromFile(i, "/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/bibeln.txt");
        //print result of one letter.
        test.printInFileLetterUseage("/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/resultOneLetter.txt");
        i = 2;
        //Read file, for diagram.
        test.readFromFile(i, "/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/bibeln.txt");
        //Createfile for diagram.
        test.printInFileLetterUseage("/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/resultDiagram.txt");
        //read file, for treagram.
        i = 3;
        test.readFromFile(i, "/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/bibeln.txt");
        //Create file for threagram.
        test.printInFileLetterUseage("/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/resultTregram.txt");
        
        //try{
        RandomTextPrint  tmp = new RandomTextPrint("/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/bibeln.txt");
        tmp.makeValueInProcent();
    }
}