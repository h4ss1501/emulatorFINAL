package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class thePixle {

    //fields
    private int x;
    private int y;
    private int width;
    private int height;
    private int canvasMiddleX = x /2;
    private int canvasMiddleY = y /2;
    private UDPmessage receivedMessage;
    private Color myColor;
    protected boolean


    //drawable method, that allows us to draw.

    //the draw method will require a GraphicsContent, since it will be the pen that will allows to draw on the canvas.




    //constructor
    public thePixle (){

    }



    //drawing method
    public void drawPixle(GraphicsContext graphicsContext){

        graphicsContext.setFill(getMyColor());
        graphicsContext.setStroke(getMyColor());
        graphicsContext.strokeOval(x, y,width,height);



    }



    public void convertMesToCoordinates(UDPmessage receivedMessage){

        String mes2Cor = receivedMessage.getMessage();
       /* //coordinates
        int canvasMiddleX = this.canvasMiddleX;
        int canvasMiddleY = this.canvasMiddleY;


        double x = 0;
        double y = 0;*/

       if(mes2Cor.contains("init 9 9"){

       }
        /*
        if(mes2Cor.contains("moveup")){
            thePixle.set
        }else if(mes2Cor.contains("movedown")){
            x = canvasMiddleX;
            y = canvasMiddleY - 10.0;
        }else if(mes2Cor.contains("moveright")){
            x = canvasMiddleX + 10.0;
            y = canvasMiddleY;
        }else if(mes2Cor.contains("moveleft")){
            x = canvasMiddleX -10.0;
            y = canvasMiddleY;
        }

        */

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getReceivedMessage(){
        return toString();
    }

    public Color getMyColor() {
        return myColor;
    }

    public void setMyColor(Color myColor) {
        this.myColor = myColor;
    }

    //getters and setters

}