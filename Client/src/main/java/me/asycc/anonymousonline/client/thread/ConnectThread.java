package me.asycc.anonymousonline.client.thread;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import me.asycc.anonymousonline.client.main.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.security.GeneralSecurityException;

/**
 *
 * A thread dedicated to connecting to the server to prevent the
 * main thread from being blocked, causing the GUI to freeze.
 *
 * @author Asyc
 * @since 3/5/2019
 */
public class ConnectThread extends Thread{

    /**
     * The IP address of the server to connect to.
     */
    private final InetAddress address;

    /**
     * The port of the server to connect to.
     */
    private final int port;

    /**
     * @param address The server address to connect to.
     * @param port The port to connect to.
     */
    public ConnectThread(final InetAddress address, final int port){
        this.address = address;
        this.port = port;
    }

    /**
     * Connects to the server.
     */
    @Override
    public void run() {

        try {
            Client.instance.getConnection().connect(address, port, "");
        }catch (IOException | GeneralSecurityException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not connect to server");
            alert.setContentText(e.getMessage());
            Platform.runLater(alert::showAndWait);
            return;
        }

        Platform.runLater(() -> {
            try{
                Client.instance.initializeChatWindow();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
