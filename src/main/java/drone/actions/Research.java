package drone.actions;

public class Research extends Action{

    public Research(){
        super();
        name="Research";
        description="Research new ComponentTypes or Traits!";
    }

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
