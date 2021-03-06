package commands.game.drones;

import drone.Drone;
import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.ExceptionLogger;
import potz.utils.commands.Command;
import util.DroneUtils;

public class Check extends Command {

    public Check(String identifier,String description) {
        super(identifier,description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
            DroneStorage droneStorage = DroneUtils.getStorageOrWarnUser(sender, c, commandMap);
            if (args.length == 2) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setAuthor(sender);
                embedBuilder.setTitle(sender.getDisplayName(s) + "'s Drones");
                droneStorage.listDronesAsEmbeds(embedBuilder);
                c.sendMessage(embedBuilder).exceptionally(ExceptionLogger.get());
            } else if (args[2].equals("drone")) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setAuthor(sender);
                embedBuilder.setTitle(droneStorage.getSelectedDrone().getIdentity());
                if (args.length == 3) {
                    droneStorage.getSelectedDrone().toEmbedField(embedBuilder);
                } else if (args.length == 4) {
                    droneStorage.getDrone(args[3]).toEmbedField(embedBuilder);
                }
                c.sendMessage(embedBuilder).exceptionally(ExceptionLogger.get());
            }
    }

}
