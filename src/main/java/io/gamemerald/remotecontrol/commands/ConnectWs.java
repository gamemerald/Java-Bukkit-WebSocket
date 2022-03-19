package io.gamemerald.remotecontrol.commands;

import io.gamemerald.remotecontrol.RemoteControl;
import io.gamemerald.remotecontrol.Setup;
import io.gamemerald.remotecontrol.ws.WebsocketClient;
import io.gamemerald.remotecontrol.ws.WebsocketServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.net.URISyntaxException;

public class ConnectWs implements CommandExecutor {
    JavaPlugin plugin;

    public void getPlugin(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(Setup.getCfg().getBoolean("client.client")){
            try {
                WebsocketClient wc = new WebsocketClient(new URI(RemoteControl.getUri()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }

        return false;
    }
}
