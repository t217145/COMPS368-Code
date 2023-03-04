package aesdemo;

import java.security.*;
import java.io.*;

public class GenKey{
    public static void genKey(){
        try{
            FileOutputStream priFile = new FileOutputStream("C:\\Users\\Cyrus Cheng\\Desktop\\private.key");
            FileOutputStream pubFile = new FileOutputStream("C:\\Users\\Cyrus Cheng\\Desktop\\public.key");

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom rand = SecureRandom.getInstance("SHA1PRNG","SUN");
            keyGen.initialize(1024,rand);

            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey priKey = keyPair.getPrivate();
            PublicKey pubKey = keyPair.getPublic();

            byte[] priBuf = priKey.getEncoded();
            byte[] pubBuf = pubKey.getEncoded();

            priFile.write(priBuf);
            priFile.close();

            pubFile.write(pubBuf);
            pubFile.close();
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
    }//end of genKey
}