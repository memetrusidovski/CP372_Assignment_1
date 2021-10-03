import javax.swing.*;
import java.awt.*;

public class panel extends JPanel {

    public int[] colour;
    int x;
    int y;
    int width;
    int height;

    panel(int x, int y, int width, int height, int[] colour){
        this.setPreferredSize(new Dimension(500,500));
        this.colour = colour;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }



    public void paint(Graphics g) {
        Graphics2D gr = (Graphics2D) g;




        for(int x = this.x; x< this.x + this.width; x+=1)
            for(int y = this.y; y< this.y + this.height; y+=1){
                gr.fillRect(x, y, 10, 10);
                gr.setColor(new Color(this.colour[0], this.colour[1], this.colour[2]));
            }

        gr.setColor(new Color(0, 0, 0));
        gr.setFont( new Font("Helvetica", Font.PLAIN, 12));
        gr.drawString("Hello this is a message", (this.x + this.width)/2, (this.y + this.height)/2 );
        //gr.drawString("Hello", 50, 50 );

    }
}
