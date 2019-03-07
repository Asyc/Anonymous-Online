package me.asycc.anonymousonline.client.thread;

import me.asycc.anonymousonline.client.main.Client;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * A thread dedicated to updating the encryption keys
 * for the client and the server.
 */
public class UpdateThread extends Thread{

    @Override
    public void run() {

        while (Client.instance.getConnection().isConnected()){

            try{
                Client.instance.getConnection().updateKeys();
            }catch (IOException | GeneralSecurityException e) {
                System.err.println("Unable to update keyapair : " + e.getMessage());

                try {
                    Thread.sleep(2000);
                }catch (InterruptedException ie) {}

                return;
            }

            try {
                Thread.sleep(5000);
            }catch (InterruptedException e) {}
        }

    }
}
