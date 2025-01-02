package nio;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * @author CaptainWang
 * @since 2025/1/2
 */
public class BlockingIOServer {
    public static void main(String[] args) {
        int port = 9888;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                // 1. 等待客户端连接（阻塞）
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getRemoteSocketAddress());

                // 2. 为每个连接创建一个线程
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream()
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);

                // Echo message back to client
                writer.println("Echo: " + message);

                if ("bye".equalsIgnoreCase(message)) {
                    System.out.println("Closing connection with " + clientSocket.getRemoteSocketAddress());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
