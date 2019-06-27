package drone.blueprints;

import drone.component.Component;
import drone.component.Storage;

import java.util.function.Consumer;

public class StorageTrait extends Trait{

    StorageTrait(double factor) {
        Consumer<Component> consumer = component -> {
            Storage comp = (Storage)component;
            //TODO: comp.storageCapacity * factor
        };
        effects.add(consumer);
    }

}
