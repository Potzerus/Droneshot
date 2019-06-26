package drone.component;

import drone.actions.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DefaultComponent implements Component {


    protected Socket[] plusSockets, minusSockets;
    protected Action action;
    protected String identifier;
    protected ComponentType type;
    protected String description;

    public DefaultComponent(String identifier,Action action,int plusSocketAmount, int minusSocketAmount ) {
        this.identifier = identifier;
        this.action=action;
        Component component = this;
        this.plusSockets = new Socket[plusSocketAmount];
        Arrays.setAll(plusSockets, value -> new Socket(component, true));
        this.minusSockets = new Socket[minusSocketAmount];
        Arrays.setAll(minusSockets, value -> new Socket(component, false));
        type = ComponentType.STRUCTURE;
        description = "This is a basic Component, Frequently used as Structure to house other Components";
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
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return identifier + ":" + (action == null ? "NA" : action.toString());
    }
}
