package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;



public class Controller {

    @FXML
    private TableView<UDPmessage> table;
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
    public thePixel myPixle = new thePixel();


    //fields
    private UDPConnector udpConnector;
    private UDPbroadcastServer broadcastServer;


    //buttons
    public void toggleButtonEchoServer() {

        System.out.println("toggleButtonEcho Clicked!");
        if (udpConnector.isReceiveMessages()) {
            udpConnector.setReceiveMessages(false);
            toggleButtonEcho.setText("OFF");
        } else {

            startUdpConnection();
            toggleButtonEcho.setText("ON");

        }
    }

    public void toggleButtonBroadcastServer() {
        System.out.println("togglebtnBROADCAST clicked");
        if (broadcastServer.isBroadcast()) {
            broadcastServer.setBroadcast(false);
            toggleButtonBroadcast.setText("OFF");
        } else {
            startBroadcasting();
            toggleButtonBroadcast.setText(("ON"));
        }
    }

    public void initialize() {
        System.out.println("initialize");

        startUdpConnection();
        startBroadcasting();
        graphContext = myCanvas.getGraphicsContext2D();

    }

    public void drawOnCanvas() {
        clearCanvas(); //we will be clearing canvas everytime, we update
        myPixle.DrawObject(graphContext);
    }

    public void clearCanvas() {
        graphContext.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
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


    public void receiveMessage(UDPmessage udpMessage) {
        if (udpMessage.getMessage().contains("init 9 9")) {
            myPixle.setActivator(true);
            //casting as an int
            myPixle.setX((int) myCanvas.getWidth() / 2);
            myPixle.setY((int) myCanvas.getHeight() / 2);
            myPixle.setMyspeed(5);
            table.getItems().add(0, udpMessage);
            drawOnCanvas(); //draws the pixle on canvas if initialized
        }

        if (myPixle.isActivator() == true) {

            if (udpMessage.getMessage().contains("speed 1")) {
                myPixle.setMyspeed(10);
            }
            if (udpMessage.getMessage().contains("speed 4")) {
                myPixle.setMyspeed(15);
            }
            if (udpMessage.getMessage().contains("speed 7")) {
                myPixle.setMyspeed(20);
            }
            if (udpMessage.getMessage().contains("speed 9")) {
                myPixle.setMyspeed(25);

                if (udpMessage.getMessage().contains("moveup")) {
                    myPixle.setY(myPixle.getY() - myPixle.getMyspeed());
                }
                if (udpMessage.getMessage().contains("movedown")) {
                    myPixle.setY(myPixle.getY() + myPixle.getMyspeed());
                }
                if (udpMessage.getMessage().contains("moveright")) {
                    myPixle.setX(myPixle.getX() + myPixle.getMyspeed());
                }
                if (udpMessage.getMessage().contains("moveleft")) {
                    myPixle.setX(myPixle.getX() - myPixle.getMyspeed());
                }
                table.getItems().add(0, udpMessage);
                drawOnCanvas();

            } else {
                myPixle.setActivator(false);
                System.out.println("COMMAND NOT ACCEPTED: Please intiate the pixle first by clicking the controller");
            }


        }
    }
}
