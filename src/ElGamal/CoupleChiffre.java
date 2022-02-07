package src.ElGamal;

import java.math.BigInteger;

public class CoupleChiffre {
    public BigInteger C1;
    public BigInteger C2;

    public CoupleChiffre(BigInteger C1, BigInteger C2){
        this.C1 = C1;
        this.C2 = C2;
    }
    public BigInteger getC1(){
        return C1;
    }
    public BigInteger getC2(){
        return C2;
    }

}
