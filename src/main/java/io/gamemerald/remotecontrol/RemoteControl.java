package io.gamemerald.remotecontrol;

import io.gamemerald.remotecontrol.ws.WebsocketClient;
import io.gamemerald.remotecontrol.ws.WebsocketServer;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.java_websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public final class RemoteControl extends JavaPlugin {
    private WebSocketClient wc;
    private WebsocketServer ws;
    private static String uri;
    public static final String p1 = "[Remote Control JS] ";
    public static final String p2 = "[Remote Control JS (Debug)] ";

    @Override
    public void onEnable() {
        try {
            new Setup().setupConfig(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        plog(p1 + "PLEASE DO NOT RELOAD", "info");
        plog(p1 + "Library: Java WebSockets | https://github.com/TooTallNate/Java-WebSocket", "info");
        plog(p1 + "Version 1.0", "info");
        plog(p1 + "Debug " + Setup.getCfg().getBoolean("dev.debug"), "info");
        uri = Setup.getCfg().getString("client.uri");

        if(Setup.getCfg().getBoolean("client.client")){
            try {
                WebSocketClient wc = new WebsocketClient(new URI(Setup.getCfg().getString("client.uri")));
                wc.connect();
                ((WebsocketClient) wc).getPlugin(this);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        if(Setup.getCfg().getBoolean("server.server")){
            int port = Setup.getCfg().getInt("server.port");

            try {
                ws = new WebsocketServer(port);
                ws.start();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
        if(Setup.getCfg().getBoolean("client.client")) {
            wc.close();
        }
        if(Setup.getCfg().getBoolean("server.server")){
            try {
                ws.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void plog(String msg, String level){
        if(level == "info"){
            System.out.println(ChatColor.RED + msg);
        }if(level == "debug"){
            System.out.println(ChatColor.YELLOW + msg);
        }
    }

    public static String getUri(){
        return uri;
    }
}
