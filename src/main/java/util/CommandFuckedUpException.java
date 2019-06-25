package util;

import org.javacord.api.entity.channel.TextChannel;
import potz.utils.commands.Command;

public class CommandFuckedUpException extends Exception {
    CommandFuckedUpException(String ErrorMessage, TextChannel c) {
        super();
        c.sendMessage(ErrorMessage);
    }
}
