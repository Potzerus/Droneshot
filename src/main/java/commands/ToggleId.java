package commands;

import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.DroneUtils;

public class ToggleId extends Command {
    public ToggleId(String identifier) {
        super(identifier);
    }

    public ToggleId(String identifier,String description) {
        super(identifier,description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
            DroneStorage ds=DroneUtils.getStorageOrWarnUser(sender,c,commandMap);
            ds.toggleShowId();
            c.sendMessage(ds.hasShowId()?"You can now see Drone ID's":"You can no longer see Drone ID's");

    }
}
