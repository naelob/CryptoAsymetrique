package src.Rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Params {
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

    /*public Params(RSAPublicKey pK, RSAPrivateKey pvK, BigInteger p, BigInteger q, 
    BigInteger d,BigInteger e,BigInteger PHI,BigInteger N){
        this.publicKey=pK;
        this.privateKey=pvK;
        this.p=p;
        this.q=q;
        this.d=d;
        this.e=e;
        this.PHI=PHI;
        this.N=N;
    }*/
    public Params(){
        
    }
}
