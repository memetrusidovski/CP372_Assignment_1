package objects;

import java.util.ArrayList;

public class GridCell {
    ArrayList<Message> messagePointers = new ArrayList<Message>();
    boolean hasPin = false;

    public void addPointer (Message m){
        this.messagePointers.add(m);
    }

    public boolean getPin(){
        return this.hasPin;
    }
}
