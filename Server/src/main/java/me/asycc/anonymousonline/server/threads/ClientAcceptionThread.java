package me.asycc.anonymousonline.server.threads;


import me.asycc.anonymousonline.server.main.Server;
import me.asycc.anonymousonline.server.network.Client;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * A new thread to accept client connections without hanging the main
 * or other client threads.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class ClientAcceptionThread extends Thread{

    /**
     * An infinite loop to accept new socket connections from clients, then creating a new thread
     * to continue the connect sequence for the client.
     */
    @Override
    public void run() {

        while (Server.instance.getSocket().isBound()){

            Socket clientSocket;

            try {
                clientSocket = Server.instance.getSocket().accept();
            }catch (IOException e){
                Server.showError("Error accepting client", e.getMessage());
                continue;
            }


            new Thread(() -> new Client(clientSocket), "Client-" + Server.instance.getClientMap().size() + 1);
        }
    }
}
