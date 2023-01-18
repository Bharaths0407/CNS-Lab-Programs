import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private final static BigInteger one = new BigInteger("1");
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    // generate an N-bit (roughly) public and private key
    RSA(int N) {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(N / 2, 100, r);
        BigInteger q = new BigInteger(N / 2, 100, r);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);
        publicKey = new BigInteger("65537"); // common value in practice = 2^16 + 1
        privateKey = publicKey.modInverse(phi);
    }

    BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(128);
        String message = "This is a secret message";
        BigInteger bigInt = new BigInteger(message.getBytes());
        BigInteger encrypted = rsa.encrypt(bigInt);
        BigInteger decrypted = rsa.decrypt(encrypted);
        String decryptedMessage = new String(decrypted.toByteArray());
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + encrypted);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
