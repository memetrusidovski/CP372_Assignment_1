
import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable {

	private static final long serialVersionUID = -7500131554836560740L;
	public int width;
    public int height;
    public int pinCount;
    public ArrayList<ArrayList<GridCell>> grid = new ArrayList<ArrayList<GridCell>>();
    public ArrayList<Message> messageStack = new ArrayList<Message>();
    public ArrayList<String> colors;
    public ArrayList<int[]> pinLocations = new ArrayList<int[]>();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        // Add a new line to the grid
        for (int x = 0; x < height; x++) {
            grid.add(new ArrayList<GridCell>());

            // Add cells up to the width of the grid
            for (int y = 0; y < width; y++) {
                grid.get(x).add(new GridCell());
            }
        }
    }

    public GridCell getCell(int x, int y) {
        GridCell cell;

        cell = grid.get(x).get(y);

        return cell;
    }

    // DONE
    public void setMessage(Message message) {
        this.messageStack.add(message);

        int getX = message.getX();
        int getY = message.getY();

        System.out.println(getX + "+" + getY);

        //GridCell c = this.getCell(getX, getY);

        for (int y = getY; y > getY - message.getHeight() && y >=0; y--) {
            //System.out.println("+++++++++");
            for (int x = getX; x < getX + message.getWidth() && x <= this.width; x++) {
                //this.grid.get(x).get(y).hasPin = true;
                this.grid.get(x).get(y).messagePointers.add(message);
                //System.out.println("<<<<<<<<<<<<");
            }
        }
    }

    // For testing purposes
    public void printGrid() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if(this.grid.get(x).get(y).messagePointers.size() > 0){
                    System.out.print(" 1 ");
                }
                else{
                    System.out.print(" 0 ");
                }
            }
            System.out.println("\n");
        }
    }

    public boolean setPin(int x, int y) {
        boolean r = false;


        if(this.grid.get(x).get(y).hasPin == false) {
            this.pinLocations.add(new int[]{x, y});
            this.grid.get(x).get(y).hasPin = true;
            r = true;//Pin added successfully
        }
        else{
            System.out.println("There's Already a Pin at This Location");
        }

        return r;
    }

    public void removeMessage(Message removeMessage) {
        //this.messageStack.remove(removeMessage);
        int getX = removeMessage.getX();
        int getY = removeMessage.getY();

        for (int y = getY; y > getY - removeMessage.getHeight() && y >=0; y--) {
            for (int x = getX; x < getX + removeMessage.getWidth() && x <= this.width; x++) {
                this.grid.get(x).get(y).messagePointers.remove(removeMessage);
            }
        }
    }

    public void removePin(int x, int y){

        GridCell cell = this.getCell(x,y);

        if(cell.hasPin == true) {
            for (Message m : cell.messagePointers) {
                int c = this.messageStack.indexOf(m);
                this.messageStack.get(c).pinCount--;
            }
            cell.hasPin = false;

            this.pinLocations.removeIf( (e) -> e[0] == x && e[1] == y );

        }
        else{
            System.out.println("This Location Has No Pin.");
        }
    }
}
