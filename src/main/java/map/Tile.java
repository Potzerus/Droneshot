package map;

public class Tile {

    private ResourceType resource;
    private int resourceAmount;

    public Tile(ResourceType resource, int resourceAmount) {
        this.resource = resource;
        this.resourceAmount = resourceAmount;
    }

    public Tile(ResourceType resource){
        this(resource, 0);
    }

    public Tile(){
        this(ResourceType.NONE, 0);
    }

    public ResourceType getResource() {
        return resource;
    }

    public void setResource(ResourceType resource){
        this.resource = resource;
    }

    public void setResource(ResourceType resource, int amount){
        this.resource = resource;
        this.resourceAmount = amount;
    }

    public int getResourceAmount(){
        return this.resourceAmount;
    }

    public void setResourceAmount(int amount){
        this.resourceAmount = amount;
    }

    public String toString(){
        return this.resource.toString();
    }

    public boolean equals(Object object){
        if (object == null){
            return false;
        }

        if (!object.getClass().equals(this.getClass())){
            return false;
        }

        Tile tile = (Tile) object;

        return tile.getResource().equals(this.getResource()) && tile.getResourceAmount() == this.resourceAmount;
    }

    public int extractResources(int amount){
        if (resourceAmount - amount < 0){
            int resources = resourceAmount;
            this.resourceAmount = 0;
            return resources;
        }else {
            resourceAmount -= amount;
            return amount;
        }
    }
}
