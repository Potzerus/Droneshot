package drone.actions;

import drone.Drone;
import org.javacord.api.entity.channel.TextChannel;

public interface Action {

    void run(Drone d);

    String getDescription();

    boolean repeats();

    default void scream(Drone d, TextChannel c){
        c.sendMessage(getDescription());
    }
}
