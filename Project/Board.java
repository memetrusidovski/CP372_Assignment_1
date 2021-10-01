import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    public int[] colour = { 100, 100, 100 };
    int x = 50;
    int y = 50;
    int width = 50;
    int height = 50;
    String message = "Hello welcome message";
    Grid grid;

    Board(Grid grid){
        this.grid = grid;
        this.setPreferredSize(new Dimension(500,500));
    }

    void setMessage(){

    }

    public void paint(Graphics g) {
        Graphics2D gr = (Graphics2D) g;

        //gr.setColor(new Color(125, 167, 116));

        for(int x = this.x; x< this.x + this.width; x+=1)
            for(int y = this.y; y< this.y + this.height; y+=1){
                gr.fillRect(x, y, 10, 10);
                gr.setColor(new Color(this.colour[0], this.colour[1], this.colour[2]));
                gr.drawString(this.grid != null ? this.grid.messageStack.get(0).message : "NONE", this.x,this.y);
            }

    }
}
