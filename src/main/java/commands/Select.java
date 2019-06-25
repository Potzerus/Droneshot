package commands;

import drone.Drone;
import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;

public class Select extends Command {

    public Select(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        DroneStorage ds = DroneStorage.getStorage(commandMap.getServerStorage().getOrAddPlayer(sender.getId()));
        if (ds.isEmpty()) {
            c.sendMessage("You don't have any drones to select!");
        } else if (args.length == 2) {
            c.sendMessage("Select a drone to be the active drone:");
            EmbedBuilder embedBuilder = new EmbedBuilder();
            ds.listDronesAsEmbeds(embedBuilder);
            c.sendMessage(embedBuilder);
        } else if (args.length == 3) {
            Drone newSelection = ds.getDrone(args[2]);
            if (newSelection == null) {
                c.sendMessage("You do now own a drone with this Name or id");
                return;
            }
            ds.selectDrone(newSelection);
            c.sendMessage("Successfully made "+newSelection.getIdentity()+" the new Selected Drone!");

        }
    }
}
