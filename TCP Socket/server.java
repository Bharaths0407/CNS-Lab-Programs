import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws IOException {
        // listen on port 7777
        ServerSocket seso = new ServerSocket(7777); // seso = server

        while (true) 
        {
            // wait for a client to connect
            Socket cs = seso.accept(); // cs = Client Socket
            DataInputStream in = new DataInputStream(cs.getInputStream());
            
            // read the file name from the client
            String fn = in.readUTF(); // fn = File name
            File file = new File(fn);
            
            // check if the file exists
            if (file.exists()) 
            { 
                byte[] fileData = new byte[(int) file.length()];
                DataInputStream fileIn = new DataInputStream(new FileInputStream(file));
                
                // read the file data
                fileIn.readFully(fileData); 
                DataOutputStream out = new DataOutputStream(cs.getOutputStream());
                
                // send the file data to the client
                out.writeUTF(new String(fileData)); 
            } 
            else 
            {
                DataOutputStream out = new DataOutputStream(cs.getOutputStream());
                out.writeUTF("File not found"); // send a message to the client if the file is not found
            }
            cs.close(); // close the socket
        }
    }
}

