package drone.actions;


import drone.Drone;
import drone.blueprints.Blueprint;

public class Fabricate implements Action {


    private final String name="Fabricate";
    private Blueprint blueprint;
    private Drone executingDrone;

    public Fabricate(Blueprint blueprint, Drone executingDrone) {
        this.blueprint = blueprint;
        this.executingDrone = executingDrone;
    }

    @Override
    public void run() {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean repeats() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
