package io.natsusai.github.reactor.demo.singleton;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kurenai
 * @since 2020-09-09 23:46
 */

public class WorkHandler implements Runnable {

    private final SocketChannel channel;

    public WorkHandler(SocketChannel channel) {
        this.channel = channel;
    }


    @Override
    public void run() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            String msg = new String(buffer.array(), StandardCharsets.UTF_8);
            System.out.println("Accept msg: " + msg);
            System.out.println("---");
            channel.write(ByteBuffer.wrap("Accepted msg.".getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
