import potz.utils.database.ServerStorage;
import potz.utils.database.State;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import potz.Utils;

public class Center {
    private static DiscordApi api;
    private static State state = new State();


    public static void main(String[] args) {
        api = new DiscordApiBuilder().setToken(Utils.getToken()).login().join();

        api.addMessageCreateListener(event -> {
            System.out.println(event.getMessageContent());
            if (event.getMessageContent().startsWith("^Toybox")) {
                String[] argus = Utils.parseArgsArray(event.getMessageContent());
                for (int i = 0; i < argus.length; i++) {
                    System.out.println(i + ": " + argus[i]);

                }
                System.out.println();
                if (event.getServer().isPresent() && event.getMessageAuthor().isBotOwner() && argus.length == 3 && argus[1].equals("enable")) {
                    long serverId = event.getServer().get().getId();
                    ServerStorage ss = state.getServer(serverId);
                    for (Modules m : Modules.values()) {
                        if (m.activate(ss, event, serverId, argus[2].toLowerCase()))
                            break;
                    }
                }
            }
        });

        System.out.println("Setup Finished " + api.createBotInvite());
    }

    public static DiscordApi getApi() {
        return api;
    }

    public static State getState() {
        return state;
    }
}
