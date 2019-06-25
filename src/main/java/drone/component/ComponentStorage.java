package drone.component;

import java.util.Arrays;

public class ComponentStorage extends DefaultComponent {
    protected Socket[] storageSockets;

    public ComponentStorage(int carryingSocketAmount, int carriedSocketAmount, int storageSocketAmount) {
        this(carryingSocketAmount, carriedSocketAmount, storageSocketAmount, "ComponentStorage");
    }

    public ComponentStorage(int carryingSocketAmount, int carriedSocketAmount, int storageSocketAmount, String identifier) {
        super(carryingSocketAmount, carriedSocketAmount, identifier);
        storageSockets = new Socket[storageSocketAmount];
        Arrays.setAll(storageSockets, value -> new Socket(this, true, true));
    }


    public Socket[] getStorageSockets() {
        return storageSockets;
    }

    public boolean hasFreeStorageSocket() {
        for (Socket s:storageSockets) {
            if(!s.isLinked())
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        int freeSocketCount = 0;
        for (int i = 0; i < storageSockets.length; i++) {
            if (!storageSockets[i].isLinked())
                freeSocketCount++;
        }
        return "ComponentStorage:" + (freeSocketCount > 0 ? "Free Output Slots:" + freeSocketCount:"No Free Output Slots");
    }
}
