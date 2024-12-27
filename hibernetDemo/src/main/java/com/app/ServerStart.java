package com.app;

import com.server.config.AppServer;

public class ServerStart {
    public static void main(String[] args) {
        // DB config and connection

        // check packages
        AppServer.crateServer(4000,"hibernetDemo","com.userB","/api","/v2");
    }
}
