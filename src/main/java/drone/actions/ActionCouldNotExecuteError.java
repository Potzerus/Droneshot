package drone.actions;

import launcher.Center;
import potz.utils.database.Char;

public class    ActionCouldNotExecuteError extends Error {
    public ActionCouldNotExecuteError(String message, Char player){
        super(message);
        Center.getApi().getUserById(player.getId()).join().openPrivateChannel().join().sendMessage(
                "Action aborted on "+player.getParent().getServerName()+"\nReason: "+message);
    }
}
