package commands;

import drone.Drone;
import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.DroneUtils;

public class Select extends Command {

    public Select(String identifier) {
        super(identifier);
    }

    public Select(String identifier,String description) {
        super(identifier,description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
            DroneStorage ds = DroneUtils.getStorageOrWarnUser(sender, c, commandMap);
            if (ds != null) {
                if (args.length == 2) {
                    c.sendMessage("Select a drone to be the active drone:");
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    ds.listDronesAsEmbeds(embedBuilder);
                    c.sendMessage(embedBuilder);
                } else if (args.length >= 3) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        sb.append(args[i]);
                        sb.append(" ");
                    }

                    Drone newSelection = ds.getDrone(sb.toString());
                    if (newSelection == null) {
                        c.sendMessage("You do now own a drone with this Name or Id");
                        return;
                    }
                    ds.selectDrone(newSelection);
                    c.sendMessage("Successfully made " + newSelection.getIdentity(ds.hasShowId()) + " the new Selected Drone!");

                }
            }
    }
}