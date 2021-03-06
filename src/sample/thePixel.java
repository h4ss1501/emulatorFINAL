package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class thePixel extends DrawableObject{

    //pixel class extends the abstract class DrawableObject

    //fields
    private int x;
    private int y;
    private int width = 15;
    private int height = 15;
    private Color myColor = Color.BLACK;
    private int mySpeed;
    protected boolean activator;




    //drawable method, that allows us to draw.

    //the draw method will require a GraphicsContent, since it will be the pen that will allows to draw on the canvas.
    //drawing method
    @Override
    public void DrawObject(GraphicsContext graphicsContext) {
        graphicsContext.setFill(getMyColor());
        graphicsContext.setStroke(getMyColor());
        graphicsContext.strokeOval(x, y,width,height);


    }

    //getters and setters
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

    public boolean isActivator() {
        return activator;
    }

    public void setActivator(boolean activator) {
        this.activator = activator;
    }

    public int getMyspeed() {
        return mySpeed;
    }

    public void setMyspeed(int myspeed) {

        this.mySpeed = myspeed;
    }



}