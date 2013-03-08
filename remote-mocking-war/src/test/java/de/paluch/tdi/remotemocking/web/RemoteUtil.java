package de.paluch.tdi.remotemocking.web;

import java.io.IOException;
import java.net.ServerSocket;

public class RemoteUtil {

    public static int findFreePort() throws IOException {
        ServerSocket server = new ServerSocket(0);
        int port = server.getLocalPort();
        server.close();
        return port;
    }
}