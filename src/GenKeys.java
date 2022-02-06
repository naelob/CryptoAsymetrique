package src;
import java.math.BigInteger;
import java.security.SecureRandom;

public class GenKeys {

    //ETAPE 1 : calcul clés publiques/privées
    public SecureRandom RANDOM = new SecureRandom();
    public int SECURITY_PARAM;
    public RSAPublicKey publicKey;
    public RSAPrivateKey privateKey;
    public BigInteger p;
    public BigInteger q;
    public BigInteger d;
    public BigInteger e;
    public BigInteger PHI;
    public BigInteger N;

    public GenKeys(int SECURITY_PARAM){
        this.SECURITY_PARAM = SECURITY_PARAM;
        genParams();
        genPublicKey();
        genPrivateKey();
    }
    public void genParams(){
        p = BigInteger.probablePrime(SECURITY_PARAM, RANDOM);
        q = BigInteger.probablePrime(SECURITY_PARAM, RANDOM);
        N = p.multiply(q);
        PHI = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
         
        //cherchons un nombre d tel que ed soit congru à 1 modulo PHI(N) 
        //avec e un nombre choisi dans (Z/PHI(N)Z)*

        e = new BigInteger("3");
        d = e.modInverse(PHI);
    }
    public void genPublicKey(){
        publicKey = new RSAPublicKey(N,e);
    }
    public void genPrivateKey(){
        privateKey = new RSAPrivateKey(d, p, q);
    }

    public RSAPublicKey getPublicKey(){
        return publicKey;
    }
    public RSAPrivateKey getPrivateKey(){
        return privateKey;
    }

    //ETAPE 2 : chiffrement par rapport à une clé publique donnée

    /*public BigInteger chiffrement(String messageClair){
        return "";
    }*/

    public BigInteger chiffrement(BigInteger messageClair){
        return messageClair.modPow(e, N);
    }

    //ETAPE 3 : déchiffrement par rapport à une clé secrète donnée
    public BigInteger dechiffrement(BigInteger messageChiffre){
        return messageChiffre.modPow(d, N);
    }
}