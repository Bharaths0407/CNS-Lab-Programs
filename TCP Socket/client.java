import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {
        // connect to the server on localhost at port 7777
        Socket cs = new Socket("localhost", 7777); // cs = client socket
        DataOutputStream out = new DataOutputStream(cs.getOutputStream());
        
        // send the file name to the server
        out.writeUTF("example.txt");
        DataInputStream in = new DataInputStream(cs.getInputStream());
        
        // read the file data from the server
        String fileData = in.readUTF();
        System.out.println(fileData); // print the file data
        cs.close(); // close the socket
    }
}
