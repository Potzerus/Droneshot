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
    private int[] resources=new int[ResourceType.values().length];

    public Drone(Component rootComponent) {
        this.rootComponent = rootComponent;
    }

    public Drone(Component rootComponent, Char owner) {
        this(rootComponent);
        this.owner = owner;
    }

    public int getId(){
        return id;
    }

    public Action[] getActions() {
        ArrayList<Action> list = new ArrayList<>();
        for (Component c : this) {
            list.add(c.getAction());
        }
        return (Action[]) list.toArray();
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
        Arrays.fill(resources,0);
        for (Component c : this) {
            if (c instanceof Storage) {
                int[] addition = ((Storage) c).getResource();
                for (int i = 0; i < resources.length; i++) {
                    resources[i]+=addition[i];
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
        StringBuilder sb=new StringBuilder(name + ":" + id);
        sb.append('\n');


        return sb.toString();
    }

    public String getIdentity(){
        return name+":"+id;
    }

    public String getInfo(){
        ArrayList<String> sl=new ArrayList<>();
        for (Component c:this) {
            sl.add(c.toString());
        }
        Object[] output=sl.toArray();
        Arrays.sort(output);

        StringBuilder sb=new StringBuilder();
        for (Object o:output) {
            sb.append(o.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    public void toEmbedField(EmbedBuilder embedBuilder) {
        embedBuilder.addField(getIdentity(), getInfo());
    }

}
