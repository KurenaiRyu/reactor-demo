package io.github.natsusai.reactor.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;
import java.util.Scanner;

/**
 * @author Kurenai
 * @since 2020-09-09 23:26
 */

public class ReactorClient {

    public static void main(String[] args) {

        try {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 9090));
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            InputStream is = socket.getInputStream();
                            byte[] bytes = new byte[1024];
                            is.read(bytes);
                            System.out.println(new String(bytes));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            while (true) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    socket.getOutputStream().write(s.getBytes(StandardCharsets.UTF_8));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
