package objects;

import java.util.ArrayList;

public class Grid {
    public int width;
    public int height;
    public int pinCount;
    public ArrayList<ArrayList<GridCell>> grid = new ArrayList<ArrayList<GridCell>>();

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
            System.out.println("+++++++++");
            for (int x = getX; x < getX + message.width && x <= this.width; x++) {
                this.grid.get(y).get(x).hasPin = true;
                System.out.println("<<<<<<<<<<<<");
            }
        }
    }

    // For testing purposes
    public void printGrid() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                System.out.print(this.grid.get(y).get(x).hasPin + " ");
            }
            System.out.println("\n");
        }
    }

    public void setPin(int x, int y) {
        this.grid.get(y).get(x).hasPin = true;
    }

}
