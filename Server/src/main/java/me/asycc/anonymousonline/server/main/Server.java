package me.asycc.anonymousonline.server.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import me.asycc.anonymousonline.common.utils.Version;
import me.asycc.anonymousonline.server.event.EventHandler;
import me.asycc.anonymousonline.server.network.Client;
import me.asycc.seb.EventBus;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * The server class, containing all the instances of objects, users, etc.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class Server extends Application {

    /**
     * The instance of the {@link Server} class.
     */
    public static volatile Server instance;

    /**
     * The {@link EventBus} that will be used as an event manager
     * throughout this project.
     */
    public static volatile EventBus EVENT_BUS = new EventBus();

    /**
     * The socket to run the server on
     */
    private volatile ServerSocket socket;

    /**
     * If this is set to true, no gui
     * will appear on screen.
     */
    private boolean noGUI = false;

    /**
     * A map of all the users connected the servers. The key is the user's id, which is the order
     * they joined the server, and the {@link Client} object.
     */
    private volatile Map<Integer, Client> clientMap = Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * An array of {@link Version} signifying the acceptable versions where the server can make a successful
     * connection with the client without error.
     */
    private final Version[] acceptableVersions = new Version[]{
            new Version(1, 0, 0),
    };

    /**
     * The "main" method to start the server
     *
     * @param primaryStage The stage to display a gui on
     */
    @Override
    public void start(Stage primaryStage) {
        EVENT_BUS.register(new EventHandler());

        try{
            this.startServer();
        }catch (IOException e){
            showError("Unable to start server", e.getMessage());
        }

        if(!noGUI){
            Pane pane;

            try{
                pane = FXMLLoader.load(this.getClass().getResource("/gui.fxml"));
            }catch (IOException e){
                e.printStackTrace();
                Platform.exit();
                return;
            }

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
        }
    }

    /**
     * Starts the server by checking the parameters specified, if no parameters
     * are specified, the server will be bound to the {@link InetAddress#getLocalHost()}
     * and the default port(42069)
     */
    private void startServer() throws IOException{

        InetAddress address = null;
        int port = 42069;
        int backlog = 8;

        //Looping through all the arguments specified to see if any user defined options were specified.
        for(int i = 0; i < getParameters().getRaw().size(); i++){
            String arg = getParameters().getRaw().get(i);

            if(arg.equalsIgnoreCase("nogui")){
                noGUI = true;
                i++;
            }else if(arg.equalsIgnoreCase("-ip")){
                try{
                    address = InetAddress.getByName(getParameters().getRaw().get(i + 1));
                }catch (ArrayIndexOutOfBoundsException e){
                    showError("Could not start server", "-ip flag specified, but no IP specified.");
                    Platform.exit();
                }catch (UnknownHostException e){
                    showError("Could not start server", "Invalid IP specified.");
                    Platform.exit();
                }
                i++;
            }else if(arg.equalsIgnoreCase("-port")){
                try{
                    port = Integer.parseInt(getParameters().getRaw().get(i + 1));
                }catch (NumberFormatException e){
                    showError("Could not start server", "Invalid port specified.");
                }catch (ArrayIndexOutOfBoundsException e){
                    showError("Could not start server", "-port flag specified, but no port specified.");
                }

                i++;
            }else if(arg.equalsIgnoreCase("-backlog")){
                try{
                    backlog = Integer.parseInt(getParameters().getRaw().get(i + 1));
                }catch (NumberFormatException e){
                    showError("Could not start server", "Invalid backlog specified.");
                }catch (ArrayIndexOutOfBoundsException e){
                    showError("Could not start server", "-backlog flag specified, but no backlog specified.");
                }

                i++;
            }
        }

        socket = new ServerSocket(port, backlog, address == null ? InetAddress.getLocalHost() : address);
    }


    /**
     *
     * A method to simplify creating error alerts. If {@link Server#instance#nogui} is true, then it will
     * print the error to console, otherwise it will show an {@link Alert}
     *
     * @param header The header text to display in the {@link Alert}
     * @param content The content text to display in the {@link Alert}
     */
    public static void showError(String header, String content){
        if(!Server.instance.noGUI){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        }else{
            System.err.println(content);
        }
    }

    /**
     * @return Returns the socket that the server runs on.
     */
    public ServerSocket getSocket() {
        return socket;
    }

    /**
     * @return Returns {@link Server#clientMap}
     */
    public Map<Integer, Client> getClientMap() {
        return clientMap;
    }

    /**
     * @return Returns {@link Server#acceptableVersions}
     */
    public Version[] getAcceptableVersions() {
        return acceptableVersions;
    }


}
