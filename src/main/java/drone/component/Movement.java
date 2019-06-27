package drone.component;

import drone.actions.Action;

public class Movement extends DefaultComponent {

    public Movement(String identifier, Action action, int plusSocketAmount, int minusSocketAmount) {
        super(identifier,action,plusSocketAmount, minusSocketAmount);
        type = ComponentType.MOVEMENT;
        description="Movement Tool for Landbound walking";
    }

    public Movement(){
        super();
        type=ComponentType.MOVEMENT;
    }
}
