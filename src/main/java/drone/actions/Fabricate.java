package drone.actions;


import drone.Drone;
import drone.blueprints.Blueprint;
import drone.component.Component;
import drone.component.Fabricator;
import drone.Socket;
import potz.utils.database.Char;
import util.DroneUtils;

public class Fabricate extends Action {


    private Fabricator fabricator;
    private Drone executingDrone;
    private Blueprint blueprint;
    private int loops;

    public Fabricate(Fabricator fabricator, Drone executingDrone, Blueprint blueprint) {
        this.blueprint = blueprint;
        this.executingDrone = executingDrone;
        this.fabricator = fabricator;
        name = "Fabricate";
        description = "Creates a component";

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

        int[] cost = c.getBuildCost();
        for (int i = 0; i < cost.length; i++) {
            cost[i] += fabricator.getUseCost()[i];
        }

        DroneUtils.chargeIfAffordable(cost, executingDrone);

        //This just works
        //From four preconditions being wrong we already know that one of these has to work
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
        return description;
    }

    @Override
    public boolean repeats() {
        return --loops >= 0;
    }

    public void setLoops(int loops) {
        this.loops = loops;
    }

    @Override
    public String getName() {
        return name;
    }
}
