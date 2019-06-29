package drone.actions;

import drone.Drone;

public class Idle extends Action{
    public Idle(Drone executingDrone) {
        super(executingDrone, "Idle", "Does Nothing");
    }

    @Override
    public void run() {

    }
}
