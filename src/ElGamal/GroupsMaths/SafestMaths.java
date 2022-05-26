package src.ElGamal.GroupsMaths;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class SafestMaths {

    static BigInteger ZERO = BigInteger.ZERO;
    static BigInteger ONE = BigInteger.ONE;
    static BigInteger TWO = BigInteger.TWO;

    public static BigInteger fastExponential(BigInteger base, BigInteger exponent, BigInteger module){
		if (exponent.equals(ZERO)) return ONE;
		BigInteger res = ONE;
		base = base.mod(module);
		while (exponent.compareTo(ZERO) > 0){
			if (exponent.mod(TWO).equals(ONE)){
				res = res.multiply(base);
				res = res.mod(module);
			}
			base = base.multiply(base);
			base = base.mod(module);
			exponent = exponent.divide(TWO);
		} 
		return res;
	}
	
	public static boolean fermatTest(BigInteger number){
		if (number.compareTo(ONE) <= 0) return false;
		if (number.equals(TWO)) return true;
		if (number.mod(TWO).equals(ZERO)) return false;
		BigInteger exponent = number.subtract(ONE);
		BigInteger base, remainder;
		for (int i = 0; i < 100; i++){
			base = randomBigInteger(number);
			remainder = fastExponential(base, exponent, number);
			if (remainder.compareTo(ONE) != 0) return false;
		}
		return true;
	}
    public static BigInteger confirmPrime(int SECURITY, Random rand){
        BigInteger p = new BigInteger("6");
        while(!fermatTest(p)) 
            p = BigInteger.probablePrime(SECURITY,rand);
        return p;
    }

    public static boolean isPrime(int n)
    {
        // Corner cases
        if (n <= 1)
        {
            return false;
        }
        if (n <= 3)
        {
            return true;
        }
 
        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
        {
            return false;
        }
 
        for (int i = 5; i * i <= n; i = i + 6)
        {
            if (n % i == 0 || n % (i + 2) == 0)
            {
                return false;
            }
        }
 
        return true;
    }
    public static int power(int x, int y, int p){
        int res = 1;     // Initialize result
 
        x = x % p; // Update x if it is more than or
        // equal to p
 
        while (y > 0)
        {
            // If y is odd, multiply x with result
            if (y % 2 == 1)
            {
                res = (res * x) % p;
            }
 
            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }
    public static void findPrimefactors(HashSet<Integer> s, int n)
    {
        // Print the number of 2s that divide n
        while (n % 2 == 0)
        {
            s.add(2);
            n = n / 2;
        }
 
        // n must be odd at this point. So we can skip
        // one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i = i + 2)
        {
            // While i divides n, print i and divide n
            while (n % i == 0)
            {
                s.add(i);
                n = n / i;
            }
        }
 
        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2)
        {
            s.add(n);
        }
    }
    public static BigInteger findGenerateurGroupe(int n){
        HashSet<Integer> s = new HashSet<Integer>();
 
        // Check if n is prime or not
        if (isPrime(n) == false)
        {
            return BigInteger.valueOf(0);
        }
 
        // Find value of Euler Totient function of n
        // Since n is a prime number, the value of Euler
        // Totient function is n-1 as there are n-1
        // relatively prime numbers.
        int phi = n - 1;
 
        // Find prime factors of phi and store in a set
        findPrimefactors(s, phi);
 
        // Check for every number from 2 to phi
        for (int r = 2; r <= phi; r++)
        {
            // Iterate through all prime factors of phi.
            // and check if we found a power with value 1
            boolean flag = false;
            for (Integer a : s)
            {
 
                // Check if r^((phi)/primefactors) mod n
                // is 1 or not
                if (power(r, phi / (a), n) == 1)
                {
                    flag = true;
                    break;
                }
            }
 
            // If there was no power with value 1.
            if (flag == false)
            {
                return BigInteger.valueOf(r);
            }
        }
 
        // If no primitive root found
        return BigInteger.valueOf(0);
    }
    public static int gcd(int a, int b){
        if(a < b){
            return gcd(b,a);
        }else if(a % b == 0) {
            return b;
        }else{
            return gcd(b,a % b);
        }
    }
    public static BigInteger gcdBG(BigInteger a, BigInteger b) {
        BigInteger t;
        while (!b.equals(BigInteger.ZERO)) {
            t = a;
            a = b;
            b = t.remainder(b);
        }
        return a;
    }
    public static BigInteger randomBigInteger(BigInteger n) {
        Random rnd = new Random();
        int maxNumBitLength = n.bitLength();
        BigInteger aRandomBigInt;
        do {
            aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
            // compare random number lessthan ginven number
        } while (aRandomBigInt.compareTo(n) > 0); 
        return aRandomBigInt;
    }
    public static BigInteger randomBigIntegerBorn(BigInteger lower, BigInteger upper){
        //BigInteger upper = new BigInteger("5000000000000");
        //BigInteger lower = new BigInteger("25000000000");
        BigInteger bigInteger = upper.subtract(lower);
        Random randNum = new Random();
        int len = upper.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(lower) < 0)
            res = res.add(lower);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(lower);
            //System.out.println("The random BigInteger = "+res);
        return res;
    }
    //@getXFromGroup to get an x element such that gcd(a,p)=1
    public static BigInteger getXFromGroup(BigInteger p){
        Random rand = new Random();
        int k = rand.nextInt(p.intValue() - (10^20)) + (10^20); // we dont check if p.intValue() is negative or not
        while(gcd(p.intValue(),k) != 1) {
            k = rand.nextInt(p.intValue() - (10^20)) + (10^20);
        }
        return BigInteger.valueOf(k);
    }

    public static BigInteger getPrimRoot(BigInteger p) {
        ArrayList<BigInteger> fact = new ArrayList<BigInteger>();
        BigInteger phi = p.subtract(BigInteger.ONE);
        fact.add(BigInteger.valueOf(2));
        fact.add(p.divide(BigInteger.valueOf(2)));
        for (BigInteger res = BigInteger.valueOf(2); res.compareTo(p) <= 0; res = res.add(BigInteger.ONE)) {
            boolean ok = true;
            for (int i = 0; i < fact.size() && ok; ++i) {
                BigInteger b = res.modPow(phi.divide(fact.get(i)), p);
                ok = (b.compareTo(BigInteger.ONE) != 0);
            }
            if (ok) return res;
        }
        return null;
    }
}
