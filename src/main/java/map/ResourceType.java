package map;

public enum ResourceType {
    //Used to operate Electric Devices
    ENERGY("Energy"),
    //Used to construct Metal components
    METAL("Metal"),
    //Used to construct Electric Devices
    SILICON("Silicon"),
    //Used in power Generation
    CRYSTALS("Crystals"),
    ;

    private String name;

    ResourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}