package src.Rsa;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException,NoSuchAlgorithmException{
        GenKeys keyPair = new GenKeys(1024);
        String a = Fichier.getContentFichier("/Users/nael/Desktop/PROJET MATH_INFO/exemples/text1.txt");
        byte[] By = Fichier.stringToBytesArray(a);
        String s = new String(By);
        BigInteger mesg = new BigInteger(By);

        System.out.println("Message clair (String) : " + a);
        System.out.println("Message clair (bytes) : " + s );
        System.out.println("Message clair (BigInteger) : " + mesg);

        BigInteger msgChiffre = keyPair.chiffrement(mesg);
        System.out.println("Message chiffré (BigInteger) " + msgChiffre);

        BigInteger msgDechiffre = keyPair.dechiffrement(msgChiffre);
        System.out.println("Message dechiffré : " + msgDechiffre );

        DigitalSig sv = new DigitalSig();
        BigInteger sig;
        boolean b;
        sig = sv.signature(msgChiffre.toByteArray()); // erreur dans signature
        System.out.println("Signature generée : " + sig);
        b = sv.verification(msgChiffre.toByteArray(), sig);
        if(b){
            System.out.println("Signature correcte !");
        }else{
            System.out.println("Signature erronée ! ");
        }
    }
}
