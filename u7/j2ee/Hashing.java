import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Hashing {
	public static String bytes2HexAscii(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}

	public static String sha256(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(data.getBytes("utf-8"));
		byte[] hashBytes = md.digest();
		return bytes2HexAscii(hashBytes);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Input (empty line to quit): ");
			String data = scanner.nextLine();
			if (data.length() == 0)
				break;
			System.out.println("SHA-256: " + sha256(data));
		}
	}
}