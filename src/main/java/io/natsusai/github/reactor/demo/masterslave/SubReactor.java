package io.natsusai.github.reactor.demo.masterslave;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author Kurenai
 * @since 2020-09-10 0:55
 */

public class SubReactor implements Runnable {

    private final Selector selector;

    public SubReactor(Selector selector) {
        this.selector = selector;
    }


    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                System.out.println("SubReactor's selector: " + selector.toString() + ", thread: " + Thread.currentThread().getName());
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    dispatch(selectionKey);
                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable attachment = (Runnable) selectionKey.attachment();
        attachment.run();
    }


}
