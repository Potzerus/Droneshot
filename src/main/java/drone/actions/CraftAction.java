package drone.actions;

import drone.component.Socket;

public interface CraftAction extends Action {

    /**Queues a craft action that creates a new Component and links it up to the provided Socket
     *
     * @param resources use valaynas resource enum.values() as index
     *                  and see how many resources are available
     * @param output Socket that new Component will be attached to
     *               must be Empty!
     * @return wether or not the Operation was queued successfully
     */
    boolean setupAction(int[] resources, Socket output);
}
