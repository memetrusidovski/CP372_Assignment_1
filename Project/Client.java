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


	static Grid grid = null;
    static Board boardPanel = new Board(grid);

    public static void main(String argv[]) throws Exception {
        getInstance();

    }

    private Client() {
    	String st = "Google";

        //Set up Main Frame
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        ConnectPanel connectPanel = new ConnectPanel(st, grid);
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
        frame.add(boardPanel,BorderLayout.EAST);

        frame.setVisible(true);


    }

    public static Client getInstance() {
    	if(instance == null) {
    		instance = new Client();
    	}
    	return instance;
    }

    /*class Sync implements Runnable{
        Board boardPanel;
        //Grid grid;

        public Sync(Board boardPanel, Grid grid) {
            this.boardPanel = boardPanel;
            this.grid = grid;
        }

        @Override
        public void run() {
            while (true){
                boardPanel.repaint();
                boardPanel.grid = this.grid;
                System.out.println("this.grid.messageStack.get(0).message");
                if(this.grid.messageStack.size() > 0){
                    boardPanel.message = grid.messageStack.remove(0).message;
                }
            }
        }
    }*/
}
