package drone.actions;

import drone.Drone;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public abstract class Action {
    protected String name;
    protected String description;
    protected Drone executingDrone;
    protected int loops=0;

    public Action(Drone executingDrone,String name,String description) {
        this.executingDrone = executingDrone;
        this.name=name;
        this.description=description;
    }

    public abstract void run();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean repeats() {
        return loops-- != 0;
    }

    public void setLoops(int amount) {
        loops = amount;
    }

    public String toString() {
        return name + '\n' + description;
    }

    public void toEmbed(EmbedBuilder embedBuilder) {
        embedBuilder.addField(name, description);
    }

    public static Action getIdle() {
        return new Idle(null);
    }
}
