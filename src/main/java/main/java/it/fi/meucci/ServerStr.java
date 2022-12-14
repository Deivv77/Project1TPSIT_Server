package main.java.it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerStr extends Thread{
    ServerSocket server = null;
    Socket client = null;
    String received = null;
    String modified = null;
    BufferedReader input;
    DataOutputStream output;
    

    public ServerStr()
    {
        
        try {
         server = new ServerSocket(7073);
        
         System.out.println("------1 Server start execution------");
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
    }
public Socket waiting()
{
    try{
        
        client = server.accept();
        
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new DataOutputStream(client.getOutputStream());
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Error during server instance");
        System.exit(1);
    }
    return client;

}
public void comunicate()
{
    try{
        System.out.println("Welcome client, write a phrase, waiting...");
        received = input.readLine();
        modified = received.toUpperCase();
        System.out.println("Printing the modified phrase");
        output.writeBytes(modified + '\n');
        System.out.println("SERVER: elaboration ended...");
        client.close();
    }
    catch(Exception e)
    {

    }
}

}
