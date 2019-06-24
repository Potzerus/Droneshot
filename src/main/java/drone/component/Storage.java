package drone.component;

import map.ResourceType;

public class Storage extends DefaultComponent {

    private int storageCapacity;
    private int currentAmount;
    //TODO: Add a none Type and finish implementing this class
    private ResourceType type = ResourceType.NONE;


    public Storage(int carryingSocketAmount, int carriedSocketAmount, int storageCapacity) {
        super(carryingSocketAmount, carriedSocketAmount, "Storage");
        this.storageCapacity = storageCapacity;

    }

    /**
     * param type type of resource that is being inserted or drained from this tank
     *
     * @param amount amount of resource that is being inserted or drained from this tank
     * @return how much is still leftover to fill/drain from the original request
     */
    public int fillOrEmptyTank(ResourceType type, int amount) {
        if (this.type == ResourceType.NONE || this.type == type) {
            this.type = type;
            currentAmount += amount;
            if (currentAmount < 0) {
                amount = currentAmount;
                currentAmount = 0;
                this.type = ResourceType.NONE;
            } else if (currentAmount > storageCapacity) {
                amount = currentAmount - storageCapacity;
                currentAmount = storageCapacity;
            }
        }
        return amount;
    }

    public int[] getResource() {
        return new int[0];
    }

    @Override
    public String toString() {
        return "Storage: " + type.getName() + ": " + currentAmount + "/" + storageCapacity;
    }
}
