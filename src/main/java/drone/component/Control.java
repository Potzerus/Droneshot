package drone.component;

import drone.Drone;

public class Control extends DefaultComponent{

    Drone owner;

    public Control(int carryingSocketAmount, int carriedSocketAmount) {
        super(carryingSocketAmount, carriedSocketAmount,"Control");
    }
}
