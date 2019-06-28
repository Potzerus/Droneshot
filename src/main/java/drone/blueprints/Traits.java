package drone.blueprints;

import map.ResourceType;


public enum Traits {

    DEFAULT(new Trait(new BuildCostTrait(1), new OutputTrait(1))),       //doesn't do anything
    DOUBLEOUTPUT(new Trait(new BuildCostTrait(2), new OutputTrait(2))),  //doubles the output, but also Cost
    HALVECOST(new Trait(new BuildCostTrait(0.5), new OutputTrait(0.5))), //halves the cost, but also the output
    DOUBLESTORAGE(new Trait(new StorageTrait(2.0)));                  //Double Storage

    private static int[] cost = new int[ResourceType.values().length];
    private Trait trait;
    Traits(Trait trait) {
        this.trait = trait;
    }

    public Trait getTrait() {
        return trait;
    }
}
