package drone.component;

import java.util.function.Supplier;

public enum ComponentType {
    STRUCTURE("Structure", DefaultComponent::new),
    CONTROL("Control",Control::new),
    BATTERY("Battery",Battery::new),
    STORAGE("Storage",Storage::new),
    COMPONENTSTORAGE("ComponentStorage",ComponentStorage::new),
    FABRICATOR("Fabricator",Fabricator::new),
    MOVEMENT("Movement",Leg::new);

    private String name;
    private Supplier<Component> constructor;
    ComponentType(String name, Supplier<Component> constructor){
        this.name=name;
        this.constructor=constructor;
    }

    public String getName() {
        return name;
    }

    public Component getBlank(){
        return constructor.get();
    }
}
