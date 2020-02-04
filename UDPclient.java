/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
                try {
                    System.out.print("Enter a command: ");
                    line = input.readLine();
                    out.writeUTF(line);

                    System.out.println();

                    quote = in.readUTF();
                    System.out.println(quote + "\n");

                } catch (IOException i) {
                    System.out.println("Client Closed");
                }
            }

        }

    }

    public static void main(String[] args) {

        String ipAdrs = (args[0]);
        int port = Integer.parseInt(args[1]);
        
        Client client = new Client(ipAdrs, port);
    }
}
