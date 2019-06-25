package commands;

import drone.Drone;
import drone.DroneStorage;
import drone.component.Component;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.ExceptionLogger;
import potz.utils.commands.Command;
import util.CommandFuckedUpException;
import util.DroneBrowser;
import util.DroneUtils;

public class Browse extends Command {

    public Browse(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        try {
            DroneStorage ds=DroneUtils.getStorageOrWarnUser(sender,c,commandMap);
            Drone d = DroneUtils.getSelectedDrone(sender, c, commandMap);
            DroneBrowser db = d.getBrowser();
            EmbedBuilder embedBuilder=new EmbedBuilder();
            if (args.length == 2) {
                embedBuilder.setTitle(d.getIdentity(ds.hasShowId()));
                embedBuilder.addField(db.getCurrent().getType().getName(),db.getCurrent().getDescription());
            }else if(args.length >=3){
                try{
                Component comp=db.moveTo(Integer.parseInt(args[2]));
                db.toEmbed(embedBuilder);
            }catch(NumberFormatException e){
                    switch(args[2]){
/*
                        case "list":
                            embedBuilder.addField("Accessible Components:",db.getAccessableComponentString());
                            break;
*/
                        case "reset":
                            db.reset();
                            break;
                        case "swap":
                            if(args.length==3) {
                                c.sendMessage("you need to specfiy which Socket you want to swap with!");
                                return;
                            }else
                            db.swap(Integer.parseInt(args[3]));
                            c.sendMessage("Successfully Swapped Components!");
                            break;
                        case "sockets":
                            db.toEmbed(embedBuilder,true);
                            break;
                    }
                }
            }
            c.sendMessage(embedBuilder).exceptionally(ExceptionLogger.get());
        } catch (CommandFuckedUpException e) {
        }
    }
}
