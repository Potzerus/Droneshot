package drone.blueprints;

import drone.component.Component;
import drone.component.Storage;
import map.ResourceType;

import java.util.function.Consumer;

public class RequirementTrait extends Trait {


    public RequirementTrait(int[][] conditions) {
        Consumer<Component> consumer = component -> {
            int[][] componentStuff=new int[ResourceType.values().length][3];
            componentStuff[0]=component.getBuildCost();
            componentStuff[1]=component.getUseCost();
            for (int i = 0; i < conditions[0].length; i++) {

                switch (conditions[i][1]) {
                    case -1:

                    case 0:

                    case 1:

                }
            }


        };
        conditionEffects.add(consumer);
    }
}
