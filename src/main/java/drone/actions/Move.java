package drone.actions;

public class Move implements Action {

    int maxAmount;

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
}
