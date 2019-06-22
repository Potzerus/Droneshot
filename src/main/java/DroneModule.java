import commands.Test;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import potz.utils.Module;
import potz.utils.database.ModuleStorage;
import potz.utils.database.State;

public class DroneModule extends Module {

    public DroneModule(String prefix, DiscordApi api, Server server, State state) {
        super(prefix, api, server, state);
        commandMap.registerAll(
            new Test("test")
        );
    }

    @Override
    public String getIdentifier() {
        return "drone";
    }

    @Override
    public ModuleStorage genStorage() {
        return null;
    }
}