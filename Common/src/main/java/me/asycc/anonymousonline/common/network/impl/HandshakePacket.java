package me.asycc.anonymousonline.common.network.impl;


import me.asycc.anonymousonline.common.network.Packet;
import me.asycc.anonymousonline.common.utils.Version;

/**
 * A packet that contains information about the client.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class HandshakePacket implements Packet {

    /**
     * The client's chosen nickname.
     */
    private final String nickname;

    /**
     * The client's version of the software he/she is running.
     */
    private final Version version;

    /**
     * @param nickname The client's version of the software he/she is running.
     * @param version The client's chosen nickname.
     */
    public HandshakePacket(String nickname, Version version) {
        this.nickname = nickname;
        this.version = version;
    }

    /**
     * @return Returns the client's chosen nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return Returns the client's version of the software he/she is running.
     */
    public Version getVersion() {
        return version;
    }
}
