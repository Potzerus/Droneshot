package drone.component;

public class Storage extends DefaultComponent {

    private int storageCapacity;
    private int currentAmount;
    //TODO: Add a none Type and finish implementing this class
    //private ResourceEnumeration type;


    public Storage(int carryingSocketAmount, int carriedSocketAmount, int storageCapacity) {
        super(carryingSocketAmount, carriedSocketAmount,"Storage");
        this.storageCapacity = storageCapacity;

    }

    /**
     * param type type of resource that is being inserted or drained from this tank
     * @param amount amount of resource that is being inserted or drained from this tank
     * @return how much is still leftover to fill/drain from the original request
     */
    public int fillOrEmptyTank(
            /*Todo: Add ResourceEnumeration
            * ResourceEnumeration type,*/
            int amount){
        return amount;
    }

    public int[] getResource(){
        return new int[0];
    }

    @Override
    public String toString() {
        return super.toString()+currentAmount+"/"+storageCapacity;
    }
}
