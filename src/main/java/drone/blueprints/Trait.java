package drone.blueprints;

import drone.component.Component;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Trait {

    protected LinkedList<Consumer<Component>> effects=new LinkedList<>();

    public Trait(Trait... traits) {
        for (Trait t : traits) {
            effects.addAll(t.getEffects());
        }
    }


    public void applyTrait(Component comp) {
        for (Consumer c : effects) {
            c.accept(comp);
        }
    }

    public LinkedList<Consumer<Component>> getEffects() {
        return effects;
    }
}
