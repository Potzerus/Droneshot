package launcher;

import commands.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import potz.utils.Module;
import potz.utils.commands.Echo;
import potz.utils.database.ModuleStorage;
import potz.utils.database.State;

public class DroneModule extends Module {

    public DroneModule(String prefix, DiscordApi api, Server server, State state) {
        super(prefix, api, server, state);
        commandMap.registerAll(
                new Check("check",
                        "Used by itself to see your drones, and with ``drone`` to see details about your selected Drone"),
                new Generate("generate",
                        "Generates a starting drone, if you already have one you need to add ``reset`` to reset your current drones in exchange for a default one"),
                new Rename("rename",
                        "Renames your currently Selected Drone"),
                new Select("select",
                        "Lets you select a drone from your owned ones, enter either its ID or its name. ``"+prefix+" check`` to see your current Drones"),
                new Browse("browse",
                        "Used to take a more thorough look into the makeup of your Drone"),
                new ToggleId("toggleid",
                        "Toggles wether you will see your Drone's Id's or not"),
                new Scream("scream",
                        "Testing Method please ignore"),
                new CommandsList("commands",
                        "Shows the list of bot commands"),
                new ActionList("actions",
                        "Shows all available actions with the currently selected drone"),
                new Swap("swap",
                        "Lets you swap a Component with the currently held one"),
                new Take("take",
                        "Lets you take a Component if you arent holding one already, swaps them otherwise"),
                new Echo("degenerate",
                        "Displays Information concerning degeneracy",
                        "https://cdn.discordapp.com/attachments/403467603034767360/593784840575385600/iu.png")
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
