package drone.component;

import drone.Socket;
import drone.actions.Action;
import org.javacord.api.entity.message.embed.EmbedBuilder;

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

    void genSockets(int plus,int minus);

    Socket[] getMinusSockets(boolean getNonStorage, boolean getStorage);

    Action getAction();

    void setAction(Action a);

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

        if (!components.add(this)) return;
        for (Socket s : getSockets(true, false)) {
            if (s != null && s.isLinked())
                s.getLinkedComponent().stackAll(components);
        }

    }

    String getDescription();

    ComponentType getType();

    String getSocketString();

    default void makeStorageSockets(int plus, int minus) {
        Socket[] arr = getPlusSockets();
        for (int i = arr.length - 1; i >= arr.length - plus; i--) {
            arr[i].setStorage(true);
        }
        arr=getMinusSockets();
        for (int i = arr.length - 1; i >= arr.length - minus; i--) {
            arr[i].setStorage(true);
        }
    }

    String getIdentifier();

    int[] getBuildCost();

    void setBuildCost(int[] buildCost);

    int[] getUseCost();

    void setUseCost(int[] useCost);

    void setIdentifier(String identifier);

    void toEmbed(EmbedBuilder embedBuilder);
}
