package commands.game.drones;

import drone.Drone;
import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.CommandFuckedUpError;
import util.DroneUtils;

public class Select extends Command {

    public Select(String identifier,String description) {
        super(identifier,description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {

        try {
            DroneStorage ds = DroneUtils.getStorageOrWarnUser(sender, c, commandMap);
            if (ds != null) {
                if (args.length == 2) {
                    c.sendMessage("Select a drone to be the active drone:");
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    ds.listDronesAsEmbeds(embedBuilder);
                    c.sendMessage(embedBuilder);
                } else if (args.length == 3) {
                    Drone newSelection = ds.getDrone(args[2]);
                    if (newSelection == null) {
                        c.sendMessage("You do now own a drone with this Name or Id");
                        return;
                    }
                    ds.selectDrone(newSelection);
                    c.sendMessage("Successfully made " + newSelection.getIdentity() + " the new Selected Drone!");
                }
            }
        }catch (CommandFuckedUpError e){
            c.sendMessage("You do not have any available drones!");
        }
    }
}