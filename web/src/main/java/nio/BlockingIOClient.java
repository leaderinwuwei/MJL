package nio;
import java.io.*;
import java.net.Socket;
/**
 * @author CaptainWang
 * @since 2025/1/2
 */
public class BlockingIOClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9888;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true) {
                System.out.print("Enter message: ");
                message = consoleReader.readLine();

                // Send message to server
                writer.println(message);

                // Receive response from server
                String response = reader.readLine();
                System.out.println("Server response: " + response);

                if ("bye".equalsIgnoreCase(message)) {
                    System.out.println("Closing connection to server");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}