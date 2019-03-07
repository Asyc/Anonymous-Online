package me.asycc.anonymousonline.client.thread;

import me.asycc.anonymousonline.client.main.Client;
import me.asycc.anonymousonline.common.events.PacketReceivedEvent;
import me.asycc.anonymousonline.common.network.Packet;

import java.io.IOException;

/**
 *
 * A thread dedicated to reading from the {@link java.net.Socket} to
 * prevent blocking on the main thread.
 *
 * @author Asyc
 * @since 3/5/2019
 */
public class PacketListeningThread extends Thread{

    /**
     * While there is an active connection to the server, it will continuously
     * read from the server.
     */
    @Override
    public void run() {

        while (Client.instance.getConnection().isConnected()){
            Packet packet;

            try {
                packet = Client.instance.getConnection().read();
            }catch (IOException e){
                System.err.println("Could not read packet, ignoring.");
                continue;
            }

            if(packet == null) continue;
            Client.EVENT_BUS.post(new PacketReceivedEvent(packet));
        }
    }
}
