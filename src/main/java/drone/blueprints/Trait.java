package drone.blueprints;

import drone.component.Component;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Trait {

    protected LinkedList<Consumer<Component>> effects;

    public Trait(Trait...traits) {
        for(Trait t : traits) {
            for(Consumer<Component> c : t.getEffects()) {

            }
        }
    }


    public void applyTrait(Component comp) {
        for(Consumer c : effects) {
            c.accept(comp);
        }
    }

    public LinkedList<Consumer<Component>> getEffects() {
        return effects;
    }
}
