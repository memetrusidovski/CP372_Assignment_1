import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {
    static JFrame frame;
    static JButton b;

    public static void main(String argv[]) throws Exception {
        // Get the port number from the command line.
        // int port = 5555;
        frame = new JFrame("Program");
        b = new JButton("button1");

        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();

        panel.add(b);

        frame.add(panel);

        

    }
}
