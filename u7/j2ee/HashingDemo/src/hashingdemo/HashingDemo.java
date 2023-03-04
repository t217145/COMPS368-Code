package hashingdemo;

import java.security.MessageDigest;

public class HashingDemo {

    private static final String SECRET = "A123456b";
    
    public static void main(String[] args) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String str = SECRET;
            md.update(str.getBytes("utf-8"));
            byte[] hash = md.digest();

            StringBuffer sb = new StringBuffer();
            for(byte b : hash){
                sb.append(String.format("%02x", b));
            }

            System.out.println("Hash output for \"" + SECRET + "\" : " + sb.toString());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
