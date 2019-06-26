package commands;

import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.DroneUtils;

public class Rename extends Command {

    public Rename(String identifier) {
        super(identifier);
    }

    public Rename(String identifier,String description) {
        super(identifier,description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
            DroneStorage ds = DroneUtils.getStorageOrWarnUser(sender, c, commandMap);
            DroneUtils.checkStorage(c,ds);
            StringBuilder sb=new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                sb.append(args[i]);
                sb.append(' ');
            }
            ds.getSelectedDrone().setName(sb.toString());
            c.sendMessage("Successfully renamed "+ds.getSelectedDrone().getIdentity(ds.hasShowId()));
    }
}
