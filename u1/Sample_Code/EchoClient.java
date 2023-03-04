
import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String[] args) throws IOException {

        String userInput;

        try (
                Socket echoSocket = new Socket("127.0.0.1", 8001);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));) {
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
        }

    }
}
