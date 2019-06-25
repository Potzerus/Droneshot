package commands;

import drone.Drone;
import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.ExceptionLogger;
import potz.utils.commandMaps.CommandMap;
import potz.utils.commands.Command;
import potz.utils.database.Char;
import util.CommandFuckedUpException;
import util.DroneUtils;

public class Check extends Command {

    public Check(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        try {
            DroneStorage droneStorage = DroneUtils.getStorageOrWarnUser(sender, c, commandMap);
            if (args.length == 2) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setAuthor(sender);
                embedBuilder.setTitle(sender.getDisplayName(s) + "'s Drones");
                droneStorage.listDronesAsEmbeds(embedBuilder);
                c.sendMessage(embedBuilder).exceptionally(ExceptionLogger.get());
                //TODO: Add System to select a drone and examine it further -> DroneBrowser
            } else if (args[2].equals("drone")) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setAuthor(sender);
                embedBuilder.setTitle(droneStorage.getSelectedDrone().getIdentity(droneStorage.hasShowId()));
                if (args.length == 3) {
                    droneStorage.getSelectedDrone().toEmbedField(embedBuilder);
                } else if (args.length == 4) {
                    droneStorage.getDrone(args[3]).toEmbedField(embedBuilder);
                }
                c.sendMessage(embedBuilder).exceptionally(ExceptionLogger.get());
            }
        } catch (CommandFuckedUpException e) {
            e.printStackTrace();
        }
    }

}
