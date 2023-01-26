import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int bitlength = 1024;
    private SecureRandom r;

    public RSA() {
        r = new SecureRandom();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public RSA(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        Scanner sc = new Scanner(System.in);
        String plaintext = sc.nextLine();
        System.out.println("Plaintext: " + plaintext);
        BigInteger plaintextAsInt = new BigInteger(plaintext.getBytes());
        BigInteger ciphertext = rsa.encrypt(plaintextAsInt);
        System.out.println("Ciphertext: " + ciphertext);
        BigInteger decryptedtext = rsa.decrypt(ciphertext);
        System.out.println("Decrypted: " + new String(decryptedtext.toByteArray()));
    }

    public BigInteger encrypt(BigInteger plaintext) {
        return plaintext.modPow(e, N);
    }

    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, N);
    }
}

