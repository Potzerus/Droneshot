package drone.actions;

import drone.Drone;

public interface Action {

    void run(Drone d);

    String getDescription();

    boolean repeats();
}
