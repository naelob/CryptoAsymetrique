package src.Rsa;
import java.math.BigInteger;

public class RSAPrivateKey{
    public BigInteger d;
    public BigInteger p;
    public BigInteger q;


    public RSAPrivateKey(BigInteger d, BigInteger p, BigInteger q){
        this.d = d;
        this.p = p;
        this.q = q;
    }
    public BigInteger getD(){
        return d;
    }
    public BigInteger getP(){
        return p;
    }
    public BigInteger getQ(){
        return q;
    }
}