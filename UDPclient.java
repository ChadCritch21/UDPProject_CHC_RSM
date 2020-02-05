/*
 * This file will serve as our UDP client which will request quotes from the UDP server.
 * UDP Programming Project
 * CS415 - Data Communication and Data Networks
 * Spring 2020
 * @version 1.0
 */
package udpclient;

import java.io.*;
import java.net.*;

/**
 *
 * @author Chad Critchelow and Ryan Meyer
 */
public class UDPclient {

    public static class Client {

        // initialize socket and input output streams 
        private Socket socket = null;
        private DataInputStream input = null;
        private DataInputStream in = null;
        private DataOutputStream out = null;

        // constructor to put ip address and port 
        public Client(String address, int port) {
            // establish a connection 
            try {
                socket = new Socket(address, port);
                System.out.println("Client Connected" + "\n");

                // takes input from terminal 
                input = new DataInputStream(System.in);

                // sends output to the socket 
                out = new DataOutputStream(socket.getOutputStream());

                // fetches data to be returned
                in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));

            } catch (UnknownHostException u) {
                System.out.println(u);
            } catch (IOException i) {
                System.out.println(i);
            }

            // string to read message from input 
            String line = "";
            String quote = "";

            // keep reading until "END" is input 
            while (!line.equals("END")) {
                // takes command and writes string to outputstream
                try {
                    System.out.print("Enter a command: ");
                    line = input.readLine();
                    out.writeUTF(line);

                    System.out.println();
                    
                    // receives data back (quote) and prints this to the screen
                    quote = in.readUTF();
                    System.out.println(quote + "\n");

                } catch (IOException i) {
                    System.out.println("Client Closed");
                }
            }

        }

    }

    public static void main(String[] args) {

        // declares variables to store arguements from the command line
        String ipAdrs = (args[0]);
        int port = Integer.parseInt(args[1]);
        
        // creates a Client object to be used for Socket
        Client client = new Client(ipAdrs, port);
    }
}
