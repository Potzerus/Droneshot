package drone.component;

import drone.actions.Action;

public class Leg extends DefaultComponent {

    public Leg(String identifier, Action action, int plusSocketAmount, int minusSocketAmount) {
        super(identifier,action,plusSocketAmount, minusSocketAmount);
        type = ComponentType.MOVEMENT;
        description="Movement Tool for Landbound walking";
    }

    public Leg(){
        super();
        type=ComponentType.MOVEMENT;
    }
}
