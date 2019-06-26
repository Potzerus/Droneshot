package commands;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;

public class Test extends Command {

    public Test(String identifier) {
        super(identifier);
    }

    public Test(String identifier,String description) {
        super(identifier,description);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        c.sendMessage("Test Successful");
    }
}
