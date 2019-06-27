package drone.blueprints;

import drone.component.Component;
import drone.component.ComponentType;

import java.util.Arrays;

public class Blueprint {

    private int[] buildCost;
    private int[] useCost;
    private ComponentType type;
    private Trait trait;


    public Blueprint(ComponentType type, Trait trait) {
        this.type = type;
        this.trait = trait;
        Component benchmark= build();
        this.buildCost =benchmark.getBuildCost();
        this.useCost =benchmark.getUseCost();
    }

    public int[] getUseCost() {
        return Arrays.copyOf(useCost, useCost.length);
    }

    public int[] getBuildCost() {
        return Arrays.copyOf(buildCost,buildCost.length);
    }

    public ComponentType getType() {
        return type;
    }

    public Component build(){
        Component c=type.getBlank();
        trait.applyTrait(c);
        return c;
    }
}
