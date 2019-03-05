package me.asycc.anonymousonline.server.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * @author Asyc
 * @since 3/4/2019
 */
public class Server extends Application {

    /**
     * The instance of the {@link Server} class.
     */
    public static volatile Server instance;

    /**
     * The socket to run the server on
     */
    private ServerSocket socket;



    /**
     * If this is set to true, no gui
     * will appear on screen.
     */
    private boolean nogui = false;


    /**
     * The "main" method to start the server
     *
     * @param primaryStage The stage to display a gui on
     */
    @Override
    public void start(Stage primaryStage) {

        Platform.exit();
    }

    /**
     * Starts the server by checking the parameters specified, if no parameters
     * are specified, the server will be bound to the {@link InetAddress#getLocalHost()}
     * and the default port(42069)
     */
    private void startServer() throws IOException {

        InetAddress address = null;
        int port = 42069;
        int backlog = 8;

        //Looping through all the arguments specified to see if any user defined options were specified.
        for(int i = 0; i < getParameters().getRaw().size(); i++){
            String arg = getParameters().getRaw().get(i);

            if(arg.equalsIgnoreCase("nogui")){
                nogui = true;
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
        if(!Server.instance.nogui){
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
}
