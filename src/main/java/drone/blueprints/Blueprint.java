package drone.blueprints;

import drone.component.Component;
import drone.component.ComponentType;

import java.util.Arrays;

public class Blueprint {

    private int[] cost;
    private ComponentType type;
    private Trait trait;


    public Blueprint(int[] cost, ComponentType type, Trait trait) {
        this.cost = cost;
        this.type = type;
        this.trait = trait;
    }

    public int[] getCost() {
        return Arrays.copyOf(cost,cost.length);
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
