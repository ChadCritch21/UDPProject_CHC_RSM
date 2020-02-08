/*
 * This file will serve as our UDP server that is used to receive requests from clients and send quotes.
 * UDP Programming Project
 * CS415 - Data Communication and Data Networks
 * Spring 2020
 * @version 1.0
 */

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

        // declares socket and data streams for server
        private Socket socket = null;
        private ServerSocket server = null;
        private DataInputStream in = null;
        private DataOutputStream out = null;

        // constructor to hold server information
        public Server(int port) {

            try {

                int w = 0;
                String readQuote[] = new String[20];
                String QuoteFile = "quote.csv";

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

                
                LocalDateTime day = LocalDateTime.now();
                DateTimeFormatter form = DateTimeFormatter.ofPattern("MMMM dd, yyyy.");
                String f2 = day.format(form);
                
                DateTimeFormatter tim = DateTimeFormatter.ofPattern("hh:mma");
                String f3 = tim.format(day);
                
                DateTimeFormatter fullDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String f4 = day.format(fullDate);
                                
                // creates a socket for requests
                server = new ServerSocket(port);
                
                // prints process of server connection to the screen
                System.out.println("Server Started at " + f3 + " on " + f2 + "\n");

                socket = server.accept();
              
                // takes input from the client socket 
                in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));

                // sends quotes from the server to the client
                out = new DataOutputStream(socket.getOutputStream());
                
                String line = "";
                String ip = "";
                String portNum = "";
                
                // prompts user for input until "END" is entered
                while (!line.equals("END")) {
                    try {
                        line = in.readUTF();

                        if (line.equals("REQUESTQUOTE")) {
                            int randQuote = ThreadLocalRandom.current().nextInt(0, 19 + 1);
                            out.writeUTF(readQuote[randQuote]);
                            
                            ip = in.readUTF();
                            
                            portNum = in.readUTF();
                           
                            System.out.println("Request received from "+ip+": "+portNum+" "+f4+" "+f3+"\n");
                        }

                    } catch (IOException i) {
                        System.out.println("\n"+"Client ended session");
                        break;
                    }
                }

                // close connection 
                socket.close();
                in.close();
            } catch (IOException i) {
                System.out.println("\n"+"Closing Connection");
            }
        }

    }

    public static void main(String[] args) {

        // creates a server object with the port number
        Server server = new Server(2010);
    }
}
