package drone.component;

import drone.actions.Action;

import java.util.HashSet;


//Components need a dedicatedAmount of carrying and carried Sockets upon creation
public interface Component {


    default Socket[] getSockets() {
        return getSockets(true, false);
    }

    default Socket[] getPlusSockets() {
        return getPlusSockets(true, false);
    }

    default Socket[] getMinusSockets() {
        return getMinusSockets(true, false);
    }

    Socket[] getSockets(boolean getNonStorage, boolean getStorage);

    Socket[] getPlusSockets(boolean getNonStorage, boolean getStorage);

    Socket[] getMinusSockets(boolean getNonStorage, boolean getStorage);

    Action getAction();

    default Socket getFreeSocket(boolean plus) {
        Socket s = getFreeSocket(plus, false);
        if (s == null) {
            s = getFreeSocket(plus, true);
        }
        return s;
    }

    default Socket getFreeSocket(boolean plus, boolean storage) {
        Socket[] selection;
        if (plus)
            selection = getPlusSockets(!storage, storage);
        else
            selection = getMinusSockets(!storage, storage);
        for (Socket s : selection) {
            if (!s.isLinked())
                return s;
        }
        return null;
    }

/*
    default boolean attach(Component c) {
        if (hasFreeSocket(false) && c.hasFreeSocket(true)) {
            Socket socket1, socket2;
            {
                int i = 0;

                do {
                    if (!(socket1 = getMinusSockets()[i]).isLinked())
                        break;
                    i++;
                } while (i < getMinusSockets().length);

            }
            int i = 0;

            do {
                if (!(socket2 = c.getPlusSockets()[i]).isLinked())
                    break;
                i++;
            } while (i < c.getPlusSockets().length);

            socket1.setLinked(socket2);
        } else if (hasFreeSocket(true) && c.hasFreeSocket(false)) {
            Socket socket1, socket2;
            {
                int i = 0;

                do {
                    if (!(socket1 = getPlusSockets()[i]).isLinked())
                        break;
                    i++;
                } while (i < getPlusSockets().length);

            }
            int i = 0;

            do {
                if (!(socket2 = c.getMinusSockets()[i]).isLinked())
                    break;
                i++;
            } while (i < c.getMinusSockets().length);

            socket1.setLinked(socket2);

        } else
            return false;
        return true;
    }
*/

    default Socket getSocketConnectingTo(Component c) {
        for (Socket s : getSockets()) {
            if (s.getLinkedComponent().equals(c))
                return s;
        }
        return null;
    }

    default boolean hasFreeSocket(boolean plus) {
        if (plus)
            for (Socket s : getPlusSockets()) {
                if (!s.isLinked())
                    return true;
            }
        else
            for (Socket s : getMinusSockets()) {
                if (!s.isLinked())
                    return true;
            }
        return false;
    }

    default void stackAll(HashSet<Component> components) {
        for (Socket s : getMinusSockets()) {
            Component up = s.getLinkedComponent();
            if (!components.contains(up))
                up.stackAll(components);
        }
        components.add(this);
        for (Socket s : getPlusSockets()) {
            if (s.isLinked() && s.isPlus()) {
                Component down = s.getLinkedComponent();
                if (!components.contains(down))
                    down.stackAll(components);
            }
        }

    }

    String getDescription();

    ComponentType getType();

    String getSocketString();
}
