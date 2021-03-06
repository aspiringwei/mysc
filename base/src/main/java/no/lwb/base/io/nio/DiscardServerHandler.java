package no.lwb.base.io.nio;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author ixm.
 * @date 2018/7/27
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//        ByteBuf in = (ByteBuf) msg;
//        try {
////            while (in.isReadable()) { // (1)
////                System.out.print((char) in.readByte());
////                System.out.flush();
////            }
////            System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
//            // 回声 Echo Server
////            ctx.write(msg);
////            ctx.flush();
////            ctx.writeAndFlush(msg);
//        } finally {
////            ReferenceCountUtil.release(msg); // (2)
//        }
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }
}
