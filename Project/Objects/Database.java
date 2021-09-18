package objects;

import java.util.ArrayList;

public class Database {
    public Grid grid;
    public ArrayList<String> colors = new ArrayList<String>();
    public ArrayList<Message> messageStack = new ArrayList<Message>();
    public ArrayList<Message> pins = new ArrayList<Message>();
    public int boardWidth;
    public int boardHeight;

    public Database(int width, int height, ArrayList<String> colors){
        this.grid = new Grid(width, height);
        this.boardHeight = height;
        this.boardWidth = width;
        this.colors = colors;
    }

    
}
