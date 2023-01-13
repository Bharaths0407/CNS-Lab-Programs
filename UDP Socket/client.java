import java.io.*;
import java.net.*;
import java.util.*;

public class client {
    public static void main (String args[]) throws IOException {
        // Create Client socket 
        DatagramSocket ceso = new DatagramSocket(); // ceso = ClientSocket
        
        while(true)
        {
            // Get the Server'S Address
            InetAddress sa = InetAddress.getByName("localhost"); // sa = SeverAddress
            
            Scanner sc = new Scanner(System.in);
            while(true)
            {
                System.out.print("Enter the Message: ");
                String message = sc.nextLine();
                
                // Send the message to the Server
                byte[] sd = message.getBytes(); // sd = Send Data
                DatagramPacket sp = new DatagramPacket(sd,sd.length,sa,9876); // sp = Send Packet
                ceso.send(sp);
                
                // Receive the message from the Server
                byte[] rd = new byte[1024]; // Receive Data
                DatagramPacket rp = new DatagramPacket(rd,rd.length); // rp = Receive Packet
                ceso.receive(rp);
                
                // Print the received message
                String rm = new String(rp.getData(),0,rp.getLength()); // rm = Recevied Message
                System.out.println("Recevied Message from the server: "+ rm);
            }
        }
    }
}
