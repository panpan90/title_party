package com.sohu.mrd.netty;
import java.io.IOException;
public abstract class Server {
    public abstract void start() throws IOException;
    public abstract void stop();
}
