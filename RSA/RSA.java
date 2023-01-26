import java.math.BigInteger;
import java.util.*;

public class RSA {
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    RSA(int bitLength) {
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        modulus = p.multiply(q);
        publicKey = new BigInteger("257"); // commonly used value in RSA
        privateKey = publicKey.modInverse(phi);
    }

    BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }
    public static void main(String[] args) {
        RSA rsa = new RSA(1024);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plain text");
        String text1 = sc.nextLine();
        System.out.println("The plain text is : " + text1);
        BigInteger plaintext = new BigInteger(text1.getBytes());
        BigInteger ciphertext = rsa.encrypt(plaintext);
        System.out.println("Encrypted message: " + ciphertext);
        System.out.println("Ciphertext Text to be Decrypted: " + ciphertext);
        plaintext = rsa.decrypt(ciphertext);
        System.out.println("Decrypted message: " + new String(plaintext.toByteArray()));
    }
}