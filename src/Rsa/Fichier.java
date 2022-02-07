package src.Rsa;

import java.io.*;

public class Fichier {
    // Classe qui permet de gerer le format des fichiers textes

    // TODO : encode, decode vers un format correct RSA/EL GAMAL
    public static String getContentFichier(String fichier) throws FileNotFoundException{
        FileReader fr = new FileReader(fichier); 
        // "/Users/nael/Desktop/PROJET MATH_INFO/exemples/text1.txt"
        int i;
        String s ="";
        try {
            while((i=fr.read()) != -1 ){
                //System.out.print((char) i);
                s+=(char)i;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }

    public static byte[] stringToBytesArray(String a){
        return a.getBytes();
    }
    /*public static void main(String[] args) throws FileNotFoundException {
        String a = getContentFichier("/Users/nael/Desktop/PROJET MATH_INFO/exemples/text1.txt");
        byte[] By = stringToBytesArray(a);
        for(byte b:By)
            System.out.print(b);
        System.out.println();
    }*/
    
}
