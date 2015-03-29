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
        
        //Read user input 1 for 1 char, 2 for Diagram och 3 for triagram.
        System.out.println("var god och fyll i moduls värdet.");
        Scanner input = new Scanner(System.in);
        int i = 0;
        i = input.nextInt();
        //new object of class StatLetter, par is file name/location.
        StatLetter test= new StatLetter("/home/mejan/Documents/skola/VT-15/Kryptografi/Assignment/a1/svenskaAlpha/bibeln.txt");
        //read file.
        test.ReadFromFile(i);
        
    }
}