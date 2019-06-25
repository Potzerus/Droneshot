package commands;

import drone.Drone;
import drone.DroneStorage;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import potz.utils.commands.Command;
import potz.utils.database.Char;
import util.DroneUtils;

public class Generate extends Command {

    public Generate(String identifier) {
        super(identifier);
    }

    @Override
    public void execute(User sender, Server s, TextChannel c, String[] args) {
        Char character=commandMap.getServerStorage().getOrAddPlayer(sender.getId());
        DroneStorage droneStorage=(DroneStorage)character.getOrAddStat("droneStorage",new DroneStorage(character));
        if(droneStorage==null){
            droneStorage=new DroneStorage(character);
            character.addStat("droneStorage",droneStorage);
        }
        if(args.length==2)
        if(droneStorage.isEmpty()) {
            c.sendMessage("Generating Starting drone!");
            Drone drone = DroneUtils.buildDefaultDrone();
            droneStorage.addDrone(drone);
        }else
            c.sendMessage("You already have a drone! add ``reset`` to the command to replace all your drones " +
                    "with a single default one (You will lose all their parts and resources!)");
        else if(args.length==3&&args[2].equals("reset")){
            droneStorage.reset();
            droneStorage.addDrone(DroneUtils.buildDefaultDrone());
            c.sendMessage("Successfully deleted all your drones and replaced them with a single Standard drone");
    }}
}
