import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import potz.utils.Module;
import potz.utils.database.ServerStorage;
import potz.utils.database.State;

import java.lang.reflect.InvocationTargetException;

public enum Mod {

    private static final String MESSAGE_MODULE_ALREADY_ACTIVE = "This Module is already active on this server!";
    private static final String MESSAGE_MODULE_ACTIVATION_SUCCESS = " successfully activated";


    private String identifier, prefix;
    private Class type;

    Mod(String identifier, String prefix, Class activated) {
        this.identifier = identifier;
        this.prefix = prefix;
        type = activated;
    }

    public boolean activate(ServerStorage ss, MessageCreateEvent event, long serverId, String identifier) {
        if (identifier.equals(this.identifier)) {
            if (Center.getState().getServer(serverId).hasActiveModule(identifier)) {
                event.getChannel().sendMessage(MESSAGE_MODULE_ALREADY_ACTIVE);
            } else {
                try {
                    ss.addModule((Module) type.getConstructor(String.class, DiscordApi.class, Server.class, State.class)
                            .newInstance(prefix, Center.getApi(), event.getServer().get(), Center.getState()));
                    event.getChannel().sendMessage(identifier + MESSAGE_MODULE_ACTIVATION_SUCCESS);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else
            return false;
    }
}
