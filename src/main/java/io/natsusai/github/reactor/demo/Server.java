package io.natsusai.github.reactor.demo;

import io.natsusai.github.reactor.demo.masterslave.Reactor;

/**
 * @author Kurenai
 * @since 2020-09-10 0:01
 */

public class Server{
    public static void main(String[] args) {
        Reactor reactor = new Reactor(9090);
        reactor.run();
    }
}
