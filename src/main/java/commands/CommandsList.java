package commands;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.ExceptionLogger;
import potz.utils.commands.Command;

import java.util.Arrays;

public class CommandsList extends Command {


    public CommandsList(String identifier) {
        super(identifier);
    }

    public CommandsList(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(sender);
        embedBuilder.setTitle("List of available commands:");
        Command[] holder = new Command[commandMap.size()];
        int i=0;
        for (Command command : commandMap) {
            holder[i++]=command;
        }
        Arrays.sort(holder);
        for (Command command : holder) {
            embedBuilder.addField(command.getIdentifier(), command.getDescription());
        }
        c.sendMessage(embedBuilder).exceptionally(ExceptionLogger.get());
    }
}
