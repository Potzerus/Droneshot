package drone.component;

import drone.actions.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DefaultComponent implements Component {


    protected Socket[] plusSockets, minusSockets;
    protected Action action;
    protected final String identifier;
    protected ComponentType type;
    protected String description = "Default Description";

    public DefaultComponent(int carryingSocketAmount, int carriedSocketAmount) {
        this(carryingSocketAmount, carriedSocketAmount, "Basic");
        description = "This is a basic Component, Frequently used as Structure to house other Components";
    }

    public DefaultComponent(int carryingSocketAmount, int carriedSocketAmount, String identifier) {
        this.plusSockets = new Socket[carryingSocketAmount];
        Component component = this;
        Arrays.setAll(plusSockets, value -> new Socket(component, true));
        this.minusSockets = new Socket[carriedSocketAmount];
        Arrays.setAll(minusSockets, value -> new Socket(component, false));
        this.identifier = identifier;
        type = ComponentType.STRUCTURE;
    }


    @Override
    public Socket[] getSockets(boolean getNonStorage,boolean getStorage) {
        Socket[] output = new Socket[plusSockets.length + minusSockets.length];
        Socket[] insertion1=getPlusSockets(getNonStorage,getStorage);
        System.arraycopy(insertion1,0,output,0,insertion1.length);
        Socket[] insertion2=getMinusSockets(getNonStorage,getStorage);
        System.arraycopy(insertion2,0,output,insertion1.length,insertion2.length);
        return output;
    }



    @Override
    public Socket[] getPlusSockets() {
        return getPlusSockets(true,false);
    }

    @Override
    public Socket[] getPlusSockets(boolean getNonStorage, boolean getStorage) {
        if (getNonStorage && getStorage)
            return plusSockets;
        else {
            ArrayList<Socket> sockets = new ArrayList<>();
            if (getNonStorage) {
                for (Socket s : plusSockets) {
                    if (!s.isStorage())
                        sockets.add(s);
                }
            }
            if (getStorage) {
                for (Socket s : plusSockets) {
                    if (s.isStorage())
                        sockets.add(s);
                }
            }
            Socket[] socketArray = new Socket[sockets.size()];
            Iterator<Socket> socketIterator=sockets.iterator();
            for (int i = 0; i < socketArray.length; i++) {
                socketArray[i]=socketIterator.next();
            }
            return socketArray;
        }

    }

    @Override
    public Socket[] getMinusSockets() {
        return getMinusSockets(true,false);
    }

    @Override
    public Socket[] getMinusSockets(boolean getNonStorage, boolean getStorage) {
        if (getNonStorage && getStorage)
            return minusSockets;
        else {
            ArrayList<Socket> sockets = new ArrayList<>();
            if (getNonStorage) {
                for (Socket s : minusSockets) {
                    if (!s.isStorage())
                        sockets.add(s);
                }
            }
            if (getStorage) {
                for (Socket s : minusSockets) {
                    if (s.isStorage())
                        sockets.add(s);
                }
            }
            Socket[] socketArray = new Socket[sockets.size()];
            Iterator<Socket> socketIterator=sockets.iterator();
            for (int i = 0; i < socketArray.length; i++) {
                socketArray[i]=socketIterator.next();
            }
            return socketArray;
        }

    }


    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String getSocketString() {
        StringBuilder sb=new StringBuilder();
        int i=0;
        for (Socket s:getSockets(true,true)) {
            sb.append(i++);
            sb.append(' ');
            sb.append(s.toString());
            sb.append("\n");
        }
        return identifier + ":\n" + sb.toString();
    }

    @Override
    public String toString() {
        return identifier + ":" + (action == null ? "NA" : action.toString());
    }
}
