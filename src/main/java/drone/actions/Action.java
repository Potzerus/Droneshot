package drone.actions;

import drone.Drone;
import org.javacord.api.entity.channel.TextChannel;

public interface Action {

    void run();

    String getDescription();

    boolean repeats();

    default void scream(Drone d, TextChannel c) {
        c.sendMessage("I have no mouth but I must Scream");
    }

    static Action getIdle() {
        return new Action() {

            @Override
            public void run() {

            }

            @Override
            public String getDescription() {
                return "Does Nothing";
            }

            @Override
            public boolean repeats() {
                return true;
            }
        };
    }
}
