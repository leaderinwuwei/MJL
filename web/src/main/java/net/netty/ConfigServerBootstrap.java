package net.netty;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CaptainWang
 * @since 2024/10/15
 */
public class ConfigServerBootstrap {

    public static final int WORKER_THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Logger log = LoggerFactory.getLogger("ConfigServerBootstrap");

    public void start(){
        int port = 8080;
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(WORKER_THREAD_COUNT);

//        final BizServerHandler bizServerHandler = new BizServerHandler();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new IdleStateHandler(10, 10, 0));
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(500 * 1024 * 1024));
//                            pipeline.addLast(bizServerHandler);
                        }
                    });
            log.info("start netty server, port:{}", port);
            // print internal state
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            StatusPrinter.print(lc);
            serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
//            log.error(String.format("start netty server error, port:%s", port), e);
        }


    }

    public static void main(String[] args) {
        ConfigServerBootstrap configServerBootstrap = new ConfigServerBootstrap();
        configServerBootstrap.start();
    }
}
