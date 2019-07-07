package commands.game.component;

import drone.DroneBrowser;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.DroneUtils;

public class Take extends Command {
    public Take(String identifier) {
        super(identifier);
    }

    public Take(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        DroneBrowser db = DroneUtils.getSelectedDrone(sender, c, commandMap).getBrowser();

        if (args.length == 2) {
            c.sendMessage("you need to specfiy which Socket you want to swap with!");
            return;
        } else {
            db.swap(Integer.parseInt(args[2]));
            EmbedBuilder embedBuilder = new EmbedBuilder();
            db.toEmbed(embedBuilder);
            c.sendMessage(embedBuilder);
        }
    }
}
