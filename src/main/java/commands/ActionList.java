package commands;

import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.ExceptionLogger;
import potz.utils.commands.Command;
import util.CommandFuckedUpError;
import util.DroneUtils;

public class ActionList extends Command {


    public ActionList(String identifier) {
        super(identifier);
    }

    public ActionList(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        DroneStorage ds = DroneUtils.getStorageOrWarnUser(sender, c, commandMap);
        DroneUtils.checkStorage(c,ds);
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(sender);
        embedBuilder.setTitle("Available actions with: " + ds.getSelectedDrone().getIdentity());
        ds.getSelectedDrone().listActionsAsEmbeds(embedBuilder);

        c.sendMessage(embedBuilder).exceptionally(ExceptionLogger.get());
    }
}

