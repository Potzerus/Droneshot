package drone;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import potz.utils.database.Char;

import java.util.HashSet;
import java.util.Iterator;

public class DroneStorage implements Iterable<Drone> {
    private Char owner;
    private HashSet<Drone> drones = new HashSet<>();
    private Drone selectedDrone;
    private boolean showId=true;

    public DroneStorage(Char owner) {
        this.owner = owner;
    }

    public boolean addDrone(Drone d) {
        if(drones.isEmpty())
            selectedDrone=d;
        d.setParent(this);
        return drones.add(d);
    }

    public Drone getSelectedDrone(){
        return selectedDrone;
    }

    public void selectDrone(Drone d){
        if(d!=null)
        selectedDrone=d;
    }

    //Looks for Drone with matching id, if String cannot be converted to integer searches for name instead
    public Drone getDrone(String droneIdentifier) {
        try {
            Drone output= seekDrone(Integer.parseInt(droneIdentifier));
            if(output!=null)
                return output;
        } catch (NumberFormatException e){}
        return seekDrone(droneIdentifier);
    }

    private Drone seekDrone(int droneId) {
        for (Drone d : this) {
            if (d.getId() == droneId)
                return d;
        }
        return null;
    }

    private Drone seekDrone(String droneName) {
        for (Drone d : this) {
            if (droneName.equals(d.getName()))
                return d;
        }
        return null;
    }

    public int getSize() {
        return drones.size();
    }

    public boolean removeDrone(Drone d) {
        return drones.remove(d);
    }

    public boolean hasDrone(Drone d) {
        return drones.contains(d);
    }

    public boolean isEmpty() {
        return drones.isEmpty();
    }

    @Override
    public Iterator<Drone> iterator() {
        return drones.iterator();
    }

    public void listDronesAsEmbeds(EmbedBuilder embedBuilder) {
        for (Drone d : drones) {
            embedBuilder.addField(d.getIdentity(showId)+(d.equals(selectedDrone)?"(selected)":""),d.getDroneInfo());
        }
    }


    public void reset() {
        drones.clear();
    }

    public static DroneStorage getStorage(Char c){
        return (DroneStorage) c.getStat("droneStorage");
    }

    public boolean hasShowId(){
        return showId;
    }

    public void toggleShowId(){
        showId=!showId;
    }

    public Char getOwner() {
        return owner;
    }
}
