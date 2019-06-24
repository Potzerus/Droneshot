package drone.actions;

public interface Move extends Action {

    //Todo:Replace int with Direction Enum
    boolean setupAction(int moveAmount,int direction);
}
