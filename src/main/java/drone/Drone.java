package drone;

import drone.actions.Action;
import drone.component.Component;
import drone.component.Storage;
import map.ResourceType;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import potz.utils.database.Char;
import util.DroneUtils;

import java.util.*;

public class Drone implements Iterable<Component> {
    private Component rootComponent;
    private String name;
    private int id = DroneUtils.genId();
    private Char owner;
    private Action queuedAction;
    private int[] resources = new int[ResourceType.values().length];


    public Drone(Component rootComponent) {
        this.rootComponent = rootComponent;
    }

    public Drone(Component rootComponent, Char owner) {
        this(rootComponent);
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Action[] getActions() {
        HashSet<Action> set = new HashSet<>();
        for (Component c : this) {
            set.add(c.getAction());
        }
        Action[] actions=new Action[set.size()];
        int i=0;
        for (Action a:set) {
            actions[i++]=a;
        }
        return actions;
    }

    public void setOwner(Char owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Component getRootComponent() {
        return rootComponent;
    }

    public boolean hasComponentType(Class c) {
        for (Component component : this) {
            if (c.isInstance(component))
                return true;
        }
        return false;
    }

    public void recalculateResources() {
        Arrays.fill(resources, 0);
        for (Component c : this) {
            if (c instanceof Storage) {
                int[] addition = ((Storage) c).getResource();
                for (int i = 0; i < resources.length; i++) {
                    resources[i] += addition[i];
                }
            }
        }
    }

    @Override
    public Iterator<Component> iterator() {
        HashSet<Component> components = new HashSet<>();
        rootComponent.stackAll(components);

        return components.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + ":" + id);
        sb.append('\n');
        sb.append(getDroneInfo());
        return sb.toString();
    }

    public String getDroneInfo() {
        StringBuilder sb = new StringBuilder();
        ResourceType[] types = ResourceType.values();
        boolean noResources=true;
        sb.append("Resources: ");
        for (int i = 1; i < types.length; i++) {
            if (resources[i] > 0) {
                sb.append(types[i].getName());
                sb.append(':');
                sb.append(resources[i]);
                sb.append(" ");
                noResources=false;
            }
        }
        if(noResources)
            sb.append("None ");
        return sb.toString();
    }

    public String getIdentity() {
        return name + ":" + id;
    }

    public String getComponentInfo() {
        ArrayList<String> sl = new ArrayList<>();
        for (Component c : this) {
            sl.add(c.toString());
        }
        Object[] output = sl.toArray();
        Arrays.sort(output);

        StringBuilder sb = new StringBuilder();
        for (Object o : output) {
            sb.append(o.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    public void toEmbedField(EmbedBuilder embedBuilder) {
        embedBuilder.addField("Components:", getComponentInfo());
    }

}
