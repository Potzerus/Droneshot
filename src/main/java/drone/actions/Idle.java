package drone.actions;

import drone.Drone;

public class Idle implements Action{


    @Override
    public void run(Drone d) {

    }

    @Override
    public String getDescription() {
        return "Does Nothing";
    }
}
