
import java.io.Serializable;
import java.util.ArrayList;

public class GridCell implements Serializable {
    
	private static final long serialVersionUID = 105913395971605054L;
	ArrayList<Message> messagePointers = new ArrayList<Message>();
    public boolean hasPin = false;

    public void addPointer (Message m){
        this.messagePointers.add(m);
    }

    public boolean getPin(){
        return this.hasPin;
    }
}
