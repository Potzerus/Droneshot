package drone.actions;

import drone.Drone;
import drone.component.Movement;

public class Move extends Action {

    private int maxAmount;
    private Movement mover;


    public Move(Movement mover, Drone executingDrone, int maxAmount) {
        super(executingDrone,"Move","Move to a different Tile");
        this.maxAmount = maxAmount;
        this.mover=mover;
    }

    @Override
    public void run() {

    }


}

