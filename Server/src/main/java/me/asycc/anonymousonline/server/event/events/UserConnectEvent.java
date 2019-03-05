package me.asycc.anonymousonline.server.event.events;

/**
 * An event class used to store information about a user disconnecting
 * and invoke methods to act upon that event.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class UserConnectEvent {

    /**
     * The ID of the user that connected to the server.
     */
    private final int id;

    /**
     * @param id The ID of the user that connected to the server.
     */
    public UserConnectEvent(final int id){
        this.id = id;
    }

    /**
     * @return Returns the ID of the user that connected to the server.
     */
    public int getID() {
        return id;
    }
}
