package drone.actions;

import drone.Drone;
import drone.component.Harvester;
import map.ResourceType;
import map.Tile;
import potz.utils.database.Char;
import util.DroneUtils;

public class Mine extends Action {

    private Harvester harvester;
    private Drone executingDrone;
    private Tile currentTile;

    public Mine(Harvester harvester, Drone executingDrone,Tile currentTile) {
        super(executingDrone,"Mine","Mines the current Tile for resources");
        this.currentTile=currentTile;
        this.harvester=harvester;
        this.executingDrone=executingDrone;
    }

    @Override
    public void run() {
        DroneUtils.chargeIfAffordable(harvester.getUseCost(),executingDrone);

        executingDrone.addResources(currentTile.extractResources(harvester.getHarvestCapacity()));
    }
}
