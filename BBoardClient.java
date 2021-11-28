import java.io.*;
import java.net.*;

public class BBoardClient{
  public static void main(String[] args) throws IOException{

    Socket bbSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    try{
      bbSocket = new Socket("192.168.1.122", 4444);
      out = new PrintWriter(bbSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(bbSocket.getInputStream()));
    }catch (UnknownHostException e){
      System.err.println("Dont know about host: Laptop");
      System.exit(1);
    }catch (IOException e){
      System.err.println("Could not get I/O for the connection to: Laptop");
      System.exit(1);
    }

    System.out.println("Connected to Server");

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String fromServer, fromUser;

    while (true){
      fromServer = in.readLine();
      System.out.println("Server: " + fromServer);

      // If server says bye
      if (fromServer.equalsIgnoreCase("byebye")){
        break;
      }

      fromUser = stdIn.readLine();
      if (fromUser != null){
        System.out.println("Client: " + fromUser);
        out.println(fromUser);
      }
    }
    out.close();
    in.close();
    stdIn.close();
    bbSocket.close();
    System.exit(1);
  }
}
