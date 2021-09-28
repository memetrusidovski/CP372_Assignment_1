
import java.io.Serializable;
import java.util.ArrayList;

public class GridCell implements Serializable {
    ArrayList<Message> messagePointers = new ArrayList<Message>();
    public boolean hasPin = false;

    public void addPointer (Message m){
        this.messagePointers.add(m);
    }

    public boolean getPin(){
        return this.hasPin;
    }
}
