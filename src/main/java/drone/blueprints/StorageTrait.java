package drone.blueprints;

import drone.component.Component;
import drone.component.Storage;

import java.util.function.Consumer;

public class StorageTrait extends Trait{

    StorageTrait(int addition) {
        Consumer<Component> consumer = component -> {
            Storage comp = (Storage)component;
            comp.setStorageCapacity((addition+comp.getStorageCapacity()));
        };
        additiveEffects.add(consumer);
    }

    StorageTrait(double factor) {
        Consumer<Component> consumer = component -> {
            Storage comp = (Storage)component;
            comp.setStorageCapacity((int)(factor*comp.getStorageCapacity()));
        };
        multiplicativeEffects.add(consumer);
    }

}
