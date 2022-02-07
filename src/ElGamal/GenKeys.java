package src.ElGamal;

import java.math.BigInteger;
import java.util.Random;

import src.ElGamal.GroupsMaths.SafestMaths;

public class GenKeys {
    public static Params param;
    public static Random RAND = new Random();

    public GenKeys(int SECURITY_PARAM){
        param = new Params();
        param.SECURITY_PARAM  = SECURITY_PARAM;
        genParams();
        genPublicKey();
        genPrivateKey();

    }
    public static void genParams(){
        //TODO:test primalite fermat
        param.p = BigInteger.probablePrime((param.SECURITY_PARAM)/2, param.RANDOM);
        param.g = SafestMaths.findGenerateurGroupe(param.p.intValue()); // choisir un nombre qui engendre Fp pour p dans Z
        param.x = BigInteger.valueOf(1 + Math.abs(RAND.nextLong()));
        param.h = param.g.modPow(param.x,param.p);
    }
    public static void genPublicKey(){
        param.publicKey = new ElGamalPublicKey(param.p, param.g,param.h);
    }
    public static void genPrivateKey(){
        param.privateKey = new ElGamalPrivateKey(param.x);
    }

    public ElGamalPublicKey getPublicKey(){
        return param.publicKey;
    }
    public ElGamalPrivateKey getPrivateKey(){
        return param.privateKey;
    }

    //ETAPE 2 : chiffrement par rapport à une clé publique donnée

    public CoupleChiffre chiffrement(BigInteger messageClair){
        Random rand = new Random();
        BigInteger r = BigInteger.valueOf(Math.abs(rand.nextLong()));
        BigInteger m = new BigInteger("23"); //encoder le message selon le groupe Fp en question
        BigInteger c2 = param.h.modPow(r, param.p).multiply(m).mod(param.p);
        BigInteger c1 = param.g.modPow(r,param.p);
        return new CoupleChiffre(c1,c2);
    }

    //ETAPE 3 : déchiffrement par rapport à une clé secrète donnée
    public BigInteger dechiffrement(BigInteger messageClair){
        CoupleChiffre C = chiffrement(messageClair);
        BigInteger C2 = C.getC2();
        BigInteger C1 = C.getC1();
        C1.modPow(param.x,param.p);
        return C2.divide(C1).mod(param.p);
    }


}
