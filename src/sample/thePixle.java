package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class thePixle {

    //fields
    protected int horizontal;
    protected int vertical;
    protected int width;
    protected int height;
    protected int canvasMiddleX = horizontal/2;
    protected int canvasMiddleY = vertical/2;
    protected UDPmessage receivedMessage;
    protected Color myColor;


    //drawable method, that allows us to draw.

    //the draw method will require a GraphicsContent, since it will be the pen that will allows to draw on the canvas.




    //constructor
    public thePixle (UDPmessage receivedMessage){

    }



    //drawing method
    public void drawPixle(GraphicsContext graphicsContext){

        graphicsContext.setFill(myColor);
        graphicsContext.strokeOval(horizontal, vertical,width,height);



    }



    public void convertMesToCoordinates(UDPmessage receivedMessage, GraphicsContext graphicsContext){

        String mes2Cor = receivedMessage.getMessage();
        //coordinates
        int canvasMiddleX = this.canvasMiddleX;
        int canvasMiddleY = this.canvasMiddleY;


        double x = 0;
        double y = 0;

        if(mes2Cor.contains("moveup")){
            x = canvasMiddleX;
            y = canvasMiddleY + 10.0;
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

        graphicsContext.setFill(myColor);
        graphicsContext.strokeOval(x,y,width,height);


    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
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