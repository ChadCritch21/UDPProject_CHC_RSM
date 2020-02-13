# UDP Networking Project

Team: Chad Critchelow and Ryan Meyer

Summary:
 
  This project consists of two java applications, a client and server, that will be used with UDP to communicate information.
  The UDP server will run and wait to receive requests from the client application. Once a request has been received, this  
  will send a random quote to the client from a provided list, as well as display a response that a request has been received,
  and include the client's IP address, port number, and date and time the request was made. The client application will prompt
  for a command from the user and will continue prompting until "END" is entered. Once "REQUESTQUOTE" has been entered, this
  will send a request to the UDP server and then displays the requested quote once received. Upon the user entering "END", the
  request loop ends and connection closed. 

Tasks Completed:

1. Ryan 
  - Completed client application to receive messages from server
  - Assisted with commenting out java files for clarity
  - Completed formatting for date and time for server application
  - Helped with adding IP address from client accessing the server to show in message
  - Formatted README.md file to include proper information 
  
2. Chad
  - Completed server application to send quote messages to client
  - Send email to obtain port message for communication
  - Assisted with commenting java files for clarity
  - Create quotes file to be used for server to fetch from and send
  - Completed setup to obtain port number from client accessing the server application to show in message
  - Worked on QA using the ghopper server to confirm functionality
  
Instructions:

1. With both UDP server and client applications cloned, begin by opening the UDPserver application and running this upon compilation
   - Once the repository is cloned, the quotes.csv file should be included in the UDPProject_CHC_RSM directory
2. Once the UDP server is running and UDPclient application has been compiled, the UDP client application will be ran using
the following command prompt with two arguements, the IP Address of the server and port number: java UDPclient 10.101.1.1 2010
3. Upon starting the UDP client, the command line will prompt to enter a command
4. Type "REQUESTQUOTE" to send a request to the UDP server to fetch a random quote, or "END" to stop the application
5. Once "REQUESTQUOTE" is typed, the UDP client should display the random quote, along with an additional prompt to enter a command
6. The UDP server will then prompt a request was received and include information about the client 
7. After this, as many quotes can be requested as desired until "END" is entered to close the connection through the UDP 
client application


