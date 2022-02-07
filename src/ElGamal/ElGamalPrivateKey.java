package src.ElGamal;

import java.math.BigInteger;

public class ElGamalPrivateKey {
    public BigInteger x;


    public ElGamalPrivateKey(BigInteger x){
        this.x = x;
    }
    public BigInteger getX(){
        return x;
    }

}
