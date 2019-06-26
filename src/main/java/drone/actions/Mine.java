package drone.actions;

import drone.Drone;
import drone.component.Component;
import drone.component.Socket;
import map.ResourceType;

public class Mine implements Action {

    //TODO: Check for Mapconditions to be met

    public Mine(){

    }

    @Override
    public void run(Drone d) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean repeats() {
        return false;
    }
}
