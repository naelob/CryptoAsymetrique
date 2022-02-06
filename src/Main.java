package src;
public class Main {
    
    public static void main(String[] args){
        GenKeys keyPair = new GenKeys(1024);
        String message  = "Salut crypte moi";
        System.out.println("Message clair : " + message);
        String msgChiffre = keyPair.chiffrement(message);
        System.out.println("Message chiffré : " + msgChiffre);
        System.out.println("Message dechiffré : " + keyPair.dechiffrement(msgChiffre));

    }
}
