package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class thePixle {

    //fields
    protected int horizontal;
    protected int vertical;
    protected int width;
    protected int height;
    protected String receivedMessage;
    protected Color myColor;


    //drawable method, that allows us to draw.

    //the draw method will require a GraphicsContent, since it will be the pen that will allows to draw on the canvas.

    //abstract class
    public void drawPixle(GraphicsContext graphicsContext){

        graphicsContext.setFill(myColor);
        graphicsContext.fillRect(horizontal, vertical,width,height);



    }

    public void movement(String command, int speed){

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
        return receivedMessage;
    }

    public Color getMyColor() {
        return myColor;
    }

    public void setMyColor(Color myColor) {
        this.myColor = myColor;
    }

    //getters and setters

}