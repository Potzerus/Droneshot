package util;

import drone.Drone;
import drone.component.Component;
import drone.component.Socket;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.util.ArrayList;

public class DroneBrowser {
    //TODO:Like in Iterator but works in many directions and can be used by the User aswell for traversing and understanding individual Drones
    private Component current;
    private Drone parent;
    private Socket held;


    public DroneBrowser(Drone parent) {
        this.parent=parent;
        current = parent.getRootComponent();
    }

    public Component getCurrent() {
        return current;
    }

    public Component[] getAccessableComponents() {
        Socket[] sockets = current.getSockets();
        ArrayList<Component> holder = new ArrayList<>();
        for (Socket s : sockets) {
            if (s.isLinked())
                holder.add(s.getLinkedComponent());
        }
        Component[] output = new Component[holder.size()];
        holder.toArray(output);
        return output;
    }

/*
    public String getAccessableComponentString() {
        Component[] holder = getAccessableComponents();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < holder.length; i++) {
            sb.append(i);
            sb.append(':');
            sb.append(holder[i].getType().getName());
            sb.append('\n');
        }
        return sb.toString();
    }
*/

    public int getNumFreePlusSockets(){
        int sum=0;
        for (Socket s:current.getPlusSockets()) {
            if(!s.isLinked())
                sum++;
        }
        return sum;
    }

    public int getNumFreeMinusSockets(){
        int sum=0;
        for (Socket s:current.getMinusSockets()) {
            if(!s.isLinked())
                sum++;
        }
        return sum;
    }

    public int getNumFreeSockets(){
        return getNumFreeMinusSockets()+getNumFreePlusSockets();
    }

    public Component moveTo(int i) {
        return current = getAccessableComponents()[i];
    }

    public void reset() {
        current = parent.getRootComponent();
    }

    public void swap(int i) {

        Socket swapee=held;
        Socket swapBase= current.getSockets(true,true)[i];
        held =  swapBase.getLinkedSocket();
        assert held==null||swapee==null||(held.isPlus()==swapee.isPlus()); //Cant swap different Type sockets
        if(swapee==null)
            swapBase.breakLink();
        else{
            swapBase.setLinked(swapee);
        }
        if(held!=null)
            held.breakLink();
        reset();

    }

    public void toEmbed(EmbedBuilder embedBuilder, boolean displaySockets) {
        embedBuilder.addField(current.getType().getName(), current.getDescription());
        if(displaySockets)
            embedBuilder.addField("Sockets:",current.getSocketString());
        if (held != null)
            embedBuilder.addField("Currently Holding "+(held.isPlus()?"+":"-"), held.getParent().getType().getName());
    }

    public void toEmbed(EmbedBuilder embedBuilder){
        toEmbed(embedBuilder,held!=null);
    }
}
