package no.lwb.base.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author WeiBin Lin
 */
@Slf4j
public class NIOServer extends Thread {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    NIOServer() {
        try {
            // 1. Pipe.open()-> ServerSocketChannel.open() bind 2. addWakeupSocket
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            log.info("Server is listening now...");
        } catch (IOException e) {
            //
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                // 阻塞等待的 channel
                selector.select();
                log.info("selected");
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeySet.iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    try (SocketChannel socketChannel = ((ServerSocketChannel)selectionKey.channel()).accept()){
                        socketChannel.write(Charset.defaultCharset().encode("hello nio"));
                    }
                    selectionKeyIterator.remove();
                }
            }
        } catch (IOException e) {
            log.error("", e);
        }
    }


    public static void main(String[] args) throws Exception {
        NIOServer nioServer = new NIOServer();
        nioServer.start();

        try (SocketChannel socketChannel =
                     SocketChannel.open(new InetSocketAddress(InetAddress.getLocalHost(), 8888))) {
            log.info("connected");
            TimeUnit.SECONDS.sleep(5);
            Socket socket = socketChannel.socket();
            TimeUnit.SECONDS.sleep(2);
            log.info("create socket");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader.lines().forEach(log::info);
        }
    }
}
