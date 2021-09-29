import java.io.Serializable;

public class MessageOLD implements Serializable{
    String message = null;
    int x = 0;
    int y = 0;

    public MessageOLD(String message){
        this.message = message;
    }

    public MessageOLD(String message, int x, int y) {
        this.message = message;
        this.x = x;
        this.y = y;
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
}
