package no.lwb.base.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author WeiBin Lin
 */
@Slf4j
public class BIOServer extends Thread {

    private ServerSocket serverSocket;

    private BIOServer() {
        try {
            serverSocket = new ServerSocket(0);
        } catch (IOException e) {
            log.info("", e);
        }
    }

    private int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {

            while (true) {
                Socket socket = serverSocket.accept();
                // 利用线程池来管理工作，避免频繁创建、销毁线程的开销
                new Thread(() -> {
                    try (PrintWriter writer = new PrintWriter(socket.getOutputStream())){
                        writer.println("hello io");
                        writer.flush();
                    } catch (IOException e) {
                        // nothing will happen
                    }
                }).start();
            }
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public static void main(String[] args) throws Exception {
        BIOServer bioServer = new BIOServer();
        bioServer.start();
        try (Socket client = new Socket(InetAddress.getLocalHost(), bioServer.getPort())) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(log::info);
        }
    }
}
