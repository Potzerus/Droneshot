package drone.component;

import drone.actions.Action;

import java.util.HashSet;


//Components need a dedicatedAmount of carrying and carried Sockets upon creation
public interface Component {


    Socket[] getSockets();

    Socket[] getCarryingSockets();


    Socket[] getCarriedSockets();

    Action getAction();

    default boolean attach(Component c) {
        if (hasFreeSocket(false) && c.hasFreeSocket(true)) {
            Socket socket1, socket2;
            {
                int i = 0;

                do {
                    if (!(socket1 = getCarriedSockets()[i]).isLinked())
                        break;
                    i++;
                } while (i < getCarriedSockets().length);

            }
            int i = 0;

            do {
                if (!(socket2 = c.getCarryingSockets()[i]).isLinked())
                    break;
                i++;
            } while (i < c.getCarryingSockets().length);

            socket1.setLinked(socket2);
        } else if (hasFreeSocket(true) && c.hasFreeSocket(false)) {
            Socket socket1, socket2;
            {
                int i = 0;

                do {
                    if (!(socket1 = getCarryingSockets()[i]).isLinked())
                        break;
                    i++;
                } while (i < getCarryingSockets().length);

            }
            int i = 0;

            do {
                if (!(socket2 = c.getCarriedSockets()[i]).isLinked())
                    break;
                i++;
            } while (i < c.getCarriedSockets().length);

            socket1.setLinked(socket2);

        }else
            return false;
        return true;
    }

    default boolean hasFreeSocket(boolean carrying) {
        if (carrying)
            for (Socket s : getCarryingSockets()) {
                if (!s.isLinked())
                    return true;
            }
        else
            for (Socket s : getCarriedSockets()) {
                if (!s.isLinked())
                    return true;
            }
        return false;
    }

    default void stackAll(HashSet<Component> components) {
        for (Socket s : getCarriedSockets()) {
            Component up = s.getLinkedComponent();
            if (!components.contains(up))
                up.stackAll(components);
        }
        components.add(this);
        for (Socket s : getCarryingSockets()) {
            if (s.isLinked()&&s.isCarrying()) {
                Component down = s.getLinkedComponent();
                if (!components.contains(down))
                    down.stackAll(components);
            }
        }

    }

    String getDescription();

    ComponentType getType();
}
