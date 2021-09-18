package objects;

import java.util.ArrayList;

public class Grid {
    public int width;
    public int height;
    public ArrayList<ArrayList<GridCell>> grid = new ArrayList<ArrayList<GridCell>>();

    public Grid(int width, int height){
        this.width = width;
        this.height = height;

        //Add a new line to the grid
        for(int x = 0; x < height; x++){
            grid.add(new ArrayList<GridCell>());

            //Add cells up to the width of the grid
            for(int y = 0; y < width; y++){
                grid.get(x).add(new GridCell());
            }
        }
    }

    public GridCell getCell(int x, int y){
        GridCell cell;

        cell = grid.get(x).get(y);

        return cell;
    }

    //NOT DONE
    public void setCell(Message message){
        GridCell x = this.getCell(message.getX(),message.getY());
        
    }

    //For testing purposes
    public void printGrid(){
        for(int y = 0 ; y < 5; y++){
            for(int x = 0; x < 5 ; x++){
                    System.out.print(this.grid.get(y).get(x).hasPin + " ");
            }
            System.out.println("\n");
        }
    }
    
}
