import javax.swing.*;
import java.awt.*;

public class Client {

	private static Client instance = null;

	//For updating the message board
    Grid grid = null;
    MessagePanel messagePanel = new MessagePanel(grid);
    String host;
    int port;

    PinPanel pinPanel;
    PostPanel postPanel;
    GetPanel getPanel;

    JFrame frame;

    final Connection connection = new Connection();

    public static void main(String argv[]) throws Exception {
        getInstance().initUI();

    }

    private Client() {
    	// Private constructor to prevent external instantiation (ensure singleton instance)
    }

    private void initUI() {
    	String st = "Welcome To The CP372 Message Board!";

        //Set up Main Frame
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());

        ConnectPanel connectPanel = new ConnectPanel(st);
        			 postPanel	  = new PostPanel();
        			 getPanel	  = new GetPanel();
        			 pinPanel	  = new PinPanel();
        ClearPanel	 clearPanel	  = new ClearPanel();


        JTabbedPane controls = new JTabbedPane(JTabbedPane.LEFT);

        controls.add("Connect",connectPanel);
        controls.add("Post",postPanel);
        controls.add("Get",getPanel);
        controls.add("Pin/Unpin",pinPanel);
        controls.add("Clear/Shake",clearPanel);

        //Adding Components to the frame.
        frame.add(controls,BorderLayout.WEST);
        frame.add(messagePanel,BorderLayout.EAST);

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
