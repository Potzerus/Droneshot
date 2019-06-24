package commands;

import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.ExceptionLogger;
import potz.utils.commands.Command;
import potz.utils.database.Char;

public class Check extends Command {

    public Check(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        Char character = commandMap.getServerStorage().getOrAddPlayer(sender.getId());
        DroneStorage droneStorage = (DroneStorage) character.getStat("droneStorage");
        if (droneStorage == null) {
            notRegisteredYetMessage(c);
            return;
        }
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor(sender);
            embedBuilder.setTitle(sender.getDisplayName(s) + "'s Drones");
            droneStorage.listDronesAsEmbeds(embedBuilder);
            c.sendMessage(embedBuilder).exceptionally(ExceptionLogger.get());
            //TODO: Add System to select a drone and examine it further -> DroneSurfer

    }

    private void notRegisteredYetMessage(TextChannel c) {
        c.sendMessage("You haven't used this bot before, please type ``" + commandMap.getModule().getPrefix() + " generate`` to get started!");
    }
}
