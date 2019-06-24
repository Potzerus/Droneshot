package map;

public enum ResourceType {
    NONE(""), WOOD("Wood"), CRYSTALS("Crystals"), METAL("Metal");

    private String name;
    ResourceType(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}