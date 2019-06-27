package drone.actions;

import drone.Drone;
import drone.component.Generator;
import util.DroneUtils;

public class EnergyGen extends Action {


    private Generator generator;
    private Drone executingDrone;
    private int loops;

    public EnergyGen(Generator generator, Drone executingDrone, int loops) {
        this.generator = generator;
        this.executingDrone = executingDrone;
        this.loops = loops;
        name = "Generate";
        description = "Generates Energy";
    }

    @Override
    public void run() {
        int[] cost = generator.getUseCost();

        DroneUtils.chargeIfAffordable(cost, executingDrone);

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean repeats() {
        return --loops >= 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setLoops(int amount) {
        loops = amount;
    }
}
