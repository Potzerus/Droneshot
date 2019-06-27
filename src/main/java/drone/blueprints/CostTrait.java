package drone.blueprints;

import drone.component.Component;
import map.ResourceType;

import java.util.Arrays;
import java.util.function.Consumer;

//adds some resources to the cost. factor can be < 1
public class CostTrait extends Trait{

    CostTrait(int[] cost) {
        Consumer<Component> consumer = component -> {
            //TODO: adds cost to component.cost
        };
        effects.add(consumer);
    }

    CostTrait(double factor) {
        Consumer<Component> consumer = component -> {
            //TODO: multiplies component.cost by factor
        };
        effects.add(consumer);
    }


}
