package drone.blueprints;

import drone.component.Component;

import java.util.function.Consumer;

//multiplies the output of a component with a certain factor. Can be < 1
public class OutputTrait extends Trait{

    OutputTrait(double factor) {
        Consumer<Component> consumer = component -> {
            //TODO: something with factor and component
        };
        effects.add(consumer);
    }

}
