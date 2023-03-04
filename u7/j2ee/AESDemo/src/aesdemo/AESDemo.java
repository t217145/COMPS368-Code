package aesdemo;

import java.util.Scanner;

public class AESDemo {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Generating the key");
        GenKey.genKey();
        System.out.println("Press any key continue..");
        in.nextLine();
        
        System.out.println("Encrypting");
        ASEDemoSender.start();
        System.out.println("Press any key continue..");
        in.nextLine();        
        
        System.out.println("Decryption");
        ASEDemoReceiver.start();     
    }
    
}
