package commands;

import drone.Drone;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.DroneUtils;

public class Scream extends Command {
    public Scream(String identifier) {
        super(identifier);
    }

    public Scream(String identifier,String description) {
        super(identifier,description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
            Drone d = DroneUtils.getSelectedDrone(sender, c, commandMap);
            d.setRunnable(()->d.getQueuedAction().scream(d,c));
            c.sendMessage("Success");
    }
}
