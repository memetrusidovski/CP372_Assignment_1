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

        ConnectPanel connectPanel = new ConnectPanel(st);
        PostPanel 	 postPanel	  = new PostPanel();
        GetPanel	 getPanel	  = new GetPanel();
        PinPanel	 pinPanel	  = new PinPanel();
        ClearPanel	 clearPanel	  = new ClearPanel();

        JTabbedPane controls = new JTabbedPane(JTabbedPane.LEFT);
        
        controls.add("Connect",connectPanel);
        controls.add("Post",postPanel);
        controls.add("Get",getPanel);
        controls.add("Pin/Unpin",pinPanel);
        controls.add("Clear/Shake",clearPanel);
        
        //Adding Components to the frame.
        frame.add(controls,BorderLayout.WEST);

        frame.setVisible(true);

        System.out.println(connectPanel.getS());
    }
    
    public static Client getInstance() {
    	if(instance == null) {
    		instance = new Client();
    	}
    	return instance;
    }
    
}
