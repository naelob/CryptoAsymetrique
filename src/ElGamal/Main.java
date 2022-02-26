package src.ElGamal;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        GenKeys keyPair = new GenKeys(1024);

        String a = Fichier.getContentFichier("/Users/nael/Desktop/PROJET MATH_INFO/exemples/text2.txt");
        byte[] By = Fichier.stringToBytesArray(a);
        String s = new String(By);
        BigInteger mesg = new BigInteger(By);

        System.out.println("Message clair (String) : " + a);
        System.out.println("Message clair (BigInteger) : " + mesg);

        CoupleChiffre chiffrementMesg = keyPair.chiffrement(mesg.toByteArray(),keyPair.getPublicKey().getP(),keyPair.getPublicKey().getH(),keyPair.getPublicKey().getG());
        System.out.println("Message chiffré C1 (BigInteger) " + chiffrementMesg.getC1());
        System.out.println("Message chiffré C2 (BigInteger) " + chiffrementMesg.getC2());


        BigInteger msgDechiffre = keyPair.dechiffrement(chiffrementMesg,keyPair.getPrivateKey().getX(),keyPair.getPrivateKey().getP());
        System.out.println("Message dechiffré : " + msgDechiffre );
        System.out.println("Message dechiffré : " + new String(msgDechiffre.toByteArray()));


    }
}
