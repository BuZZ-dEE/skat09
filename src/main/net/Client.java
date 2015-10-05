package main.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * Client 
 * 
 * @since 16.06.2015 22:33:04
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public final class Client implements IClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    // Sleep 5 seconds before a reconnection attempt.
    static final int RECONNECT_DELAY = Integer.parseInt(System.getProperty("reconnectDelay", "5"));
    // Reconnect when the server sends nothing for 10 seconds.
    static final int READ_TIMEOUT = Integer.parseInt(System.getProperty("readTimeout", "10"));

    private static final ClientHandler handler = new ClientHandler();

    public static void main(String[] args) throws Exception {
        configureBootstrap(new Bootstrap()).connect();
    }

    private static Bootstrap configureBootstrap(Bootstrap b) {
        return configureBootstrap(b, new NioEventLoopGroup());
    }

    static Bootstrap configureBootstrap(Bootstrap b, EventLoopGroup g) {
        b.group(g)
         .channel(NioSocketChannel.class)
         .remoteAddress(HOST, PORT)
         .handler(new ChannelInitializer<SocketChannel>() {
             @Override
             public void initChannel(SocketChannel ch) throws Exception {
                 ch.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT, 0, 0), handler);
             }
         });

        return b;
    }

    static void connect(Bootstrap b) {
        b.connect().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.cause() != null) {
                    handler.startTime = -1;
                    handler.println("Failed to connect: " + future.cause());
                }
            }
        });
    }
}
