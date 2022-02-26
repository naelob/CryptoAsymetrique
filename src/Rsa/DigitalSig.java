package src.Rsa;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigitalSig {

    public RSAPublicKey pK;
    public BigInteger pKN;

    public DigitalSig(){

    }
    
    public BigInteger signature(byte[] messageASigner, RSAPrivateKey privateKey, RSAPublicKey publicKey) throws NoSuchAlgorithmException{
        //final GenKeys keys = new GenKeys(1024);
        //pKN  = pK.getN();
        //RSAPrivateKey pvK = keys.getPrivateKey();
        BigInteger publicN = publicKey.getN();
        BigInteger privateD = privateKey.getD(); 

        //appliquer une fonction de hashage au message messageASigner
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(messageASigner, 0, messageASigner.length);
        BigInteger bi = new BigInteger(1,md.digest());
        System.out.println(bi.toString(16));

        return bi.modPow(privateD,publicN);
    }
    
    public boolean verification(byte[] messageASigner,BigInteger signatureRecue,RSAPublicKey publicKey) throws NoSuchAlgorithmException{
        BigInteger e = publicKey.getE();
        BigInteger pKN = publicKey.getN();
        BigInteger hashSignatureRecue = signatureRecue.modPow(e, pKN);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(messageASigner, 0, messageASigner.length);
        BigInteger bi = new BigInteger(1,md.digest());
        System.out.println(bi.toString(16));
        return bi.equals(hashSignatureRecue);

    }
}
