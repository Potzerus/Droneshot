package drone.component;

import drone.actions.Action;

public class Battery extends DefaultComponent {

    private int maxEnergy;
    private int currentEnergy;
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

    public int changeEnergyLevel(int amount){
        currentEnergy+=amount;
        if(currentEnergy<0){
            amount=currentEnergy;
            currentEnergy=0;
        }else if(currentEnergy>maxEnergy){
            amount=currentEnergy-maxEnergy;
            currentEnergy=maxEnergy;
        }else
            amount=0;
        return amount;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }
}
