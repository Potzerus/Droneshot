package drone.component;

import map.ResourceType;

public class Generator extends DefaultComponent {

    private int production;

    public Generator() {
        type = ComponentType.GENERATOR;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getProduction() {
        return production;
    }
    public int[] getProductionArr() {
        int[] productionArr=new int[ResourceType.values().length];
        productionArr[0]=production;
        return productionArr;
    }
}
