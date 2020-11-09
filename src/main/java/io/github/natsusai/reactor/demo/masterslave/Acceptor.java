package io.github.natsusai.reactor.demo.masterslave;

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

    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;

    public Acceptor(ServerSocketChannel serverSocketChannel) throws IOException {
        this.serverSocketChannel = serverSocketChannel;
        selector = Selector.open();
        new Thread(new SubReactor(selector)).start();
    }


    @Override
    public void run() {
        try {
            System.out.println("Acceptor thread: " + Thread.currentThread().getName());
            SocketChannel channel = serverSocketChannel.accept();
            System.out.println("Accept connect: " + channel.getRemoteAddress());
            channel.configureBlocking(false);
            selector.wakeup();
            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
            selectionKey.attach(new WorkHandler(channel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
