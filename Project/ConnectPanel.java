
import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectPanel extends JPanel {

	private static final long serialVersionUID = 5634329278457109224L;
	private String s;
	private JTextField address;
	private JTextField port;

    public ConnectPanel(String st){
    	this.s = st;
        this.initUI();
    }

    private void initUI(){

        JLabel label 	= new JLabel("IP Address");
        		address = new JTextField(10); // accepts up to 10 characters
        		port 	= new JTextField(4);
        JButton send = new JButton("Connect");
        JButton reset = new JButton("Reset");

        address.setText("localhost");
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
        	Client.getInstance().grid.printGrid();
        });
        
        add(label); // Components Added using Flow Layout
        add(address);
        add(port);
        add(send);
        add(reset);
    }

    private String getData() throws Exception{
    	String host = address.getText();
    	int port = Integer.parseInt(this.port.getText());
        Client.getInstance().connection.connect(host, port);

        return "Grid Received";
    }

    public String getS(){return this.s;}
}
