package launcher;

import drone.Drone;
import drone.DroneStorage;
import drone.actions.Action;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.permission.PermissionType;
import potz.utils.database.Char;
import potz.utils.database.ServerStorage;
import potz.utils.database.State;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import potz.Utils;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Center {
    private static DiscordApi api;
    private static State state = new State();
    private static String prefix = "droneshot";
    private static Long[] botAuthors = new Long[]{125660719323676672L, 277367997327212544L, 215541019138064384L};
    private static Long[] defaultServers = new Long[]{532907700326105108L, 377546781732503553L};
    private static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    public static TextChannel getLast() {
        return last;
    }

    private static TextChannel last;


    public static void main(String[] args) {
        api = new DiscordApiBuilder().setToken(Utils.getToken()).login().join();

        api.updateActivity("Type d! commands to see available commands");

        service.scheduleAtFixedRate(() -> {
            //This code block will be executed immediately, and then again every 60 minutes
            for (ServerStorage ss : state) {
                for (Char character : ss) {
                    DroneStorage ds = (DroneStorage) character.getOrAddStat("droneStorage", new DroneStorage(character));
                    for (Drone d : ds) {
                        Action a = d.getQueuedAction();
                        a.run();
                        if (!a.repeats()) {
                            d.resetQueuedAction();
                        }
                        d.getRunnable().run();
                        d.setRunnable(() -> {
                        });

                    }
                }
            }
        }, 1, 1, TimeUnit.MINUTES);

        api.addMessageCreateListener(event -> {
            if (!event.getMessageAuthor().asUser().get().isBot()) {
                last = event.getChannel();
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
                            || Arrays.asList(botAuthors).contains(event.getMessageAuthor().getId())
                            || event.getServer().get().isAdmin(event.getMessageAuthor().asUser().get())
                            || event.getServer().get().isOwner(event.getMessageAuthor().asUser().get()))

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
            }
        });

        for (long l : defaultServers) {
            state.getServer(l).addModule(new DroneModule("d!", api, api.getServerById(l).get(), state));
        }
        System.out.println("Setup Finished " + api.createBotInvite());
    }

    public static DiscordApi getApi() {
        return api;
    }

    public static State getState() {
        return state;
    }

    public static void loadState() {
        state = state.loadFile();
    }
}
