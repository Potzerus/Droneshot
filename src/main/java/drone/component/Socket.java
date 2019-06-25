package drone.component;

public class Socket {

    private boolean plus;
    private boolean storage;
    private Socket linked;
    private final Component parent;
    //Potential Future stats like weight Limit etc

    Socket(Component parent, boolean plus) {
        this(parent, plus, false);
    }

    Socket(Component parent, boolean plus, boolean storage) {
        this.parent=parent;
        this.plus = plus;
        this.storage = storage;
    }

    public void setLinked(Socket linked) {
        assert linked.plus != plus;//Only opposites may connect
        this.linked = linked;
        linked.reLink(this);
    }

    public void breakLink(){
        linked=null;
    }

    private void reLink(Socket linked){
        this.linked=linked;
    }

    public boolean isPlus() {
        return plus;
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

    @Override
    public String toString() {
        return (plus?"+":"-")+(linked!=null?getLinkedComponent().getType().getName():"Disconnected");
    }

    public void attach(Component component) {
        setLinked(component.getFreeSocket(!plus));
    }
}
