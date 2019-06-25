package util;

import drone.Drone;
import drone.DroneStorage;
import drone.component.*;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;
import potz.utils.commandMaps.CommandMap;
import potz.utils.database.Char;

public class DroneUtils {
    private static int idTick;

    public static Drone buildDefaultDrone() {
        Drone d = new Drone(new Control(0, 1));
        d.getRootComponent().setParent(d);
        Component c = d.getRootComponent();
        c.attach(new DefaultComponent(20, 4));
        c = c.getSockets()[0].getLinkedComponent();
        c.attach(new Fabricator(0, 1, 1));
        for (int i = 0; i < 3; i++) {
            c.attach(new Storage(0, 1, 30));
        }
        for (int i = 0; i < 4; i++) {
            c.attach(new Leg(1, 0));
        }
        return d;
    }

    public static Drone quickBuild(int numFabs, int numStor, int numLeg) {
        Drone d = new Drone(new Control(0, 1));
        Component c = d.getRootComponent();
        c.attach(new DefaultComponent(numFabs + numStor + 1, numLeg));
        c = c.getSockets()[0].getLinkedComponent();
        for (int i = 0; i < numFabs; i++) {
            c.attach(new Fabricator(0, 1, 1));
        }
        for (int i = 0; i < numStor; i++) {
            c.attach(new Storage(0, 1, 30));
        }
        for (int i = 0; i < numLeg; i++) {
            c.attach(new Leg(1, 0));
        }
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
