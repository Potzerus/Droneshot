package util;

import drone.Drone;
import drone.DroneBrowser;
import drone.DroneStorage;
import drone.actions.Action;
import drone.actions.ActionCouldNotExecuteError;
import drone.component.*;
import map.ResourceType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;
import potz.utils.commandMaps.CommandMap;
import potz.utils.database.Char;

public class DroneUtils {
    private static int idTick;

    public static Drone buildDefaultDrone() {
        Drone d = new Drone(new Control("Brain",Action.getIdle(),0, 1));
        d.getRootComponent().setParent(d);
        DroneBrowser db=d.getBrowser();
        db.getCurrent().getFreeSocket(false,false).attach(new DefaultComponent("Chassis",Action.getIdle(),10, 6));
        Component c=db.moveTo(0);
        c.getFreeSocket(true,false).attach(new Fabricator("Fabricator",Action.getIdle(),1, 1, 1,0));
        for (int i = 0; i < 3; i++) {
            c.getFreeSocket(true,false).attach(new Storage("DefaultChest",Action.getIdle(),0, 1, 5,30));
        }
        for (int i = 0; i < 4; i++) {
            c.getFreeSocket(false,false).attach(new Movement("DefaultLeg",Action.getIdle(),1, 0));
        }
        db.reset();
        return d;
    }

    public static Drone quickBuild(int numFabs, int numStor, int numLeg) {
        Drone d = new Drone(new Control("Brain", Action.getIdle(),0, 1));
        DroneBrowser db = d.getBrowser();
        db.getCurrent().getFreeSocket(false,false).attach(new DefaultComponent("Chassis",Action.getIdle(),numFabs + numStor + 1, numLeg));
        Component c = db.moveTo(0);
        for (int i = 0; i < numFabs; i++) {
            c.getFreeSocket(true,false).attach(new Fabricator("DefaultFab",Action.getIdle(),1, 1, 1,0));
        }
        for (int i = 0; i < numStor; i++) {
            c.getFreeSocket(true,false).attach(new Storage("DefaultChest",Action.getIdle(),0, 1, 5,30));
        }
        for (int i = 0; i < numLeg; i++) {
            c.getFreeSocket(false,false).attach(new Movement("DefaultLeg",Action.getIdle(),1, 0));
        }
        db.reset();
        return d;

    }

    public static DroneStorage getStorageOrWarnUser(User sender, TextChannel c, CommandMap commandMap) throws CommandFuckedUpError {
        Char character = commandMap.getServerStorage().getOrAddPlayer(sender.getId());
        DroneStorage droneStorage = DroneStorage.getStorage(character);
        if (droneStorage == null) {
            throw new CommandFuckedUpError("You don't have any Drones, please type ``" + commandMap.getModule().getPrefix() + " generate`` to get started!",c);
        }
        return droneStorage;
    }

    public static Drone getSelectedDrone(User sender,TextChannel c,CommandMap commandMap)throws CommandFuckedUpError {
        DroneStorage ds=getStorageOrWarnUser(sender,c,commandMap);
        checkStorage(c,ds);
        return ds.getSelectedDrone();
    }

    public static void checkStorage(TextChannel c, DroneStorage droneStorage) throws CommandFuckedUpError {
        if (droneStorage.isEmpty()) {
            throw new CommandFuckedUpError("You don't have any Drones!", c);
        }
    }

    public static int genId() {
        return idTick++;
    }

    public static void chargeIfAffordable(int[] cost, Drone executingDrone) {
        int[] availableResources=executingDrone.getFreshResources();
        for (int i = 0; i < ResourceType.values().length; i++) {
            if(availableResources[i]+cost[i]<0)
                throw new ActionCouldNotExecuteError("Insufficient Resources! Lacking:"+(cost[i]+availableResources[i])+ResourceType.values()[i].getName(),executingDrone.getParent().getOwner());
        }
        executingDrone.addResources(cost);
    }
}
