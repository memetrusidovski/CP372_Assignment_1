import java.util.ArrayList;

public class Grid {
    int width;
    int height;
    ArrayList<ArrayList<GridCell>> grid = new ArrayList<ArrayList<GridCell>>();

    public Grid(int width, int height){
        this.width = width;
        this.height = height;

        //Add a new line to the grid
        for(int x = 0; x <= height; x++){
            grid.add(new ArrayList<GridCell>());
            
            //Add cells up to the width of the grid
            for(int y = 0; y <= width; y++){
                grid.get(0).add(new GridCell());
            }
        }
    }
    
}
