package me.asycc.anonymousonline.common.network.impl;


import me.asycc.anonymousonline.common.network.Packet;

import java.security.PublicKey;

/**
 * A class used to store a {@link java.security.PublicKey} and to be serialized
 * and sent through a socket so the client can receive a {@link java.security.PublicKey} for encryption.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class RSAPacket implements Packet {

    /**
     * The {@link PublicKey to be sent to the client}.
     */
    private PublicKey key;

    /**
     * @param key The {@link PublicKey} to be sent to the client.
     */
    public RSAPacket(final PublicKey key){
        this.key = key;
    }

    /**
     * @return Returns {@link RSAPacket#key}
     */
    public PublicKey getKey() {
        return key;
    }
}
