package me.asycc.anonymousonline.client.event;

import me.asycc.anonymousonline.client.main.Client;
import me.asycc.anonymousonline.common.events.PacketReceivedEvent;
import me.asycc.anonymousonline.common.network.Packet;
import me.asycc.anonymousonline.common.network.impl.MessagePacket;
import me.asycc.anonymousonline.common.network.impl.RSAPacket;
import me.asycc.seb.annotation.EventSubscriber;

/**
 * A class used to handle events. Such as when packets are received, and when a user joins.
 *
 * @author Asyc
 * @since 3/6/2019
 */
public class EventHandler {

    @EventSubscriber
    public void onPacketReceived(PacketReceivedEvent event){
        Packet packet = event.getPacket();

        if(packet instanceof MessagePacket){
            Client.instance.appendText(((MessagePacket) packet).getMessage());
        }else if(packet instanceof RSAPacket){
            Client.instance.getConnection().setPublicKey(((RSAPacket) packet).getKey());
        }

    }
}
