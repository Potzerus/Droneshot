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
    private int loops;

    public Mine(Harvester harvester, Drone executingDrone,Tile currentTile) {
        this.currentTile=currentTile;
        this.harvester=harvester;
        this.executingDrone=executingDrone;
        name = "Mine";
        description="Mines the current Tile for resources";
    }

    @Override
    public void run() {
        DroneUtils.chargeIfAffordable(harvester.getUseCost(),executingDrone);

        executingDrone.addResources(currentTile.extractResources(harvester.getHarvestCapacity()));
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean repeats() {
        return --loops>=0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setLoops(int amount) {
        this.loops=amount;
    }
}
