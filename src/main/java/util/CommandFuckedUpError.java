package util;

import org.javacord.api.entity.channel.TextChannel;
import launcher.Center;

public class CommandFuckedUpError extends Error {
    public CommandFuckedUpError(String ErrorMessage, TextChannel c) {
        super();
        c.sendMessage(ErrorMessage);
    }
    public CommandFuckedUpError(String ErrorMessage) {
        super();
        Center.getLast().sendMessage(ErrorMessage);
    }
}
