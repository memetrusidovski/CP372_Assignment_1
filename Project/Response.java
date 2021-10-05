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

    Response(Message message){ this.message = message; }

    Response(ArrayList<Message> messagesList){ this.messagesList = messagesList; }

    //Pin Location
    Response(int x, int y){
        this.x = x;
        this.y = y;
    }

    Response(Grid grid){ this.grid = grid; }

}
