package no.lwb.base.io;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WeiBin Lin
 */
public class NettyServer {

    /**
     * 维护 chl 信息
     */
    private static final Map<Long, NioSocketChannel> chlMap = new ConcurrentHashMap<>(16);

    public static void putClientId(Channel channel, String clientId) {
//        channel.attr().set(clientId);
    }
}
