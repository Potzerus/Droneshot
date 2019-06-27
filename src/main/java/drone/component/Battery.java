package drone.component;

import drone.actions.Action;

public class Battery extends DefaultComponent {

    private int maxEnergy;
    public Battery(String identifier, Action action, int plusSocketAmount, int minusSocketAmount,int maxEnergy) {
        super(identifier, action, plusSocketAmount, minusSocketAmount);
        this.maxEnergy=maxEnergy;
    }

    public Battery(){
        super();
        type=ComponentType.BATTERY;
    }

    public void setMaxEnergy(int newMax){
        maxEnergy=newMax;
    }
}
