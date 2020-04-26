package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.collections.ObservableList;


public class Controller {

    ObservableList<thePixle> pixel = FXCollections.observableArrayList();

    @FXML
    private TableView <UDPmessage> table;
    @FXML
    ToggleButton toggleButtonEcho;
    @FXML
    ToggleButton toggleButtonBroadcast;
    @FXML
    private Canvas myCanvas;

    private GraphicsContext Gcontext;



    //fields
    private UDPConnector udpConnector;
    private UDPbroadcastServer broadcastServer;


    //buttons
    public void toggleButtonEchoServer() {

        System.out.println("toggleButtonEcho Clicked!");
        if (udpConnector.isReceiveMessages())
        {
            udpConnector.setReceiveMessages(false);
            toggleButtonEcho.setText("OFF");
        }
        else{

        startUdpConnection();
        toggleButtonEcho.setText("ON");

    }
    }

    public void toggleButtonBroadcastServer()
    {
        System.out.println("togglebtnBROADCAST clicked");
        if (broadcastServer.isBroadcast())
        {
            broadcastServer.setBroadcast(false);
            toggleButtonBroadcast.setText("OFF");
        }
        else
        {
            startBroadcasting();
            toggleButtonBroadcast.setText(("ON"));
        }
    }

    public void initialize()
    {
        System.out.println("initialize");

        startUdpConnection();
        startBroadcasting();

        //initializing graphicsContext pen for drawing on canvas
        Gcontext = myCanvas.getGraphicsContext2D();

    }
    public void clearLog() {
        table.getItems().clear();
        System.out.println("Table is cleared!");
    }

    private void startBroadcasting() {
        broadcastServer = new UDPbroadcastServer();
        new Thread(broadcastServer).start();
    }

    private void startUdpConnection() {
        if (udpConnector != null) udpConnector.closeSocket();
        udpConnector = new UDPConnector(this);
        new Thread(udpConnector).start();
    }


    public void receiveMessage(UDPmessage udpMessage)
    {
        table.getItems().add(0, udpMessage);
    }
}
