package drone.component;


public class Fabricator extends ComponentStorage {



    public Fabricator(int carryingSocketAmount, int carriedSocketAmount, int storageSocketAmount) {
        super(carryingSocketAmount, carriedSocketAmount,storageSocketAmount);
        type=ComponentType.FABRICATOR;
        description="Fabricates new Drone Components";
    }

    @Override
    public ComponentType getType() {
        return type;
    }
}
