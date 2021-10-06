import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<int[]> pins;
    private String errorMessage;

    Response(Message message){ this.message = message; }

    Response(List<int[]> pins){ this.pins = (ArrayList<int[]>) pins; }

    Response(ArrayList<Message> messagesList){ this.messagesList = messagesList; }

    Response(String s ){ this.errorMessage = s;}

    //Pin Location
    Response(int x, int y){
        this.x = x;
        this.y = y;
    }

    Response(Grid grid){ this.grid = grid; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Message getMessage() {
        return message;
    }

    public ArrayList<Message> getMessagesList() {
        return messagesList;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public Grid getGrid() {
        return grid;
    }

    public ArrayList<int[]> getPins() {
        return pins;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
