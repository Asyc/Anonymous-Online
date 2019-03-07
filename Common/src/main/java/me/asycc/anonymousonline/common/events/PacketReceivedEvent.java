package me.asycc.anonymousonline.common.events;

import me.asycc.anonymousonline.common.network.Packet;

/**
 * An event class used to store information about a packet being received
 * and invoke methods to act upon that event.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class PacketReceivedEvent {

    /**
     * The packet received.
     */
    private Packet packet;

    /**
     * @param packet The packet received.
     */
    public PacketReceivedEvent(final Packet packet){
        this.packet = packet;
    }

    /**
     * @return Returns the packet received.
     */
    public Packet getPacket() {
        return packet;
    }
}
