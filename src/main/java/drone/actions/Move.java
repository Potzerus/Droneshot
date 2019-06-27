package drone.actions;

public class Move extends Action {

    int maxAmount;

    public Move(int maxAmount){
        this.maxAmount = maxAmount;
        name="Move";
        description="Move to a different Tile";

    }

    @Override
    public void run() {

    }

    @Override
    public String getDescription() {
        return "move";
    }

    @Override
    public boolean repeats() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setLoops(int amount) {

    }
}
