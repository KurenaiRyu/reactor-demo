package io.github.natsusai.reactor.demo;

import io.github.natsusai.reactor.demo.masterslave.Reactor;

/**
 * @author Kurenai
 * @since 2020-09-10 0:01
 */

public class ReactorServer {
    public static void main(String[] args) {
        Reactor reactor = new Reactor(9090);
        reactor.run();
    }
}
