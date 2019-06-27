package drone.actions;

import drone.Drone;
import map.ResourceType;
import map.Tile;

public class Mine implements Action {

    //TODO: Check for Mapconditions to be met
    private Tile currentTile;
    private int[] expectedResources;
    private Drone executingDrone;
    private final String name = "Mine";
    private String description="Mines the current Tile for resources";

    public Mine(Tile currentTile, int[] expectedResources, Drone executingDrone) {
        this.currentTile=currentTile;
        this.expectedResources=expectedResources;
        this.executingDrone=executingDrone;
    }

    @Override
    public void run() {
        int[] actuallyMinedResources = currentTile.extractResources(expectedResources);
        int[] addedResources = new int[actuallyMinedResources.length];
        System.arraycopy(actuallyMinedResources, 0, addedResources, 0, addedResources.length);
        executingDrone.addResources(addedResources);
        boolean refund = false;
        for (int i : addedResources) {
            if (i < 0) {
                refund = true;
                break;
            }
        }
        if (refund) {
            for (int i = 0; i < addedResources.length; i++) {
                addedResources[i] = addedResources[i] - actuallyMinedResources[i];
            }
            executingDrone.addResources(addedResources);
        } else
            currentTile.addResources(actuallyMinedResources);
    }

    @Override
    public String getDescription() {
        return description;
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
