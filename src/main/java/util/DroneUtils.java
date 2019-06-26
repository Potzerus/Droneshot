package util;

import drone.Drone;
import drone.DroneBrowser;
import drone.DroneStorage;
import drone.actions.Idle;
import drone.component.*;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;
import potz.utils.commandMaps.CommandMap;
import potz.utils.database.Char;

public class DroneUtils {
    private static int idTick;

    public static Drone buildDefaultDrone() {
        Drone d = new Drone(new Control("Brain",new Idle(),0, 1));
        d.getRootComponent().setParent(d);
        DroneBrowser db=d.getBrowser();
        db.getCurrent().getFreeSocket(false,false).attach(new DefaultComponent("Chassis",new Idle(),10, 4));
        Component c=db.moveTo(0);
        c.getFreeSocket(true,false).attach(new Fabricator("Fabricator",new Idle(),1, 1, 1,0));
        for (int i = 0; i < 3; i++) {
            c.getFreeSocket(true,false).attach(new Storage("DefaultChest",new Idle(),0, 1, 5,30));
        }
        for (int i = 0; i < 4; i++) {
            c.getFreeSocket(false,false).attach(new Leg("DefaultLeg",new Idle(),1, 0));
        }
        db.reset();
        return d;
    }

    public static Drone quickBuild(int numFabs, int numStor, int numLeg) {
        Drone d = new Drone(new Control("Brain",new Idle(),0, 1));
        DroneBrowser db = d.getBrowser();
        db.getCurrent().getFreeSocket(false,false).attach(new DefaultComponent("Chassis",new Idle(),numFabs + numStor + 1, numLeg));
        Component c = db.moveTo(0);
        for (int i = 0; i < numFabs; i++) {
            c.getFreeSocket(true,false).attach(new Fabricator("DefaultFab",new Idle(),1, 1, 1,0));
        }
        for (int i = 0; i < numStor; i++) {
            c.getFreeSocket(true,false).attach(new Storage("DefaultChest",new Idle(),0, 1, 5,30));
        }
        for (int i = 0; i < numLeg; i++) {
            c.getFreeSocket(false,false).attach(new Leg("DefaultLeg",new Idle(),1, 0));
        }
        db.reset();
        return d;

    }

    public static DroneStorage getStorageOrWarnUser(User sender, TextChannel c, CommandMap commandMap) throws CommandFuckedUpException {
        Char character = commandMap.getServerStorage().getOrAddPlayer(sender.getId());
        DroneStorage droneStorage = DroneStorage.getStorage(character);
        if (droneStorage == null) {
            throw new CommandFuckedUpException("You don't have any Drones, please type ``" + commandMap.getModule().getPrefix() + " generate`` to get started!",c);
        }
        return droneStorage;
    }

    public static void checkStorage(TextChannel c, DroneStorage droneStorage) throws CommandFuckedUpException {
        if (droneStorage.isEmpty()) {
            throw new CommandFuckedUpException("You don't have any Drones!", c);
        }
    }

    public static Drone getSelectedDrone(User sender,TextChannel c,CommandMap commandMap)throws CommandFuckedUpException{
        DroneStorage ds=getStorageOrWarnUser(sender,c,commandMap);
        checkStorage(c,ds);
        return ds.getSelectedDrone();
    }

    public static int genId() {
        return idTick++;
    }
}
