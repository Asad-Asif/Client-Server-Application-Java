import java.io.*;
import java.net.*;
import java.util.*;

public final class BBoardServer {
  public static void main(String argv[]) throws Exception{

    int port = 4444;

    // Checking number of inputs from command line
    if (argv.length < 4){
      System.out.println("Server not started. Not enough arguments");
      System.exit(1);
    }
    else{

      // Converting first input into integer port
      port = Integer.valueOf(argv[0]);

    }

    // Try and catch to listen to port
    ServerSocket server = null;
    try{
      server = new ServerSocket(port);
    }catch(IOException e){
      System.err.println("Could not listen to port: 4444");
      System.exit(1);
    }

    System.out.println("Listening to port 4444");

    // Cient socket to accept connection
    Socket client = null;
    try{
      client = server.accept();
    }catch (IOException e){
      System.err.println("Accept Failed");
      System.exit(1);
    }

    System.out.println("Accepted Client");

    // Read and Write to/from client
    PrintWriter output = new PrintWriter(client.getOutputStream(), true);
    BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));

    String inputLine, outputLine;
    System.out.println("YOOOO");
    while((inputLine = input.readLine()) != null){
      System.out.println("HIIIII");

      // If client says "Bye"
      if (inputLine.equalsIgnoreCase("Bye")){
        System.out.println("byebye");
        break;
      }

      // Close all appropriate streams/sockets
      output.close();
      input.close();
      client.close();
      server.close();

    }
  }
}
