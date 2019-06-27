package drone.component;

public class Harvester extends DefaultComponent {

    int[] harvestCapacity;

    public Harvester() {
        type=ComponentType.HARVESTER;
    }

    public int[] getHarvestCapacity() {
        return harvestCapacity;
    }

    public void setHarvestCapacity(int[] harvestCapacity) {
        this.harvestCapacity = harvestCapacity;
    }

}
