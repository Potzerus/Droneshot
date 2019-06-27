package drone.actions;

import drone.Drone;
import org.javacord.api.entity.channel.TextChannel;

public abstract class Action {
    protected String name = "Action";
    protected String description = "Default Action";
    protected int loops;

    public abstract void run();

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public boolean repeats(){
        return --loops>=0;
    }

    public void setLoops(int amount){
        loops=amount;
    }

    public static Action getIdle() {
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

            @Override
            public String getName() {
                return "Idle";
            }

        };
    }
}
