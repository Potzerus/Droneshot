package util;

import drone.Drone;
import drone.component.*;

public class DroneUtils {
    private static int idTick;

    public static Drone buildDefaultDrone(){
        Drone d=new Drone(new DefaultComponent(0,1));
        Component c=d.getRootComponent();
        c.attach(new DefaultComponent(20,4));
        c=c.getSockets()[0].getLinkedComponent();
        c.attach(new Fabricator(0,1,1));
        for (int i = 0; i < 3; i++) {
            c.attach(new Storage(0,1,30));
        }
        for (int i = 0; i < 4; i++) {
            c.attach(new Leg(1,0));
        }
        return d;
    }

    public static Drone quickBuild(int numFabs,int numStor,int numLeg){
        Drone d=new Drone(new Control(0,1));
        Component c=d.getRootComponent();
        c.attach(new DefaultComponent(numFabs+numStor+1,numLeg));
        c=c.getSockets()[0].getLinkedComponent();
        for (int i = 0; i < numFabs; i++) {
            c.attach(new Fabricator(0,1,1));
        }
        for (int i = 0; i < numStor; i++) {
            c.attach(new Storage(0,1,30));
        }
        for (int i = 0; i < numLeg; i++) {
            c.attach(new Leg(1,0));
        }
        return d;

    }

    public static int genId(){
        return idTick++;
    }
}
