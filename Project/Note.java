
import javax.swing.*;
import java.awt.*;

public class Note extends JPanel {
    
	private static final long serialVersionUID = -2184638162077818005L;
	public int[] colour;
    int x;
    int y;
    int width;
    int height;
    String message;

    Note(int x, int y, int width, int height, int[] colour){
        this.setPreferredSize(new Dimension(500,500));
        this.colour = colour;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }


    public void paint(Graphics g) {
        Graphics2D gr = (Graphics2D) g;

        //gr.setColor(new Color(125, 167, 116));

        for(int x = this.x; x< this.x + this.width; x+=1)
            for(int y = this.y; y< this.y + this.height; y+=1){
                gr.fillRect(x, y, 10, 10);
                gr.setColor(new Color(this.colour[0], this.colour[1], this.colour[2]));
            }

    }
}
