package net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 特性	    多路复用	         NIO	             Netty
 * 抽象层次	操作系统原生机制	Java 标准库	         高级框架
 * 复杂度	高	            中	                 低
 * 开发效率	较低	            一般	                 高
 * 性能	    操作系统决定	    取决于代码实现和 JVM	 高度优化
 * 适用场景	底层通信开发	    轻量级高并发网络应用	 企业级高并发网络应用
 *
 * @author CaptainWang
 * @since 2024/10/15
 */
public class ConfigServerBootstrap {

    public static final int WORKER_THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Logger log = LoggerFactory.getLogger(ConfigServerBootstrap.class);

    public void start() {
        int port = 8080;
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(WORKER_THREAD_COUNT);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new IdleStateHandler(10, 10, 0)); // Idle detection
                            pipeline.addLast(new HttpServerCodec()); // HTTP codec
                            pipeline.addLast(new HttpObjectAggregator(50 * 1024 * 1024)); // Aggregate large messages
                            pipeline.addLast(new SimpleChannelInboundHandler<Object>() { // Custom handler
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    log.info("Received: {}", msg);
                                    ctx.writeAndFlush("Response: Hello from Netty!");
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                    log.error("Error occurred: ", cause);
                                    ctx.close();
                                }
                            });
                        }
                    });

            log.info("Starting Netty server on port {}", port);
            ChannelFuture future = serverBootstrap.bind(port).sync();
            log.info("Server started successfully.");

            // Shutdown gracefully on server close
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("Server interrupted: ", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            log.info("Server shut down.");
        }
    }

    public static void main(String[] args) {
        new ConfigServerBootstrap().start();
    }
}

