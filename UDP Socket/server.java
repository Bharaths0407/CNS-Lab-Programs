import java.io.*;
import java.net.*;

public class server{
    public static void main(String args[]) throws IOException{
        // Create the Server Socket
        DatagramSocket seso = new DatagramSocket(9876); // seso = ServerSocket
        
        while(true)
        {
            // Receive the Client'S message
            byte[] rd = new byte[1024]; // rd = ReceiveData
            DatagramPacket rp = new DatagramPacket(rd,rd.length); // rp = ReceivePacket
            seso.receive(rp);
            
            // Extract the Client address and Port
            InetAddress ca = rp.getAddress(); // ca = ClientAddress
            int cp = rp.getPort(); // cp = ClientPort
            
            // Get the massage
            String message = new String(rp.getData(),0,rp.getLength());
            System.out.println("Received Message From "+ca.getHostAddress()+" : "+cp+" : "+message);
            
            // Send the message back to the Client
            byte[] sd = message.getBytes(); // sd = Senddata
            DatagramPacket sp = new DatagramPacket(sd,sd.length,ca,cp); // sp = SendPacket
            seso.send(sp);
        }
    }
}