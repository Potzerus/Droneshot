package drone.component;

import java.util.Arrays;

public class ComponentStorage extends DefaultComponent {

    public ComponentStorage(int plusSocketAmount, int minusSocketAmount, int plusStorage,int minusStorage) {
        this(plusSocketAmount, minusSocketAmount, "ComponentStorage");
        type=ComponentType.COMPONENTSTORAGE;
        description="This is a Component Storage Module, used to Store Inactive Components";
        makeStorageSockets(plusStorage,minusStorage);
    }

    public ComponentStorage(int plusSocketAmount, int minusSocketAmount, String identifier) {
        super(plusSocketAmount, minusSocketAmount, identifier);
    }

    @Override
    public ComponentType getType() {
        return type;
    }
}
