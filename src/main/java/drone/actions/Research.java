package drone.actions;

public class Research implements Action{

    private final String name="Research";

    @Override
    public void run() {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean repeats() {
            return false;
    }

    @Override
    public String getName() {
        return name;
    }
    //TODO:Model how its supposed to work
}
