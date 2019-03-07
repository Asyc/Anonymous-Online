package me.asycc.anonymousonline.common.network.impl;

import me.asycc.anonymousonline.common.network.Packet;

/**
 * @author Asyc
 * @since 3/5/2019
 */
public class MessagePacket implements Packet {

    /**
     * A string containing a message sent by a client.
     */
    private String message;

    /**
     * @param message The message sent by a client.
     */
    public MessagePacket(final String message){
        this.message = message;
    }

    /**
     * @return Returns {@link MessagePacket#message}
     */
    public String getMessage() {
        return message;
    }
}
