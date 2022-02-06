package src;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Signature;

public class GenKeys {

    //ETAPE 1 : calcul clés publiques/privées
    /*public SecureRandom RANDOM = new SecureRandom();
    public int SECURITY_PARAM;
    public RSAPublicKey publicKey;
    public RSAPrivateKey privateKey;
    public BigInteger p;
    public BigInteger q;
    public BigInteger d;
    public BigInteger e;
    public BigInteger PHI;
    public BigInteger N;*/
    public static Params param = new Params();

    public GenKeys(int SECURITY_PARAM){
        //this.SECURITY_PARAM = SECURITY_PARAM;
        param.SECURITY_PARAM = SECURITY_PARAM;
        genParams();
        genPublicKey();
        genPrivateKey();
    }
    public static void genParams(){

        //TODO : TEST DE PRIMALITE A VERIFIER PAR LA SUITE 
        param.p = BigInteger.probablePrime((param.SECURITY_PARAM)/2, param.RANDOM);
        param.q = BigInteger.probablePrime((param.SECURITY_PARAM)/2, param.RANDOM);
        param.N = param.p.multiply(param.q);
        param.PHI = (param.p.subtract(BigInteger.ONE).multiply(param.q.subtract(BigInteger.ONE)));
         
        //cherchons un nombre d tel que ed soit congru à 1 modulo PHI(N) 
        //avec e un nombre choisi dans (Z/PHI(N)Z)*

        param.e = new BigInteger("65537");
        param.d = param.e.modInverse(param.PHI);
    }
    public static void genPublicKey(){
        param.publicKey = new RSAPublicKey(param.N,param.e);
    }
    public void genPrivateKey(){
        param.privateKey = new RSAPrivateKey(param.d, param.p, param.q);
    }

    public RSAPublicKey getPublicKey(){
        return param.publicKey;
    }
    public RSAPrivateKey getPrivateKey(){
        return param.privateKey;
    }

    //ETAPE 2 : chiffrement par rapport à une clé publique donnée

    public String chiffrement(String messageClair){
        return this.chiffrement(new BigInteger(messageClair)).toString();
    }

    public BigInteger chiffrement(BigInteger messageClair){
        return messageClair.modPow(param.e, param.N);
    }

    //ETAPE 3 : déchiffrement par rapport à une clé secrète donnée
    public BigInteger dechiffrement(BigInteger messageChiffre){
        return messageChiffre.modPow(param.d, param.N);
    }
    public String dechiffrement(String messageChiffre){
        return this.dechiffrement(new BigInteger(messageChiffre)).toString();
    }

}