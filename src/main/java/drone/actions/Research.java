package drone.actions;

import drone.Drone;
import drone.blueprints.Trait;
import drone.component.ComponentType;
import drone.component.Science;

public class Research extends Action{

    private Science researcher;
    private Trait researchingTrait;
    private ComponentType researchingComponent;
    private boolean researchesTrait;


    public Research(Science researcher, Drone executingDrone, Trait researching){
        super(executingDrone,"Research","Research new ComponentTypes or Traits!");
        this.researcher=researcher;
        researchingTrait=researching;
        researchesTrait=true;
    }

    public Research(Science researcher, Drone executingDrone, ComponentType researching){
        super(executingDrone,"Research","Research new ComponentTypes or Traits!");
        this.researcher=researcher;
        researchingComponent =researching;
        researchesTrait=false;
    }

    @Override
    public void run() {

    }

    //TODO:Model how its supposed to work
}
