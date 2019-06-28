package drone.component;

import java.util.Arrays;

public class Science extends DefaultComponent {

    private int[] techLevel;

    public Science() {
        type = ComponentType.SCIENCE;
    }

    public void setTechLevel(int[] techLevel) {
        this.techLevel = techLevel;
    }

    public int[] getTechLevel() {
        return Arrays.copyOf(techLevel, techLevel.length);
    }
}
