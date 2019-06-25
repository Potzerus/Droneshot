package drone.component;

import map.ResourceType;

import java.util.Arrays;

import static map.ResourceType.NONE;

public class Storage extends DefaultComponent {

    private int storageCapacity;
    private int[] currentResourceAmount;
    private int maxStoredTypes;


    public Storage(int plusSocketAmount, int minusSocketAmount, int maxStoredTypes, int storageCapacity) {
        super(plusSocketAmount, minusSocketAmount, "Storage");
        this.storageCapacity = storageCapacity;
        type = ComponentType.STORAGE;
        description = "Used to Store Materials";
        this.maxStoredTypes =maxStoredTypes;
    }

    /**
     * param storedType storedType of resource that is being inserted or drained from this tank
     *
     * @param amount amount of resource that is being inserted or drained from this tank
     * @return how much is still leftover to fill/drain from the original request
     */
    public int fillOrEmptyTank(ResourceType type, int amount) {
        //TODO: Test if this works properly(After mining is implemented)
        if (currentResourceAmount[type.ordinal()]>0 || getStoredTypeAmount()<maxStoredTypes) {
            currentResourceAmount[type.ordinal()] += amount;
            if (currentResourceAmount[type.ordinal()] <= 0) {
                amount = currentResourceAmount[type.ordinal()];
                currentResourceAmount[type.ordinal()] = 0;
            } else if (currentResourceAmount[type.ordinal()] > storageCapacity) {
                amount = currentResourceAmount[type.ordinal()] - storageCapacity;
                currentResourceAmount[type.ordinal()] = storageCapacity;
            } else
                amount = 0;
        }
        return amount;
    }

    public int[] getResource() {
        return Arrays.copyOf(currentResourceAmount, currentResourceAmount.length);
    }

    private int getStoredTypeAmount(){
        int output=0;
        for (int i : currentResourceAmount) {
            if(i>0)
                output++;
        }
        return output;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder(identifier);
        sb.append(':');
        int j=0;
        for (int i = 0; i < maxStoredTypes; i++) {
            if(currentResourceAmount[i]>0){
                sb.append(ResourceType.values()[i].getName());
                sb.append(':');
                sb.append(currentResourceAmount[i]);
                sb.append("/");
                sb.append(storageCapacity);
                sb.append('\n');
                j++;
            }
        }
        for (;j  < 3 ; j++) {
            sb.append("Free");
            sb.append(':');
            sb.append('0');
            sb.append("/");
            sb.append(storageCapacity);
            sb.append('\n');
        }
        return sb.toString();
    }
}
