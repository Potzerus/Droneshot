package commands.game.action;

import drone.Drone;
import drone.actions.Action;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import util.DroneUtils;

public class QueueAction extends Command {
    public QueueAction(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        Drone d=DroneUtils.getSelectedDrone(sender,c,commandMap);
        Action[] actions=d.getActions();
        EmbedBuilder embedBuilder=new EmbedBuilder();
        if (args.length==2){
            for (int i = 0, actionsLength = actions.length; i < actionsLength; i++) {
                Action a = actions[i];
                embedBuilder.addField(i+':'+a.getName(), a.getDescription());
            }
            c.sendMessage(embedBuilder);
        }else if(args.length==3){
            d.setRunnable(()->actions[Integer.parseInt(args[2])].run());
        }
    }
}
