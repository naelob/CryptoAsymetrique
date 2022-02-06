package src;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignAndVerify {
    //Params paramS = new Params();
    public RSAPublicKey pK;
    public BigInteger pKN;

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

    public BigInteger signature(String messageASigner) throws NoSuchAlgorithmException{
        final GenKeys keys = new GenKeys(1024);
        pK = keys.getPublicKey();
        pKN  = pK.getN();
        RSAPrivateKey pvK = keys.getPrivateKey();
        BigInteger privateKD = pvK.getD(); 
        //appliquer une fonction de hashage au message messageASigner
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashMessage = md.digest(messageASigner.getBytes(StandardCharsets.UTF_8));
        String hashedString = bytesToHex(hashMessage);
        return (new BigInteger(hashedString)).modPow(privateKD,pKN);
    }
    
    public boolean verification(BigInteger messageASigner,BigInteger signatureRecue){
        //String signed = signatureRecue.toString();
        BigInteger e = pK.getE();
        BigInteger hashSignatureRecue = signatureRecue.modPow(e, pKN);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashMessage = md.digest(messageASigner.getBytes(StandardCharsets.UTF_8));
        String hashedString = bytesToHex(hashMessage);
        return (new BigInteger(hashedString))==hashSignatureRecue;

    }
}
