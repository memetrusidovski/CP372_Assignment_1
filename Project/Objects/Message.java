package objects;

import java.io.Serializable;

public class Message implements Serializable {
    String message = null;
    String color;

    boolean status = false;

    int x = 0;
    int y = 0;
    int width = 1;
    int height = 1;

    public Message(String message){
        this.message = message;
    }
    
    public Message(String message, int x, int y) {
        this.message = message;
        this.x = x;
        this.y = y;
    }
    
    public Message(String message, int x, int y, int width, int height) {
        this.message = message;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getMessage(){
        return this.message;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getColour(){
        return this.color;
    }
}
