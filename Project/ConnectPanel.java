
import javax.swing.*;

public class ConnectPanel extends JPanel {

	private static final long serialVersionUID = 5634329278457109224L;
	String s;
	private JTextField address;
	private JTextField port;
    JLabel label;
    Grid grid;

    public ConnectPanel(String st){
    	this.s = st;
        this.initUI();
    }

    private void initUI(){

                label 	= new JLabel("IP Address");
        		address = new JTextField(10); // accepts up to 10 characters
        		port 	= new JTextField(4);
        JButton send = new JButton("Connect");
        JButton reset = new JButton("Reset");

        address.setText("localhost");
        port.setText("5555");

        send.addActionListener( (e)->{
            try {
            	this.getData();
            } catch (Exception exception) {
            	JOptionPane.showMessageDialog(send.getRootPane(), "We couldn't connect to the server", "Connection Failed", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }

            label.setText(this.s);
        });

        reset.addActionListener( (e)->{
        	address.setText("localhost");
            port.setText("5555");
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
        Grid x = Client.getInstance().connection.connect(host, port);

        Client.getInstance().grid = x;

        return "Grid Received";
    }

    public String getS(){return this.s;}
}
