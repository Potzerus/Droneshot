package drone.component;

import drone.Drone;

public class Control extends DefaultComponent{

    Drone parent;


    public Control(int plusSocketAmount, int minusSocketAmount) {
        super(plusSocketAmount, minusSocketAmount,"Control");
        type=ComponentType.BRAIN;
        description="The Component responsible for Controlling the Drone";
    }

    public void setParent(Drone parent) {
        this.parent = parent;
    }

    @Override
    public ComponentType getType() {
        return type;
    }
}
