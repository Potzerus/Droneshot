package drone.component;

public class Leg extends DefaultComponent {

    public Leg(int carryingSocketAmount, int carriedSocketAmount) {
        super(carryingSocketAmount, carriedSocketAmount, "Leg");
        type = ComponentType.MOVEMENT;
        description="Movement Tool for Landbound walking";
    }
}
