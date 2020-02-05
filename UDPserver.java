/*
 * This file will serve as our UDP server that is used to receive requests from clients and send quotes.
 * UDP Programming Project
 * CS415 - Data Communication and Data Networks
 * Spring 2020
 * @version 1.0
 */
package udpserver;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Chad Critchelow and Ryan Meyer
 */
public class UDPserver {

    public static class Server {

        //initialize socket and input stream 
        private Socket socket = null;
        private ServerSocket server = null;
        private DataInputStream in = null;
        private DataOutputStream out = null;

        // constructor with port 
        public Server(int port) {

            try {

                int w = 0;
                String readQuote[] = new String[20];
                String QuoteFile = "C:\\Users\\chadc\\Documents\\quote.csv";

                // reads quote file and stores into an array
                try { 
                    FileReader inputFile = new FileReader(QuoteFile);
                    BufferedReader bufferReader = new BufferedReader(inputFile);
                    String fileLine;

                    while ((fileLine = bufferReader.readLine()) != null) {
                        readQuote[w] = fileLine;
                        w++;
                    }
                    bufferReader.close();

                } catch (Exception e) {
                    System.out.println(e);
                }

                /*
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                        */
                
                // creates a server socket to wait for client requests
                server = new ServerSocket(port);
                
                // prints process of server connection to the screen
                System.out.println("Server Started at...."+ "\n");

                System.out.println("Waiting for a client ..." + "\n");

                socket = server.accept();
                System.out.println("Client Accepted"+"\n");

                // takes input from the client socket 
                in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));

                // sends quotes from the server to the client
                out = new DataOutputStream(socket.getOutputStream());

                String line = "";

                // reads message from client until "Over" is sent 
                while (!line.equals("END")) {
                    try {
                        line = in.readUTF();

                        if (line.equals("REQUESTQUOTE")) {
                            int randQuote = ThreadLocalRandom.current().nextInt(0, 19 + 1);
                            out.writeUTF(readQuote[randQuote]);
                        }

                    } catch (IOException i) {
                        System.out.println(i);
                    }
                }

                // close connection 
                socket.close();
                in.close();
            } catch (IOException i) {
                System.out.println("Closing Connection");
            }
        }

    }

    public static void main(String[] args) {

        // creates a server object with the port number
        Server server = new Server(5000);

    }
}
