package com.sohu.mrd.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
/**
 *
 * 类HttpServer.java的实现描述：TODO 类实现描述
 * @author hongfengwang
 */
public class HttpServer extends Server {
    protected final InetSocketAddress addr;
    protected final ServerBootstrap bootstrap;
    protected final NioServerSocketChannelFactory factory;
    protected Channel ch;
    public HttpServer(String host, int port, Object userHandler) {
        this(new InetSocketAddress(host, port), userHandler);
    }
    public HttpServer(InetSocketAddress addr, Object userHandler) {
        this.addr = addr;
        this.factory = new NioServerSocketChannelFactory(Executors
                .newCachedThreadPool(), Executors.newCachedThreadPool());
        bootstrap = new ServerBootstrap(factory);
        bootstrap.setOption("reuseAddress", true);
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.setPipelineFactory(new HttpServerPipelineFactory(userHandler, false));
    }
    @Override
    public synchronized void start() throws IOException {
        ch = bootstrap.bind(addr);
    }
    @Override
    public synchronized void stop() {
        if (ch != null)
            ch.close().awaitUninterruptibly();
        factory.releaseExternalResources();
    }
}
