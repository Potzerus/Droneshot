package commands;

import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.CommandFuckedUpException;
import util.DroneUtils;

public class ToggleId extends Command {
    public ToggleId(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        try{
            DroneStorage ds=DroneUtils.getStorageOrWarnUser(sender,c,commandMap);
            ds.toggleShowId();
            c.sendMessage(ds.hasShowId()?"You can now see Drone ID's":"You can no longer see Drone ID's");
        }catch(CommandFuckedUpException e){

        }

    }
}
