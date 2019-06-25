package drone.component;

import map.ResourceType;

public class Storage extends DefaultComponent {

    private int storageCapacity;
    private int currentAmount;
    //TODO: Add a none Type and finish implementing this class
    private ResourceType storedType = ResourceType.NONE;


    public Storage(int carryingSocketAmount, int carriedSocketAmount, int storageCapacity) {
        super(carryingSocketAmount, carriedSocketAmount, "Storage");
        this.storageCapacity = storageCapacity;
        type =ComponentType.STORAGE;
        description="Used to Store Materials";
    }

    /**
     * param storedType storedType of resource that is being inserted or drained from this tank
     *
     * @param amount amount of resource that is being inserted or drained from this tank
     * @return how much is still leftover to fill/drain from the original request
     */
    public int fillOrEmptyTank(ResourceType type, int amount) {
        if (this.storedType == ResourceType.NONE || this.storedType == type) {
            this.storedType = type;
            currentAmount += amount;
            if (currentAmount <= 0) {
                amount = currentAmount;
                currentAmount = 0;
                this.storedType = ResourceType.NONE;
            } else if (currentAmount > storageCapacity) {
                amount = currentAmount - storageCapacity;
                currentAmount = storageCapacity;
            }else
                amount=0;
        }
        return amount;
    }

    public int[] getResource() {
        int[] resources=new int[ResourceType.values().length];
        resources[storedType.ordinal()]=currentAmount;
        return resources;
    }

    @Override
    public String getSocketString() {
        return "Storage: " + storedType.getName() + ": " + currentAmount + "/" + storageCapacity;
    }
}
