package me.asycc.anonymousonline.server.threads;


import me.asycc.anonymousonline.server.main.Server;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Asyc
 * @since 3/4/2019
 */
public class ClientAcceptionThread extends Thread{

    @Override
    public void run() {

        while (true){

            Socket clientSocket;

            try {
                clientSocket = Server.instance.getSocket().accept();
            }catch (IOException e){
                Server.showError("Error accepting client", e.getMessage());
                continue;
            }



        }
    }
}
