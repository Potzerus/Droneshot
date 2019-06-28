package drone.blueprints;

import drone.component.Component;
import map.ResourceType;

import java.util.Arrays;
import java.util.function.Consumer;

//adds some resources to the cost. factor can be < 1
public class UseCostTrait extends Trait {

    UseCostTrait(int[] cost) {
        Consumer<Component> consumer = component -> {
            int[] useCost = component.getUseCost();
            for (int i = 0; i < useCost.length; i++) {
                useCost[i] = useCost[i] + cost[i];
            }
            component.setUseCost(useCost);
        };
        additiveEffects.add(consumer);
    }

    UseCostTrait(double factor) {
        Consumer<Component> consumer = component -> {
            int[] useCost = component.getUseCost();
            for (int i = 0; i < useCost.length; i++) {
                useCost[i] = (int) (useCost[i] * factor);
            }
            component.setUseCost(useCost);
        };
        multiplicativeEffects.add(consumer);
    }

    UseCostTrait(double[] factors) {
        Consumer<Component> consumer = component -> {
            int[] useCost = component.getUseCost();
            for (int i = 0; i < useCost.length; i++) {
                useCost[i] = (int) (useCost[i] * factors[i]);
            }
            component.setUseCost(useCost);
        };
        multiplicativeEffects.add(consumer);
    }




}
