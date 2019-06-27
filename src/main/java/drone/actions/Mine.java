package drone.actions;

import drone.Drone;
import map.ResourceType;
import map.Tile;

public class Mine implements Action {

    //TODO: Check for Mapconditions to be met
    Tile currentTile;
    int[] expectedResources;
    Drone executingDrone;

    public Mine(Tile currentTile,int[] expectedResources,Drone d){

    }

    @Override
    public void run() {
        int[] actuallyMinedResources=currentTile.extractResources(expectedResources);
        executingDrone.addResources(actuallyMinedResources);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean repeats() {
        return false;
    }
}
