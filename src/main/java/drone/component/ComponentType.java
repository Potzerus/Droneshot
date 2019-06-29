package drone.component;

import map.ResourceType;
import util.DroneUtils;

import java.util.Arrays;
import java.util.function.Supplier;

public enum ComponentType {
    //TODO: Finish setting base costs
    STRUCTURE("Structure", DefaultComponent::new, DroneUtils.makeResourceArray(
            ResourceType.METAL.ordinal(), 10)
    ),

    CONTROL("Control", Control::new, DroneUtils.makeResourceArray(
            ResourceType.SILICON.ordinal(), 50,
            ResourceType.CRYSTALS.ordinal(), 50)
    ),

    GENERATOR("Generator", Generator::new, DroneUtils.makeResourceArray(
            ResourceType.CRYSTALS.ordinal(), 100)
    ),

    BATTERY("Battery", Battery::new, DroneUtils.makeResourceArray()
    ),

    HARVESTER("Harvester", Harvester::new, DroneUtils.makeResourceArray()
    ),

    STORAGE("Storage", Storage::new, DroneUtils.makeResourceArray()
    ),

    SCIENCE("Science", Science::new, DroneUtils.makeResourceArray()
    ),

    FABRICATOR("Fabricator", Fabricator::new, DroneUtils.makeResourceArray()
    ),

    COMPONENTSTORAGE("ComponentStorage", ComponentStorage::new, DroneUtils.makeResourceArray()
    ),

    MOVEMENT("Movement", Movement::new, DroneUtils.makeResourceArray()
    );

    private String name;
    private Supplier<Component> constructor;
    private int[] baseCost;

    ComponentType(String name, Supplier<Component> constructor, int[] baseCost) {
        this.name = name;
        this.constructor = constructor;
        this.baseCost = baseCost;
    }

    public String getName() {
        return name;
    }

    public Component getBlank() {
        return constructor.get();
    }

    public int[] genBaseCost() {
        return Arrays.copyOf(baseCost, baseCost.length);
    }
}
