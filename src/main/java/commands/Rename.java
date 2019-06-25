package commands;

import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.CommandFuckedUpException;
import util.DroneUtils;

public class Rename extends Command {

    public Rename(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        try {
            DroneStorage ds = DroneUtils.getStorageOrWarnUser(sender, c, commandMap);
            DroneUtils.checkStorage(c,ds);
            StringBuilder sb=new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                sb.append(args[i]);
                sb.append(' ');
            }
            ds.getSelectedDrone().setName(sb.toString());
            c.sendMessage("Successfully renamed "+ds.getSelectedDrone().getIdentity(ds.hasShowId()));
        } catch (CommandFuckedUpException e) {

        }
    }
}
