package io.natsusai.github.reactor.demo.concurrent;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author Kurenai
 * @since 2020-09-10 0:32
 */

public class Process implements Runnable {

    private final SocketChannel channel;
    private final ByteBuffer buffer;

    public Process(SocketChannel channel, ByteBuffer buffer) {
        this.channel = channel;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("Process thread: " + Thread.currentThread().getName());
        String msg = new String(buffer.array(), StandardCharsets.UTF_8);
        System.out.println("Accept msg: " + msg);
        System.out.println("---");
        try {
            channel.write(ByteBuffer.wrap("Accepted msg.".getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
