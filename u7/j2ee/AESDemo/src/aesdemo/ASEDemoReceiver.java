package aesdemo;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.Cipher;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ASEDemoReceiver {
    public static void start(){
        System.out.println("Start decryption");

        try {
            byte[] pByte = Files.readAllBytes(Paths.get("C:\\Users\\Cyrus Cheng\\Desktop\\encrypted.txt"));
            byte[] dByte = decryptTxt(pByte);
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Cyrus Cheng\\Desktop\\decrypted.txt");
            fos.write(dByte);
        } catch(Exception e){
            e.printStackTrace(); 
        }

        System.out.println("Finish decryption");
    }

    private static byte[] decryptTxt(byte[] eTxt) throws Exception{
        FileInputStream fin = new FileInputStream("C:\\Users\\Cyrus Cheng\\Desktop\\private.key");
        byte[] keyBuf = new byte[fin.available()];
        fin.read(keyBuf);
        fin.close();

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBuf);
        KeyFactory keyFact = KeyFactory.getInstance("RSA");
        PrivateKey priKey = keyFact.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
		
	return cipher.doFinal(eTxt);
    }	
}
