package io.github.natsusai.reactor.demo.masterslave;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kurenai
 * @since 2020-09-09 23:46
 */

public class WorkHandler implements Runnable {

    private final SocketChannel channel;
    private final ExecutorService pool = Executors.newFixedThreadPool(8);

    public WorkHandler(SocketChannel channel) {
        this.channel = channel;
    }


    @Override
    public void run() {
        try {
            System.out.println("WorkHandler thread: " + Thread.currentThread().getName());
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            pool.execute(new Process(channel, buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
