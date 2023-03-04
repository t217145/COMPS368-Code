package aesdemo;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.Cipher;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ASEDemoSender {
    public static void start(){
        System.out.println("Start encryption");

        try {
            byte[] pByte = Files.readAllBytes(Paths.get("C:\\Users\\Cyrus Cheng\\Desktop\\plainTxt.txt"));
            byte[] eByte = encrypt(pByte);
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Cyrus Cheng\\Desktop\\encrypted.txt");
            fos.write(eByte);
        } catch(Exception e){
            e.printStackTrace(); 
        }

        System.out.println("Finish encryption");
    }

    private static byte[] encrypt(byte[] pByte) throws Exception{
        /*get the public key from the file and read it into a byte array*/
        FileInputStream fin = new FileInputStream("C:\\Users\\Cyrus Cheng\\Desktop\\public.key");
        byte[] keyBuf = new byte[fin.available()];
        fin.read(keyBuf);
        fin.close();

        /*retrive the public key object by using KeyFactory and X509EncodedKeySpec*/
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBuf);
        KeyFactory keyFact = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFact.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        return cipher.doFinal(pByte) ;
    }	
}
