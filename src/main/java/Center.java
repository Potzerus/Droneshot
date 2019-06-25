import org.javacord.api.entity.permission.PermissionType;
import potz.utils.database.ServerStorage;
import potz.utils.database.State;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import potz.Utils;

import java.util.Arrays;

public class Center {
    private static DiscordApi api;
    private static State state = new State();
    private static String prefix = "droneshot";
    private static Long[] botAuthors = new Long[]{125660719323676672L, 277367997327212544L};


    public static void main(String[] args) {
        api = new DiscordApiBuilder().setToken(Utils.getToken()).login().join();

        api.addMessageCreateListener(event -> {
            System.out.println(event.getMessageContent());
            if (event.getMessageContent().startsWith(prefix)) {
                String[] argus = event.getMessageContent().split(" ");

                //Console Info
                for (int i = 0; i < argus.length; i++) {
                    System.out.println(i + ": " + argus[i]);
                }
                System.out.println();

                if (event.getMessageAuthor().asUser().isPresent() && event.getServer().isPresent()

                        && (Utils.hasPermission(event.getMessageAuthor().asUser().get(), event.getServer().get(), PermissionType.MANAGE_CHANNELS, PermissionType.ADMINISTRATOR)
                        || Arrays.asList(botAuthors).contains(event.getMessageAuthor().getId()))

                        && argus.length == 3
                        && argus[1].equals("enable")
                        && argus[2].equals("drone")) {
                    long serverId = event.getServer().get().getId();
                    ServerStorage ss = state.getServer(serverId);
                    if (!ss.hasActiveModule("drone")) {
                        ss.addModule(new DroneModule("d!", api, event.getServer().get(), state));
                        event.getChannel().sendMessage("Module activated!");
                    } else
                        event.getChannel().sendMessage("This Module is already Active!");

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
