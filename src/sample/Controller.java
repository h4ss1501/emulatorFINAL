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
            //dividing height and width of the canvas by 2 to land in the middle of the canvas
            myPixel.setX((int) myCanvas.getWidth() / 2);
            myPixel.setY((int) myCanvas.getHeight() / 2);
            table.getItems().add(0, udpMessage); // updates table
            drawOnCanvas(); //draws the pixle on canvas if initialized
        }

        if (myPixel.isActivator() == true){

            //switch statement that sets the speed on the pixle

            switch(udpMessage.getMessage()){
                case "s1":
                    myPixel.setMyspeed(5);
                    break;
                case "s2":
                    myPixel.setMyspeed(10);
                    break;
                case "s3":
                    myPixel.setMyspeed(15);
                    break;
                case "s4":
                    myPixel.setMyspeed(20);
                    break;
            }

            //if statements that moves the pixel positions based on a command
            //nested if-statement secures that the pixel stays within the boundaries of the canvas, by setting speed to 0

            if (udpMessage.getMessage().contains("moveup")) {
                myPixel.setY(myPixel.getY() - myPixel.getMyspeed());
                if(myPixel.getY() < 0){
                    myPixel.setMyspeed(0);
                }
            }
            if (udpMessage.getMessage().contains("movedown")) {
                myPixel.setY(myPixel.getY() + myPixel.getMyspeed());
                    if(myPixel.getX() > myCanvas.getWidth()){
                        myPixel.setMyspeed(0);
                    }
            }
            if (udpMessage.getMessage().contains("moveright")) {
                myPixel.setX(myPixel.getX() + myPixel.getMyspeed());
                if(myPixel.getX() > myCanvas.getWidth()){
                    myPixel.setMyspeed(0);
                }
            }
            if (udpMessage.getMessage().contains("moveleft")) {
                myPixel.setX(myPixel.getX() - myPixel.getMyspeed());
                if(myPixel.getX() > myCanvas.getWidth()) {
                    myPixel.setMyspeed(0);
                }
            }
            table.getItems().add(0, udpMessage);
            drawOnCanvas();

        }else{
            myPixel.setActivator(false);
            System.out.println("COMMAND NOT ACCEPTED: Please intiate the pixle first by clicking the controller");
        }


    }
}
