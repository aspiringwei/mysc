package no.lwb.base.io.nio;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author ixm.
 * @date 2018/7/27
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

//    private ByteBuf buf;
//
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) {
//        buf = ctx.alloc().buffer(4); // (1)
//    }
//
//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) {
//        buf.release(); // (1)
//        buf = null;
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf m = (ByteBuf) msg;
//        buf.writeBytes(m); // (2)
//        m.release();
//
//        if (buf.readableBytes() >= 4) { // (3)
//            long value = buf.readUnsignedInt();
//            System.out.println(value);
//            long currentTimeMillis = (value - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        }
        UnixTime m = (UnixTime) msg;
        System.out.println(m);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}