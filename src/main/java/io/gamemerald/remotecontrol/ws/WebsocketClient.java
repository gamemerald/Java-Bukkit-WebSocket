package io.gamemerald.remotecontrol.ws;

import io.gamemerald.remotecontrol.RemoteControl;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebsocketClient extends WebSocketClient {
    private URI uri;
    private RemoteControl plugin;

    public WebsocketClient(URI serverUri) {
        super(serverUri);
        uri = serverUri;
    }

    public void getPlugin(JavaPlugin plugin) {
        this.plugin = (RemoteControl) plugin;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected on " + uri);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);

//        Bukkit.getScheduler().runTask(plugin, new Runnable() {
//            @Override
//            public void run() {
//                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
//            }
//        });
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
