
import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = -6096595360876385808L;
	private RequestCommand command;
    private int x = -1;
    private int y = -1;
    private int width = -1;
    private int height = -1;
    private String message = "";
    private String color = "";


    Request(RequestCommand command){
    	this.command = command;
    }

    Request(RequestCommand command, String message){
    	this.command = command;
        this.message = message;
    }
    
    Request(RequestCommand command, int x, int y, String color, String message){
    	this.command = command;
        this.message = message;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    Request(RequestCommand command, int x, int y){
    	this.command = command;
    	this.x = x;
    	this.y = y;
    }

    Request(RequestCommand command, int x, int y, int width, int height, String color, String message){
    	this.command = command;
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	this.color = color;
    	this.message = message;
    }

    RequestCommand getCommand() {
    	return command;
    }

    int getX() {
    	return x;
    }

    int getY() {
    	return y;
    }

    int getWidth() {
    	return width;
    }

    int getHeight() {
    	return height;
    }

    String getColor() {
    	return color;
    }

    String getMessage() {
    	return message;
    }

}
