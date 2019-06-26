package drone.component;

import drone.Drone;
import drone.actions.Action;

public class Control extends DefaultComponent{

    Drone parent;


    public Control(String identifier, Action action, int plusSocketAmount, int minusSocketAmount) {
        super(identifier,action,plusSocketAmount, minusSocketAmount);
        type=ComponentType.CONTROL;
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
