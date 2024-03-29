
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
        this.grid.colors = colors;
        this.boardHeight = height;
        this.boardWidth = width;
        this.colors = colors;
    }

    public void addMessage(Message message){
        this.messageStack.add(message);
        this.grid.messageStack.add(message);
    }

    public ArrayList<Message> searchMessagesByString(String s){
        ArrayList<Message> lst = new ArrayList<Message>();

        System.out.println(s + "<<<<<<<<<");
        for(Message m: this.grid.messageStack){
            if(m.getMessage().contains(s)){
                lst.add(m);
            }
        }
        return lst;
    }

    public ArrayList<Message> searchMessagesByLocation(int x, int y){
        ArrayList<Message> lst = grid.getCell(x, y).messagePointers;

        return lst;
    }

    public ArrayList<Message> searchMessagesByColour(String colour){
        ArrayList<Message> lst = new ArrayList<Message>();

        for(Message m: this.grid.messageStack){
            if(m.getColour().compareTo( colour ) == 0 ){
                lst.add(m);
            }
        }

        return lst;
    }

    public ArrayList<Message> searchMessagesByMulti(String s, String colour, int x, int y){
        ArrayList<Message> lst = new ArrayList<Message>();


        if(!s.isBlank()) {
            for(Message m: this.grid.messageStack){
                if(m.getMessage().contains(s)){
                	lst.add(m);
                }
            }
        }
        else {
            lst = this.grid.messageStack;
        }


        if(!colour.isBlank()) {
        	ArrayList<Message> temp = new ArrayList<Message>(lst);
            for(Message m: temp){
                if(m.getColour() != colour){
                    lst.remove(m);
                }
            }
        }

        if(x != -1 && y != -1) {
        	ArrayList<Message> temp = new ArrayList<Message>(lst);
            for(Message m: temp) {
                if (grid.getCell(x,y).messagePointers.contains(m) == false) {
                    lst.remove(m);
                }
            }
        }

        return lst;
    }


}
