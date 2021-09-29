
import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable {
    public int width;
    public int height;
    public int pinCount;
    public ArrayList<ArrayList<GridCell>> grid = new ArrayList<ArrayList<GridCell>>();
    public ArrayList<Message> messageStack = new ArrayList<Message>();

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
        int getX = message.getX();
        int getY = message.getY();

        System.out.println(getX + "+" + getY);

        //GridCell c = this.getCell(getX, getY);

        for (int y = getY; y > getY - message.height && y >=0; y--) {
            //System.out.println("+++++++++");
            for (int x = getX; x < getX + message.width && x <= this.width; x++) {
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

    public void setPin(int x, int y) {
        this.grid.get(y).get(x).hasPin = true;
    }

}
