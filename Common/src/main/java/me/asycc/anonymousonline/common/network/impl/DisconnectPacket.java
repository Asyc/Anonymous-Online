package me.asycc.anonymousonline.common.network.impl;


import me.asycc.anonymousonline.common.network.Packet;


/**
 * @author Asyc
 * @since 3/5/2019
 */
public class DisconnectPacket implements Packet {

    /**
     * A string containing the reason for disconnect.
     */
    private String reason;

    /**
     * @param reason The reason for disconnect.
     */
    public DisconnectPacket(String reason){
        this.reason = reason;
    }

    /**
     * @return Returns the reason for disconnecting.
     */
    public String getReason() {
        return reason;
    }
}
