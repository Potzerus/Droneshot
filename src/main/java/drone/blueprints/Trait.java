package drone.blueprints;

import drone.component.Component;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Trait {


    protected LinkedList<Consumer<Component>> conditionEffects = new LinkedList<>();
    protected LinkedList<Consumer<Component>> additiveEffects = new LinkedList<>();
    protected LinkedList<Consumer<Component>> multiplicativeEffects = new LinkedList<>();

    public Trait(Trait... traits) {
        for (Trait t : traits) {
            additiveEffects.addAll(t.getAdditiveEffects());
            multiplicativeEffects.addAll(t.getMultiplicativeEffects());
        }
    }

    public Trait addTrait(Trait t) {
        additiveEffects.addAll(t.getAdditiveEffects());
        multiplicativeEffects.addAll(t.getMultiplicativeEffects());
        return this;
    }

    public Trait addTraits(Trait... traits) {
        for (Trait t : traits) {
            additiveEffects.addAll(t.getAdditiveEffects());
            multiplicativeEffects.addAll(t.getMultiplicativeEffects());
        }
        return this;
    }


    public void applyTrait(Component comp) {
        for (Consumer c : additiveEffects) {
            c.accept(comp);
        }
        for (Consumer c : multiplicativeEffects) {
            c.accept(comp);
        }
    }

    public LinkedList<Consumer<Component>> getAdditiveEffects() {
        return additiveEffects;
    }

    public LinkedList<Consumer<Component>> getMultiplicativeEffects() {
        return multiplicativeEffects;
    }
}
