
import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectPanel extends JPanel {

    String s;
    Grid grid;

    public ConnectPanel(String st, Grid gird){
        this.grid = grid;
    	this.s = st;
        this.initUI();
    }

    private void initUI(){

        JLabel label = new JLabel("IP Address");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JTextField port = new JTextField(4);
        JButton send = new JButton("Connect");
        JButton reset = new JButton("Reset");

        tf.setText("localhost");
        port.setText("5555");

        send.addActionListener( (e)->{
            String x = null;
            try {
                x = this.getData();
            } catch (Exception exception) {
                exception.printStackTrace();
            }


            label.setText(this.s);
        });

        // For testing
        reset.addActionListener( (e)->{
        	//Client.getInstance().grid.printGrid();
            Client.boardPanel.repaint();
            System.out.println(this.grid.messageStack.get(0).message);
        });

        add(label); // Components Added using Flow Layout
        add(tf);
        add(port);
        add(send);
        add(reset);

    }

    private String getData() throws Exception{
        Socket connection = new Socket("localhost", 5555);

        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

        Grid x = (Grid) inputStream.readObject();
        x.printGrid();
        System.out.println(x.messageStack.get(0).message);
        this.grid = x;
        Client.boardPanel.grid = x;

        return "Grid Received";
    }

    public String getS(){return this.s;}
}
