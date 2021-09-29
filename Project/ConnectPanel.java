
import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectPanel extends JPanel {

    String s;

    public ConnectPanel(String st){
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

<<<<<<< HEAD
        // For testing
        reset.addActionListener( (e)->{
        	Client.getInstance().grid.printGrid();
        });
        
        add(label); // Components Added using Flow Layout
        add(tf);
        add(port);
        add(send);
        add(reset);
        
=======
        add(panel);

        panel.setVisible(true);
>>>>>>> branch 'main' of https://github.com/memetrusidovski/CP372_Assignment_1.git
    }

    private String getData() throws Exception{
        Socket connection = new Socket("localhost", 5555);

        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

        Grid x = (Grid) inputStream.readObject();
        x.printGrid();

        return "Grid Received";
    }

    public String getS(){return this.s;}
}