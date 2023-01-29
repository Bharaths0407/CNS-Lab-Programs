import java.util.*;

public class crc {
    public static int[] divide(int[] divisor, int rem[]) {
        int cur = 0;
        while(true) {
            for(int i = 0; i < divisor.length; i++) {
                rem[cur+i] = rem[cur+i] ^ divisor[i];
            }
            while(rem[cur] == 0 && cur != rem.length-1) {
                cur++;
            }
            if(rem.length-cur < divisor.length) {
                break;
            }
        }
        return rem;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of data bits : ");
        int number = sc.nextInt();
        int data[] = new int[number];
        System.out.println("Enter the data bits : ");
        for(int i = 0; i < number; i++) {
            data[i] = sc.nextInt();
        }
        int divisor[] = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1}; // 16 12 5 0
        int total = divisor.length + data.length - 1;
        int div[] = new int[total];
        int crc[] = new int[total];
        int rem[] = new int[total];
        for(int i = 0; i < data.length; i++) {
            div[i] = data[i];
            rem[i] = data[i];
        }
        System.out.println("After appending 0s : ");
        for(int i = 0; i < div.length; i++) {
            System.out.print(div[i]);
        }
        rem = divide(divisor, rem);
        System.out.println("CRC code is :");
        for(int i = 0; i < crc.length; i++) {
            crc[i] = rem[i] ^ div[i];
            System.out.print(crc[i]);
        }
        System.out.println("Enter the crc code : ");
        for(int i = 0; i < crc.length; i++) {
            div[i] = sc.nextInt();
            rem[i] = div[i];
        }
        rem = divide(divisor, rem);
        for(int i = 0; i < rem.length; i++) {
            if(rem[i] != 0) {
                System.out.println("Error");
                break;
            }
            if(i == rem.length-1) {
                System.out.println("No error");
            }
        }
        System.out.println("Thank You");
    }
}
