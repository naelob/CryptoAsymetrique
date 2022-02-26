package src.ElGamal;

import java.math.BigInteger;
import java.util.Random;

import src.ElGamal.GroupsMaths.SafestMaths;

public class GenKeys {
    public static Params param;
    public static Random RAND = new Random();
    public static Random RAND2 = new Random();

    public GenKeys(int SECURITY_PARAM){
        param = new Params();
        param.SECURITY_PARAM  = SECURITY_PARAM;
        genParams();
        genPublicKey();
        genPrivateKey();
    }
    public static void genParams(){
        Random rand = new Random();

        param.p = BigInteger.probablePrime((param.SECURITY_PARAM)/2, param.RANDOM);
        //param.g = BigInteger.valueOf(RAND2.nextInt(param.p.intValue() - 2) + 2); // we dont check if p.intValue() is negative or not
        //param.x = SafestMaths.getXFromGroup(param.p); // we dont check if p.intValue() is negative or not
        param.g = SafestMaths.getPrimRoot(param.p);
        param.x = BigInteger.valueOf(1+Math.abs(rand.nextLong()));
        param.h = param.g.modPow(param.x,param.p);
    }
    public static void genPublicKey(){
        param.publicKey = new ElGamalPublicKey(param.p, param.g,param.h);
    }
    public static void genPrivateKey(){
        param.privateKey = new ElGamalPrivateKey(param.x,param.p);
    }

    public ElGamalPublicKey getPublicKey(){
        return param.publicKey;
    }
    public ElGamalPrivateKey getPrivateKey(){
        return param.privateKey;
    }

    //ETAPE 2 : chiffrement par rapport à une clé publique donnée

    public CoupleChiffre chiffrement(byte[] messageClair,BigInteger p, BigInteger h, BigInteger g){
        Random rand = new Random();

        //BigInteger x = SafestMaths.getXFromGroup(p);
        BigInteger x = BigInteger.valueOf(Math.abs(rand.nextLong()));

        BigInteger m = new BigInteger(1,messageClair);
        System.out.println("g from chiffrement is : "+ param.g); 
        BigInteger c1 = param.g.modPow(x,param.p);
        BigInteger c2 = param.h.modPow(x, param.p).multiply(m).mod(param.p);

        return new CoupleChiffre(c1,c2);
    }

    //ETAPE 3 : déchiffrement par rapport à une clé secrète donnée
    public BigInteger dechiffrement(CoupleChiffre C, BigInteger x, BigInteger p){
        BigInteger C1 = C.getC1();
        BigInteger C2 = C.getC2();
        //BigInteger decrypt = C2.divide(C1.modPow(x,p)).mod(p);
        
        BigInteger decrypt = C2.multiply(C1.modPow(p.subtract(BigInteger.ONE).subtract(x), p));
        decrypt = decrypt.mod(p);

        return decrypt;
        
    }


}
