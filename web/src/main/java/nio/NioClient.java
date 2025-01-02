package nio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author CaptainWang
 * @since 2025/1/2
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            // 创建 SocketChannel 并连接到服务器
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 9889);
            if (!socketChannel.connect(serverAddress)) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("Connecting to server...");
                }
            }

            System.out.println("Connected to server!");

            // 创建一个线程来接收服务器的响应
            new Thread(() -> {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                try {
                    while (true) {
                        buffer.clear();
                        int bytesRead = socketChannel.read(buffer);
                        if (bytesRead > 0) {
                            buffer.flip();
                            System.out.println("Server: " + new String(buffer.array(), 0, bytesRead));
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed by server.");
                }
            }).start();

            // 主线程用于发送消息到服务器
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                socketChannel.write(ByteBuffer.wrap(message.getBytes()));
            }

            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
