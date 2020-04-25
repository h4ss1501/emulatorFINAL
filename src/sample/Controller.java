package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;

import java.awt.*;


public class Controller {

    @FXML
    TableView <UDPmessage> table;
    @FXML
    ToggleButton toggleButtonEcho;
    @FXML
    ToggleButton toggleButtonBroadcast;
    @FXML
    private Canvas myCanvas;



    //fields
    private UDPConnector udpConnector;
    private UDPbroadcastServer broadcastServer;
    GraphicsContext myGraphicsContext;

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
        myGraphicsContext = myCanvas.getGraphicsContext2D();

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
/*
    private void startPainting(){

    }*/

    public void receiveMessage(UDPmessage udpMessage)
    {
        table.getItems().add(0, udpMessage);
    }
}
