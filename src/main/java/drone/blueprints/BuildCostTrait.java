package drone.blueprints;

import drone.component.Component;
import map.ResourceType;

import java.util.Arrays;
import java.util.function.Consumer;

//adds some resources to the cost. factor can be < 1
public class BuildCostTrait extends Trait {

    BuildCostTrait(int[] cost) {
        Consumer<Component> consumer = component -> {
            int[] buildCost = component.getBuildCost();
            for (int i = 0; i < buildCost.length; i++) {
                buildCost[i] = buildCost[i] + cost[i];
            }
            component.setBuildCost(buildCost);
        };
        additiveEffects.add(consumer);
    }

    BuildCostTrait(double factor) {
        Consumer<Component> consumer = component -> {
            int[] buildCost = component.getBuildCost();
            for (int i = 0; i < buildCost.length; i++) {
                buildCost[i] = (int) (buildCost[i] * factor);
            }
            component.setBuildCost(buildCost);
        };
        multiplicativeEffects.add(consumer);
    }


}
