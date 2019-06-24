package drone.component;

import drone.actions.Action;

import java.util.Arrays;

public class DefaultComponent implements Component {


    protected Socket[] carryingSockets,carriedSockets;
    protected Action action;
    protected final String identifier;

    public DefaultComponent(int carryingSocketAmount, int carriedSocketAmount) {
        this(carryingSocketAmount, carriedSocketAmount,"Basic");
    }

    public DefaultComponent(int carryingSocketAmount, int carriedSocketAmount, String identifier) {
        this.carryingSockets=new Socket[carryingSocketAmount];
        Component component=this;
        Arrays.setAll(carryingSockets, value -> new Socket(component,true));
        this.carriedSockets=new Socket[carriedSocketAmount];
        Arrays.setAll(carriedSockets, value -> new Socket(component,false));
        this.identifier =identifier;
    }


    @Override
    public Socket[] getSockets() {
        Socket[] output=new Socket[carryingSockets.length+carriedSockets.length];
        int i = 0;
        for (; i < carryingSockets.length; i++) {
            output[i]=carryingSockets[i];
        }
        for (int j = 0; j < carriedSockets.length; i++,j++) {
            output[i]=carriedSockets[j];
        }
        return output;
    }

    @Override
    public Socket[] getCarryingSockets() {
        return carryingSockets;
    }

    @Override
    public Socket[] getCarriedSockets() {
        return carriedSockets;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return identifier+":"+((carryingSockets.length>0)?" carry:"+carryingSockets.length+";":""+((carriedSockets.length>0)?" carried:"+carriedSockets.length+";":""));
    }
}
