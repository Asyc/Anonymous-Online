package me.asycc.anonymousonline.server.event;

import me.asycc.anonymousonline.server.event.events.PacketReceivedEvent;
import me.asycc.anonymousonline.server.event.events.UserConnectEvent;
import me.asycc.anonymousonline.server.event.events.UserDisconnectEvent;
import me.asycc.seb.annotation.EventSubscriber;

/**
 * A class used for handling events.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class EventHandler {

    //for future implementation

    @EventSubscriber
    public void onUserDisconnect(UserDisconnectEvent event){
    }

    @EventSubscriber
    public void onUserConnect(UserConnectEvent event){

    }

    @EventSubscriber
    public void onPacketReceived(PacketReceivedEvent event){
        //todo : handle
    }
}
