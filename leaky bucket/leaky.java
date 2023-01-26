import java.util.*;

public class leaky {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the bucket size : ");
        int bSize = sc.nextInt();
        System.out.println("Enter the output rate : ");
        int oRate = sc.nextInt();
        System.out.println("Enter the number of seconds to simulate ;");
        int nSec = sc.nextInt();
        Random r = new Random();
        int packet[] = new int[100];
        for(int i = 0; i < nSec ; i++) {
            packet[i] = (r.nextInt(9) + 1)* 10;
        }
        System.out.println("Seconds | Received | Sent | Remain | Dropped");
        int drop = 0, remain = 0, mini = 0;
        for(int i = 0; i < nSec; i++) {
            remain = remain + packet[i];
            System.out.print(i+ 1 + "  " + packet[i] + "  ");
            if(remain > bSize) {
                drop = remain - bSize;
                remain = bSize;
            }
            mini = Math.min(remain, oRate);
            remain -= mini;
            System.out.println(mini + "  " + remain + "  " + drop);
            drop = 0;
        }
        System.out.println();
        while(remain != 0) {
            drop = 0;
            if(remain > bSize) {
                drop = remain - bSize;
                remain = bSize;
            }
            mini = Math.min(remain, oRate);
            remain -= mini;
            System.out.println("Sent = " + mini + "    Remaining = " + remain + "   Dropped = " + drop);
        }
    }
}
