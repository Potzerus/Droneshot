package drone.actions;


import drone.Drone;
import drone.blueprints.Blueprint;
import drone.component.Component;
import drone.component.Fabricator;
import drone.component.Socket;
import map.ResourceType;
import potz.utils.database.Char;

public class Fabricate implements Action {


    private final String name = "Fabricate";
    private Blueprint blueprint;
    private Drone executingDrone;
    private Fabricator fabricator;

    public Fabricate(Blueprint blueprint, Drone executingDrone, Fabricator fabricator) {
        this.blueprint = blueprint;
        this.executingDrone = executingDrone;
        this.fabricator = fabricator;
    }

    @Override
    public void run() {
        Component c = blueprint.build();
        Socket attacher = c.getFreeSocket(false);
        Char character = executingDrone.getParent().getOwner();

        //Check for 4 Failstates

        //No Free Sockets on Assembled Part
        if ((c.getFreeSocket(true) == null) && (c.getFreeSocket(false) == null))
            throw new ActionCouldNotExecuteError("No free sockets on Assembled Component!", character);
        //No Free Sockets on Assembling Part
        if (fabricator.getFreeSocket(true, true) == null && fabricator.getFreeSocket(false, true) == null)
            throw new ActionCouldNotExecuteError("No free sockets on Fabricating Component!", character);
        //No Free Plus socket on either Part
        if (fabricator.getFreeSocket(true, true) == null && c.getFreeSocket(true) == null)
            throw new ActionCouldNotExecuteError("Incompatible Sockets! you cannot connect minus and minus!", character);
        //No Free Minus socket on either Part
        if (fabricator.getFreeSocket(false, true) == null && c.getFreeSocket(false) == null)
            throw new ActionCouldNotExecuteError("Incompatible Sockets! you cannot connect plus and plus!", character);

        int[] availableResources=executingDrone.getFreshResources();
        int[] cost=blueprint.getCost();
        for (int i = 0; i < ResourceType.values().length; i++) {
            if(availableResources[i]<cost[i])
                throw new ActionCouldNotExecuteError("Insufficient Resources! Lacking:"+(cost[i]-availableResources[i])+ResourceType.values()[i].getName(),character);
        }


        //This just works
        if (attacher != null) {
            Socket attachee = fabricator.getFreeSocket(true, true);
            attachee.setLinked(attacher);
        } else {
            attacher = c.getFreeSocket(true);
            Socket attachee = fabricator.getFreeSocket(false, true);
            attachee.setLinked(attacher);
        }
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean repeats() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
