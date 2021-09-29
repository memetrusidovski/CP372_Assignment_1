
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	
	private static Client instance = null;
	
    Grid grid;

    public static void main(String argv[]) throws Exception {
        getInstance();

    }
    
    private Client() {
    	String st = "Google";

        //Set up Main Frame
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        JPanel connectPanel = new ConnectPanel(st);

        JTabbedPane controls = new JTabbedPane(JTabbedPane.LEFT);
        
        controls.add("Connect",connectPanel);
        controls.add("Post",new JPanel());
        controls.add("Get",new JPanel());
        controls.add("Pin/Unpin",new JPanel());
        controls.add("Clear/Shake",new JPanel());
        
        //Adding Components to the frame.
        frame.add(controls,BorderLayout.WEST);

        frame.setVisible(true);

        System.out.println(((ConnectPanel) connectPanel).getS());
    }
    
    public static Client getInstance() {
    	if(instance == null) {
    		instance = new Client();
    	}
    	return instance;
    }
    
}
