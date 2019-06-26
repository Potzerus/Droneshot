package drone.component;

public enum ComponentType {
    STRUCTURE("Structure"),
    CONTROL("Control"),
    STORAGE("Storage"),
    COMPONENTSTORAGE("ComponentStorage"),
    FABRICATOR("Fabricator"),
    MOVEMENT("Movement");

    private String name;
    ComponentType(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
