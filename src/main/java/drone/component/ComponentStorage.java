package drone.component;

import drone.actions.Action;

import java.util.Arrays;

public class ComponentStorage extends DefaultComponent {

    public ComponentStorage(String identifier, Action action, int plusSocketAmount, int minusSocketAmount, int plusStorage, int minusStorage) {
        super( identifier,action,plusSocketAmount, minusSocketAmount);
        description="This is a Component Storage Module, used to Store Inactive Components";
        type=ComponentType.COMPONENTSTORAGE;
        makeStorageSockets(plusStorage,minusStorage);
    }

    public ComponentStorage(){
        super();
        type=ComponentType.COMPONENTSTORAGE;
    }

    @Override
    public ComponentType getType() {
        return type;
    }
}
