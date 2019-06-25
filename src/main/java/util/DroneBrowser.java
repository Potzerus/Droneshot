package util;

import drone.component.Component;
import drone.component.Socket;

import java.util.ArrayList;
import java.util.Arrays;

public class DroneBrowser {
    //TODO:Like in Iterator but works in many directions and can be used by the User aswell for traversing and understanding individual Drones
    Component current;

    public DroneBrowser(Component startComponent){
        current=startComponent;
    }

    public Component getCurrent() {
        return current;
    }

    public Component[] getAccessableComponents(){
        Socket[] sockets=current.getSockets();
        ArrayList<Component> holder=new ArrayList<>();
        for (Socket s:sockets) {
            if(s.isLinked())
                holder.add(s.getLinkedComponent());
        }
        Component[] output=new Component[holder.size()];
        holder.toArray(output);
        return output;
    }

    public String getAccessableComponentString(){
        Component[] holder=getAccessableComponents();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < holder.length; i++) {
            sb.append(i);
            sb.append(':');
            sb.append(holder[i].getType().getName());
            sb.append('\n');
        }
        return sb.toString();
    }

    public Component moveTo(int i){
        return current=getAccessableComponents()[i];
    }



}
