package src.Rsa;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigitalSig {
    //Params paramS = new Params();
    public RSAPublicKey pK;
    public BigInteger pKN;

    public DigitalSig(){

    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    public BigInteger signature(byte[] messageASigner) throws NoSuchAlgorithmException{
        final GenKeys keys = new GenKeys(1024);
        pK = keys.getPublicKey();
        pKN  = pK.getN();
        RSAPrivateKey pvK = keys.getPrivateKey();
        BigInteger privateKD = pvK.getD(); 
        //appliquer une fonction de hashage au message messageASigner
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashMessage = md.digest(messageASigner);
        String hashedString = bytesToHex(hashMessage);

        //pronleme NumberFormatException
        System.out.println(hashedString);
        return new BigInteger(hashedString).modPow(privateKD,pKN);
    }
    
    public boolean verification(byte[] messageASigner,BigInteger signatureRecue) throws NoSuchAlgorithmException{
        //String signed = signatureRecue.toString();
        BigInteger e = pK.getE();
        BigInteger hashSignatureRecue = signatureRecue.modPow(e, pKN);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        //byte[] hashMessage = md.digest(messageASigner.getBytes(Charset.forName("UTF-8")));
        byte[] hashMessage = md.digest(messageASigner);
        String hashedString = bytesToHex(hashMessage);
        return (new BigInteger(hashedString)).equals(hashSignatureRecue);

    }
}
