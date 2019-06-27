package drone.component;

import java.util.function.Supplier;

public enum ComponentType {
    STRUCTURE("Structure", DefaultComponent::new),
    CONTROL("Control",Control::new),
    Generator("Generator",Generator::new),
    BATTERY("Battery",Battery::new),
    HARVESTER("Harvester",Harvester::new),
    STORAGE("Storage",Storage::new),
    FABRICATOR("Fabricator",Fabricator::new),
    COMPONENTSTORAGE("ComponentStorage",ComponentStorage::new),
    MOVEMENT("Movement", Movement::new);

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
