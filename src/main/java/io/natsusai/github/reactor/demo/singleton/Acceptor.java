package io.natsusai.github.reactor.demo.singleton;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Kurenai
 * @since 2020-09-09 23:42
 */

public class Acceptor implements Runnable {

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }


    @Override
    public void run() {
        try {
            SocketChannel channel = serverSocketChannel.accept();
            System.out.println("Accept connect: " + channel.getRemoteAddress());
            channel.configureBlocking(false);
            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
            selectionKey.attach(new WorkHandler(channel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
