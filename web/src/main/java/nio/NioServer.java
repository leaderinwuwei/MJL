package nio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**在 Linux 系统中，JRE 使用 epoll 提供高效的多路复用能力。
 * 通过 epoll_create、epoll_ctl 和 epoll_wait 的封装，
 * Java NIO 实现了跨平台的 Selector，使得开发者可以专注于业务逻辑，而不需要关心底层的系统调用细节
 * 。这种设计不仅提高了性能，还简化了编程复杂度，是现代 Java 高性能 IO 的核心基础。
 * <p>
 * 文件描述符在 epoll 中的作用
 * 在 epoll 或类似的 IO 多路复用机制中，文件描述符是核心的管理对象：
 *<p>
 * 注册文件描述符
 * 将需要监听的文件描述符（如 socket）注册到 epoll。
 * 监听文件描述符的事件
 * 通过 epoll_wait 监控哪些文件描述符发生了事件（如可读、可写）。
 * 通过文件描述符进行操作
 * 根据触发的事件，调用 read() 或 write() 等系统调用对对应的文件描述符进行操作。
 * 例如，当你通过 Selector 注册一个 SocketChannel 时，底层会将该通道的文件描述符注册到 epoll，并监听其事件。
 *<p>
 * 文件描述符的生命周期
 * 分配
 *<p>
 * 通过 open()、socket() 等系统调用创建资源时分配。
 * 使用
 * 使用 read(fd) 或 write(fd) 等操作文件描述符。
 * 关闭
 * 使用 close(fd) 释放文件描述符，告诉内核资源可以回收。
 * 未及时关闭文件描述符可能导致资源泄漏，耗尽系统能提供的文件描述符数量。
 * @author CaptainWang
 * @since 2025/1/2
 */
public class NioServer {
    private static final Logger log = LoggerFactory.getLogger(NioServer.class);

    public static void main(String[] args) throws IOException {
        // 1. 创建 Selector 和 Channel
        Selector selector = Selector.open();// 打开文件描述符 注册创建的epoll（linux）实例中
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(9889));
        serverChannel.configureBlocking(false); // 设置为非阻塞模式
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);//接收

        System.out.println("Server started on port 9889...");

        while (true) {
            // 2. 监控事件
            log.info("wowowo");
            if (selector.select() == 0) {
                continue; // 如果没有事件，继续循环
            }

            // 3. 获取事件集
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove(); // 必须手动移除，防止重复处理

                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = ssc.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("Accepted new connection from " + clientChannel.getRemoteAddress());

                    // 发送欢迎消息
                    clientChannel.write(ByteBuffer.wrap("Welcome to NioServer!\n".getBytes()));
                } else if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = clientChannel.read(buffer);
                    if (bytesRead == -1) {
                        System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
                        clientChannel.close();
                    } else {
                        buffer.flip();
                        String message = new String(buffer.array(), 0, bytesRead).trim();
                        System.out.println("Received: " + message + " from " + clientChannel.getRemoteAddress());

                        // 回显消息
                        String response = "Echo: " + message + "\n";
                        clientChannel.write(ByteBuffer.wrap(response.getBytes()));
                    }
                }
            }
        }
    }
}