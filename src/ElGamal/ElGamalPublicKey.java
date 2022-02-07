package src.ElGamal;

import java.math.BigInteger;

public class ElGamalPublicKey {
    BigInteger p;
    BigInteger g;
    BigInteger h;


    public ElGamalPublicKey(BigInteger p, BigInteger g, BigInteger h){
        this.p = p;
        this.g = g;
        this.h = h;
    }
    public BigInteger getP(){
        return p;
    }
    public BigInteger getG(){
        return g;
    }
    public BigInteger getH(){
        return h;
    }
    
}
