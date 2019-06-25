package drone.component;

import drone.Drone;

public class Control extends DefaultComponent{

    Drone parent;


    public Control(int carryingSocketAmount, int carriedSocketAmount) {
        super(carryingSocketAmount, carriedSocketAmount,"Control");
        type=ComponentType.BRAIN;
    }

    public void setParent(Drone parent) {
        this.parent = parent;
    }

    @Override
    public ComponentType getType() {
        return type;
    }
}
