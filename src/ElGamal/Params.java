package src.ElGamal;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Params {
    public SecureRandom RANDOM = new SecureRandom();
    public int SECURITY_PARAM;
    public ElGamalPublicKey publicKey;
    public ElGamalPrivateKey privateKey;
    public BigInteger p;
    public BigInteger g;
    public BigInteger x;
    public BigInteger h;

    public Params(){

    }
    
}
