package drone;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import potz.utils.database.Char;

import java.util.HashSet;
import java.util.Iterator;

public class DroneStorage implements Iterable<Drone>{
    Char owner;
    HashSet<Drone> drones=new HashSet<>();

    public DroneStorage(Char owner) {
        this.owner = owner;
    }

    public boolean addDrone(Drone d){
        return drones.add(d);
    }

    public int getSize(){
        return drones.size();
    }

    public boolean removeDrone(Drone d){
        return drones.remove(d);
    }

    public boolean hasDrone(Drone d){
        return drones.contains(d);
    }

    public boolean isEmpty(){
        return drones.isEmpty();
    }

    @Override
    public Iterator<Drone> iterator() {
        return drones.iterator();
    }

    public void listDronesAsEmbeds(EmbedBuilder embedBuilder){
        for (Drone d:drones) {
            d.toEmbedField(embedBuilder);
        }
    }

    public void reset() {
        drones.clear();
    }
}
