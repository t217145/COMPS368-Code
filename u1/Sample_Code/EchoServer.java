
import java.io.*;
import java.net.*;

public class EchoServer {

    public static void main(String[] args) {

        try (
                ServerSocket serverSocket = new ServerSocket(8001)) {
            System.out.println("Server started ");

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(
                                        socket.getInputStream()));) {
                    System.out.println("Connection accepted");
                    String userInput;
                    while ((userInput = in.readLine()) != null) {
                        System.out.println("echo: " + userInput);
                        out.println(userInput);
                    }
                } finally {
                    System.out.println("Connection closed");
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
