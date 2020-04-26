package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;



public class Controller {

    @FXML
    private TableView <UDPmessage> table;
    @FXML
    ToggleButton toggleButtonEcho;
    @FXML
    ToggleButton toggleButtonBroadcast;
    @FXML
    private Canvas myCanvas;
    @FXML
    ToggleButton clearCanvasButton;
    @FXML
    ToggleButton clearTableButton;

    private GraphicsContext graphContext;

    //instantiating the pixle.
    public thePixel myPixel = new thePixel();



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
        graphContext = myCanvas.getGraphicsContext2D();

    }

    public void drawOnCanvas(){
        clearCanvas(); //we will be clearing canvas everytime, we update
        myPixel.DrawObject(graphContext);
    }

    public void clearCanvas(){
        graphContext.clearRect(0,0,myCanvas.getWidth(),myCanvas.getHeight());
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
        if(udpMessage.getMessage().contains("init 9 9")) {
            myPixel.setActivator(true);
            //casting as an int
            myPixel.setX((int) myCanvas.getWidth() / 2);
            myPixel.setY((int) myCanvas.getHeight() / 2);
            table.getItems().add(0, udpMessage);
            drawOnCanvas(); //draws the pixle on canvas if initialized
        }

        if (myPixel.isActivator() == true) {

            if (udpMessage.getMessage().contains("speed1")) {
                myPixel.setMyspeed(10);
            }
            if (udpMessage.getMessage().contains("speed4")) {
                myPixel.setMyspeed(15);
            }
            if (udpMessage.getMessage().contains("speed7")) {
                myPixel.setMyspeed(20);
            }
            if (udpMessage.getMessage().contains("speed9")) {
                myPixel.setMyspeed(25);
            } else
                myPixel.setMyspeed(1);


            if (udpMessage.getMessage().contains("moveup")) {
                myPixel.setY(myPixel.getY() - myPixel.getMyspeed());
            }
            if (udpMessage.getMessage().contains("movedown")) {
                myPixel.setY(myPixel.getY() + myPixel.getMyspeed());
            }
            if (udpMessage.getMessage().contains("moveright")) {
                myPixel.setX(myPixel.getX() + myPixel.getMyspeed());
            }
            if (udpMessage.getMessage().contains("moveleft")) {
                myPixel.setX(myPixel.getX() - myPixel.getMyspeed());
            }
            table.getItems().add(0, udpMessage);
            drawOnCanvas();

        }else{
            myPixel.setActivator(false);
            System.out.println("COMMAND NOT ACCEPTED: Please intiate the pixle first by clicking the controller");
        }


    }
}
