package util;

import org.javacord.api.entity.channel.TextChannel;
import potz.utils.commands.Command;

public class CommandFuckedUpError extends Error {
    CommandFuckedUpError(String ErrorMessage, TextChannel c) {
        super();
        c.sendMessage(ErrorMessage);
    }
}
