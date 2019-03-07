package me.asycc.anonymousonline.common.events;

/**
 * An event class used to store information about a user connecting
 * and invoke methods to act upon that event.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class UserDisconnectEvent {

    /**
     * The ID of the user that disconnected from the server.
     */
    private final int id;

    /**
     * @param id The ID of the user that disconnected from server.
     */
    public UserDisconnectEvent(final int id){
        this.id = id;
    }

    /**
     * @return Returns the ID of the user that disconnected from server.
     */
    public int getID() {
        return id;
    }
}
