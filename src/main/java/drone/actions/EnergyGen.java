package drone.actions;

import drone.Drone;
import drone.component.Generator;
import map.ResourceType;
import util.DroneUtils;

public class EnergyGen extends Action {


    private Generator generator;
    private Drone executingDrone;

    public EnergyGen(Generator generator, Drone executingDrone, int loops) {
        super(executingDrone,"Generate","Generates Energy");
        this.generator = generator;
        this.executingDrone = executingDrone;
    }

    @Override
    public void run() {
        int[] cost = generator.getUseCost();

        DroneUtils.chargeIfAffordable(cost, executingDrone);

        executingDrone.addResources(generator.getProductionArr());
    }

}
