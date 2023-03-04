/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashingdemo;

import java.security.MessageDigest;
import java.util.Scanner;

/**
 *
 * @author cyrus
 */
public class HashingDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            //Get the input
            System.out.print("Input your password : ");
            Scanner _in = new Scanner(System.in);
            String pwd = _in.nextLine();
            
            //Hash the pwd
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pwd.getBytes("utf-8"));
            byte[] hash = md.digest();
            
            //convert to string
            StringBuffer sb = new StringBuffer();
            for(byte b : hash){
                sb.append(String.format("%02x", b));
            }
            
            System.out.println(sb.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
