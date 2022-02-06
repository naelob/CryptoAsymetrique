package src;
import java.math.BigInteger;

public class RSAPublicKey {
    public BigInteger N;
    public BigInteger e;

    public RSAPublicKey(BigInteger N, BigInteger e){
        this.N = N;
        this.e = e;
    }
    public BigInteger getN(){
        return N;
    }
    public BigInteger getE(){
        return e;
    }
    
}
