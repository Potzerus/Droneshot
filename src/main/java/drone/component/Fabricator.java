package drone.component;


public class Fabricator extends ComponentStorage {



    public Fabricator(int carryingSocketAmount, int carriedSocketAmount, int plusStorage,int minusStorage) {
        super(carryingSocketAmount, carriedSocketAmount,plusStorage,minusStorage);
        type=ComponentType.FABRICATOR;
        description="Fabricates new Drone Components";
    }

    @Override
    public ComponentType getType() {
        return type;
    }
}
