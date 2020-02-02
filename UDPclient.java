/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Chad Critchelow and Ryan Meyer
 */
public class UDPclient {
    
    public static void main(String[] args) throws IOException {
        
        System.out.println(args[0]);
        System.out.print(args[1]);
        
        String command = "";
        
        while (!"END".equals(command)){
            
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a command: ");
        command = reader.readLine();
        
        System.out.println();
        
        System.out.println(command+"\n");
                
    }
  }
}
