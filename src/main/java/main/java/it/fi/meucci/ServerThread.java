package main.java.it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String received = null;
    String modified = null;
    BufferedReader input;
    DataOutputStream output;
 

    public ServerThread (Socket socket)
    {
        this.client = socket;
       
        
    }

    public void run()
    {    
         try {
            comunicate();
        } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace(System.out);
        }
    }
    public void comunicate() throws Exception{
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new DataOutputStream(client.getOutputStream());
        for(;;)
        {
            received = input.readLine();
            if(received == null || received.equals("END"))
            {
                output.writeBytes(received + "server closing..." + '\n');
                System.out.println("Echo on server closing: "+ received);
                break;
            }
            else if(received.equals("POWER OFF"))
            {
                MultiServer.poweroff();
                server.close();
                break;
            }
            
            else
            {
                output.writeBytes("recived: "+ received + '\n');
                System.out.println("Echo on server:" + received);

            }
        }
        output.close();
        input.close();
        System.out.println("closing socket");
        client.close();
    }
}
