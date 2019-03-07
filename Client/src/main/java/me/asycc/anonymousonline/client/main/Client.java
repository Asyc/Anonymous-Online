package me.asycc.anonymousonline.client.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import me.asycc.anonymousonline.client.event.EventHandler;
import me.asycc.anonymousonline.client.network.Connection;
import me.asycc.anonymousonline.common.network.impl.MessagePacket;
import me.asycc.anonymousonline.common.utils.Version;
import me.asycc.seb.EventBus;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 *
 * The main class that contains all the information about the client and instances
 * of other classes, as well as containing the "main" method and UI related functions.
 *
 * @author Asyc
 * @since 3/5/2019
 */
public final class Client extends Application {

    /**
     * Used to send data through a socket and receive data.
     */
    private volatile Connection connection;

    /**
     * The global instance of {@link Client}
     */
    public static volatile Client instance;

    /**
     * The {@link Stage} to display the GUI on.
     */
    private volatile Stage stage;

    /**
     * The version of the client stored in an object {@link Version}.
     */
    public final Version version = new Version(1, 0, 0);

    /**
     * The {@link EventBus} that will be used to post events
     * throughout this project.
     */
    public static final EventBus EVENT_BUS = new EventBus();

    /**
     * The {@link TextField} that the user types a message into that will be
     * submitted to the server.
     */
    private TextField msgField;

    /**
     * The {@link TextArea} that all messages from the server appear.
     */
    private TextArea msgArea;

    /**
     * The "main" method to start the client.
     *
     * @param stage The stage to display a gui on.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        EVENT_BUS.register(new EventHandler());

        instance = this;

        this.stage = stage;
        stage.setOnCloseRequest((EventHandler) -> Platform.exit());

        //initializeConnectWindow(stage);
        initializeChatWindow();
    }

    /**
     *
     * Sets the scene to the chat GUI and adds respective {@link javafx.event.EventHandler}
     *
     * @throws IOException Thrown if the GUI cannot be loaded from the .fxml file.
     */
    public void initializeChatWindow() throws IOException {
        this.connection = new Connection();
        Pane pane = FXMLLoader.load(this.getClass().getResource("/chat.fxml"));
        this.msgField = (TextField) pane.lookup("#TextField");
        this.msgArea = (TextArea) pane.lookup("#TextArea");
        Button button = (Button) pane.lookup("#btnMessage");
        button.setOnMousePressed((EventHandler) -> this.submitMessage());

        pane.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                button.fire();
            }
        });

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the scene to the connecting window and adds respective {@link javafx.event.EventHandler}
     *
     * @param stage The stage to display the gui on.
     * @throws IOException Thrown if the GUI cannot be loaded from the .fxml file.
     */
    private void initializeConnectWindow(final Stage stage) throws IOException{
        Pane pane = FXMLLoader.load(this.getClass().getResource("/connect.fxml"));

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets the text in the {@link TextField} on the GUI, and sends a {@link MessagePacket} to
     * the server and clears the {@link TextField}.
     */
    private void submitMessage(){
        String message = msgField.getText();

        if(message.length() > connection.size){
            //todo : display error
            return;
        }

        try{
            connection.send(new MessagePacket(message));
        }catch (IOException | GeneralSecurityException e){
            //todo : display error
            return;
        }

        msgField.clear();
    }

    /**
     * This appends a {@link String} to the {@link javafx.scene.control.TextArea}
     * on the GUI.
     * @param text The text to add to the {@link javafx.scene.control.TextArea}.
     */
    public void appendText(String text){
        msgArea.appendText(text);
    }

    /**
     * @return Returns {@link Client#connection}
     */
    public Connection getConnection() {
        return connection;
    }

}
