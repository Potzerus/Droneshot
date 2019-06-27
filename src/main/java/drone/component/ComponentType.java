package drone.component;

public enum ComponentType {
    STRUCTURE("Structure",DefaultComponent.class),
    CONTROL("Control",Control.class),
    STORAGE("Storage",Storage.class),
    COMPONENTSTORAGE("ComponentStorage",ComponentStorage.class),
    FABRICATOR("Fabricator",Fabricator.class),
    MOVEMENT("Movement",Leg.class);

    private String name;
    ComponentType(String name,Class c){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
