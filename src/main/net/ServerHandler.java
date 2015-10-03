package main.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Server handler
 * 
 * @since 15.06.2015 20:18:45
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 */
public class ServerHandler extends ChannelHandlerAdapter {
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
//                System.out.print((char) in.readByte());
//                System.out.flush();
                ctx.write(msg);
                ctx.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}