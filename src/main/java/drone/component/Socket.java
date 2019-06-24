package drone.component;

public class Socket {

    private boolean carrying;
    private boolean storage;
    private Socket linked;
    private final Component parent;
    //Potential Future stats like weight Limit etc

    Socket(Component parent, boolean carrying) {
        this(parent, carrying, false);
    }

    Socket(Component parent, boolean carrying, boolean storage) {
        this.parent=parent;
        this.carrying = carrying;
        this.storage = storage;
    }

    public void setLinked(Socket linked) {
        assert linked.carrying!=carrying;//Only opposites may connect
        this.linked = linked;
        linked.reLink(this);
    }

    private void reLink(Socket linked){
        this.linked=linked;
    }

    public boolean isCarrying() {
        return carrying;
    }

    public boolean isStorage() {
        return storage;
    }

    public Component getLinkedComponent() {
        if(linked!=null)
        return linked.parent;
        return null;
    }

    public Socket getLinkedSocket() {
        return linked;
    }

    public boolean isLinked(){
        return linked!=null;
    }

    public Component getParent() {
        return parent;
    }


}
