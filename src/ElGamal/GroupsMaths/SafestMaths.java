package src.ElGamal.GroupsMaths;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class SafestMaths {

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
