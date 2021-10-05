import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable{


	private static final long serialVersionUID = 3803052751744444021L;
	private int x = -1;
    private int y = -1;
    private int width = -1;
    private int height = -1;
    private Message message;
    private ArrayList<Message> messagesList;
    private ArrayList<String> color;
    private Grid grid;
    private String pins;//Possible Change of Format

    Response(Message message){ this.message = message; }

    Response(String s){ this.pins = s; }

    Response(ArrayList<Message> messagesList){ this.messagesList = messagesList; }

    //Pin Location
    Response(int x, int y){
        this.x = x;
        this.y = y;
    }

    Response(Grid grid){ this.grid = grid; }

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

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public ArrayList<Message> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(ArrayList<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public void setColor(ArrayList<String> color) {
        this.color = color;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public String getPins() {
        return pins;
    }

    public void setPins(String pins) {
        this.pins = pins;
    }

}
