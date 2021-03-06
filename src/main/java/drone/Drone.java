package drone;

import drone.actions.Action;
import drone.component.*;
import map.ResourceType;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import util.DroneUtils;

import java.util.*;

public class Drone implements Iterable<Component> {
    private Control rootComponent;
    private String name = "New Drone";
    private int id = DroneUtils.genId();
    private Action queuedAction;
    private Runnable runnable;
    private int[] resources = new int[ResourceType.values().length];
    private DroneBrowser browser;
    private HashSet<Action> possibleActions;
    private DroneStorage parent;


    public Drone(Control rootComponent) {
        this.rootComponent = rootComponent;
        browser = new DroneBrowser(this);
        queuedAction = Action.getIdle();
        runnable = () -> queuedAction.run();
    }

    public void setParent(DroneStorage parent) {
        this.parent = parent;
    }

    public DroneStorage getParent() {
        return parent;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Action getQueuedAction() {
        return queuedAction;
    }

    public void resetQueuedAction() {
        queuedAction = Action.getIdle();
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable r) {
        runnable = r;
    }

    public Action[] getActions() {
        HashSet<Action> set = new HashSet<>();
        for (Component c : this) {
            set.add(c.getAction());
        }
        Action[] actions = new Action[set.size()];
        int i = 0;
        for (Action a : set) {
            actions[i++] = a;
        }
        return actions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Control getRootComponent() {
        return rootComponent;
    }

    public boolean hasComponentType(ComponentType type) {
        for (Component component : this) {
            if (type == component.getType())
                return true;
        }
        return false;
    }

    public void addResources(int[] addedResources) {
        for (Component c : this) {
            if (c.getType() == ComponentType.STORAGE)
                ((Storage) c).fillOrEmptyTank(addedResources);
            else if (c.getType() == ComponentType.BATTERY)
                ((Battery) c).changeEnergyLevel(addedResources[0]);
        }
    }

    public void recalculateResources() {
        Arrays.fill(resources, 0);
        for (Component c : this) {
            if (c.getType() == ComponentType.STORAGE) {
                int[] addition = ((Storage) c).getResource();
                for (int i = 1; i < resources.length; i++) {
                    resources[i] += addition[i];
                }
            } else if (c.getType() == ComponentType.BATTERY) {
                resources[0] += ((Battery) c).getCurrentEnergy();
            }
        }
    }

    public int[] getFreshResources() {
        recalculateResources();
        return Arrays.copyOf(resources, resources.length);
    }

    public void recalcPossibleActions() {
        possibleActions.clear();
        for (Component c : this) {
            possibleActions.add(c.getAction());
        }
    }

    public DroneBrowser getBrowser() {
        return browser;
    }

    public void resetBrowser() {
        browser.reset();
    }

    @Override
    public Iterator<Component> iterator() {
        HashSet<Component> components = new HashSet<>();
        rootComponent.stackAll(components,null);

        return components.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getIdentity());
        sb.append('\n');
        sb.append(getDroneInfo());
        return sb.toString();
    }

    public String getDroneInfo() {
        StringBuilder sb = new StringBuilder();
        ResourceType[] types = ResourceType.values();
        boolean noResources = true;
        sb.append("Resources: ");
        for (int i = 1; i < types.length; i++) {
            if (resources[i] > 0) {
                sb.append(types[i].getName());
                sb.append(':');
                sb.append(resources[i]);
                sb.append(" ");
                noResources = false;
            }
        }
        if (noResources)
            sb.append("None ");
        return sb.toString();
    }

    public String getIdentity() {
        return name + " : " + id;
    }

    public String getIdentity(boolean showId) {
        return name + (showId ? ":" + id : "");
    }

    public String getComponentInfo() {
        ArrayList<String> sl = new ArrayList<>();
        for (Component c : this) {
            sl.add(c.getSocketString());
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
        embedBuilder.addField(getIdentity(parent.hasShowId()), getDroneInfo());
    }

    public void listActionsAsEmbeds(EmbedBuilder embedBuilder) {
        for (Action action : getActions()) {
            embedBuilder.addField(action.getName(), action.getDescription());
        }
    }
}
