package drone.component;

public class Leg extends DefaultComponent {

    public Leg(int plusSocketAmount, int minusSocketAmount) {
        super(plusSocketAmount, minusSocketAmount, "Leg");
        type = ComponentType.MOVEMENT;
        description="Movement Tool for Landbound walking";
    }
}
