import java.util.ArrayList;

public class Response {

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

    Response(Grid gird){ this.grid = grid; }

}
