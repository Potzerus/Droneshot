package drone.blueprints;

import map.ResourceType;


public enum TraitType {
    DEFAULT(new Trait(new CostTrait(1), new OutputTrait(1))),       //doesn't do anything
    DOUBLEOUTPUT(new Trait(new CostTrait(2), new OutputTrait(2))),  //doubles the output, but also Cost
    HALVECOST(new Trait(new CostTrait(0.5), new OutputTrait(0.5))), //halves the cost, but also the output
    DOUBLESTORAGE(new Trait(new StorageTrait(2)));                  //Double Storage

    private static int[] cost = new int[ResourceType.values().length];
    public static final Trait COST_DOUBLE = new CostTrait(cost);
    private Trait trait;
    TraitType(Trait trait) {
        this.trait = trait;
    }

    public Trait getTrait() {
        return trait;
    }
}
