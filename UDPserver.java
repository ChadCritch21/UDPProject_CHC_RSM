/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * @author Chad Critchelow and Ryan Meyer
 */
public class UDPserver {

    public static void main(String[] args) {

        int i = 0;
        String readQuote[] = new String[20];
        String QuoteFile = "C:\\Users\\chadc\\Documents\\quote.csv";

        try {//reading patient data into the program
            FileReader inputFile = new FileReader(QuoteFile);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String fileLine;

            while ((fileLine = bufferReader.readLine()) != null) {
                readQuote[i] = fileLine;
                i++;
            }
            bufferReader.close();
            
            int randQuote = ThreadLocalRandom.current().nextInt(0, 19 + 1);
            System.out.println(readQuote[randQuote]);
            
        } catch (Exception e) {
            System.out.println("Processing newly created input file...\n");
        }
    }
}
   
