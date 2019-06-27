package drone.actions;

public class Move implements Action {

    int maxAmount;
    private final String name="Move";

    public Move(int maxAmount){
        this.maxAmount = maxAmount;
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
}
