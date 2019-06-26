package drone.actions;

import drone.Drone;

public class Move implements Action {

    int maxAmount;

    public Move(int maxAmount){
        this.maxAmount = maxAmount;
    }

    @Override
    public void run(Drone d) {

    }

    @Override
    public String getDescription() {
        return "move";
    }

    @Override
    public boolean repeats() {
        return false;
    }
}
