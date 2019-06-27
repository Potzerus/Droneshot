package drone.component;


import drone.actions.Action;

public class Fabricator extends ComponentStorage {



    public Fabricator(String identifier, Action action, int carryingSocketAmount, int carriedSocketAmount, int plusStorage, int minusStorage) {
        super(identifier,action,carryingSocketAmount, carriedSocketAmount,plusStorage,minusStorage);
        type=ComponentType.FABRICATOR;
        description="Fabricates new Drone Components";
    }

    public Fabricator(){
        super();
        type=ComponentType.FABRICATOR;
    }

    @Override
    public ComponentType getType() {
        return type;
    }
}
