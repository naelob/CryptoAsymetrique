package src.ElGamal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import src.ElGamal.GroupsMaths.SafestMaths;

public class DigitalSig {
    public static Params param;

    public DigitalSig(int SECURITY_PARAM){
        param = new Params();
        param.SECURITY_PARAM = SECURITY_PARAM;
    }

    public BigInteger[] getParams() {
        int xInt;
        Random rand = new Random();
        BigInteger p = SafestMaths.confirmPrime(param.SECURITY_PARAM/2,param.RANDOM);;
        BigInteger g = SafestMaths.getPrimRoot(p);
        //System.out.println("p inside getParams is "+p.intValue());
        //xInt = rand.nextInt(2, (p.intValue() - 2) + 1);
        BigInteger x; //new BigInteger(Integer.toString(xInt));
        x = SafestMaths.randomBigIntegerBorn(BigInteger.TWO, p.subtract(BigInteger.TWO));
        //System.out.println("x is inside getparams "+x);
        return new BigInteger[]{p, g, x};
    }
    

    public BigInteger[] sign(byte[] messageASigner,BigInteger p, BigInteger g, BigInteger x) throws NoSuchAlgorithmException{
        BigInteger k = BigInteger.ZERO, r = BigInteger.ZERO, s = BigInteger.ZERO;
        int kInt;
        BigInteger pMin1 = p.subtract(BigInteger.ONE);
        boolean b = false;
        boolean isPrime = false;
        while (!b){
            while (isPrime==false) {
                Random rand = new Random();
                k = SafestMaths.randomBigIntegerBorn(BigInteger.TWO, p.subtract(BigInteger.TWO));;
                if (SafestMaths.gcdBG(k, pMin1).compareTo(BigInteger.ONE) == 0) {
                    isPrime = true;
                }
            }
            r = g.modPow(k, p);
            BigInteger xr = x.multiply(r);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(messageASigner, 0, messageASigner.length);

            s = new BigInteger(1, md.digest()).subtract(xr); 
            b = true;
            try {
                s = s.multiply(k.modInverse(pMin1));
                s = s.mod(pMin1);
            } catch (ArithmeticException e) {
                b = false;
            }
            if (s.equals(BigInteger.ZERO)) {
                b = false;
            }
        }
        BigInteger[] tab = new BigInteger[2];
        tab[0] = r;
        tab[1] = s;
        return tab;
    }

    public boolean verify(BigInteger p, BigInteger g, BigInteger y, BigInteger[] signature, byte[] messageASigner) throws NoSuchAlgorithmException {
         
        BigInteger r = signature[0], s = signature[1];
        if (r.compareTo(BigInteger.ZERO) <= 0 || r.compareTo(p) >= 0 || s.compareTo(BigInteger.ZERO) <= 0 || s.compareTo(p.subtract(BigInteger.ONE)) >= 0) {
            return false;
        }
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(messageASigner, 0, messageASigner.length);
        //return y.pow(r.intValue()).multiply(r.pow(s.intValue())).mod(p).equals(g.modPow(new BigInteger(1, md.digest()), p));
        return y.modPow(r, p).multiply(r.modPow(s, p)).mod(p).equals(g.modPow(new BigInteger(1, md.digest()), p));

    }
    

}
