import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PostPanel extends JPanel {

	private static final long serialVersionUID = -2494723166396755145L;
    Grid grid;
    JComboBox<String> colorList;
    private SpinnerNumberModel xModel;
	private SpinnerNumberModel yModel;
	private SpinnerNumberModel widthModel;
	private SpinnerNumberModel heightModel;

    public PostPanel() {
        System.out.println("APPLE");
	    this.initUI();
	}

	private void initUI() {
        setLayout(new GridLayout(20,1));

        JPanel messagePanel = new JPanel();
        JLabel label 	= new JLabel("Message: ");
        JTextField text = new JTextField(10);
        messagePanel.add(label);
        messagePanel.add(text);
        
        xModel = new SpinnerNumberModel(0, 0, 0, 1);
		yModel = new SpinnerNumberModel(0, 0, 0, 1);
		widthModel = new SpinnerNumberModel(0, 0, 0, 1);
		heightModel = new SpinnerNumberModel(0, 0, 0, 1);

		JPanel positionPanel = new JPanel();
		JLabel xLabel 	     = new JLabel("X:");
		JSpinner xSpinner    = new JSpinner(xModel);
		JLabel yLabel 	  = new JLabel("Y:");
		JSpinner ySpinner = new JSpinner(yModel);
		positionPanel.add(xLabel);
		positionPanel.add(xSpinner);
		positionPanel.add(yLabel);
		positionPanel.add(ySpinner);
		
		JPanel sizePanel  	   = new JPanel();
		JLabel widthLabel 	   = new JLabel("Width:");
		JSpinner widthSpinner  = new JSpinner(widthModel);
		JLabel heightLabel 	   = new JLabel("Height:");
		JSpinner heightSpinner = new JSpinner(heightModel);
		sizePanel.add(widthLabel);
		sizePanel.add(widthSpinner);
		sizePanel.add(heightLabel);
		sizePanel.add(heightSpinner);

        colorList = new JComboBox<String>();
        messagePanel.add(colorList);

        JPanel sendPanel = new JPanel();
        JButton send = new JButton("Send");
        send.addActionListener((e) -> {
            try {
                Message m =(Message) Client.getInstance().connection.send(new Request(RequestCommand.POST, text.getText() == "" ? "????" : text.getText()));
                System.out.println(m.message);


                Client.getInstance().messagePanel.grid.messageStack.add(m);
                Client.getInstance().frame.repaint();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        sendPanel.add(send);
        
        add(messagePanel);
        add(positionPanel);
        add(sizePanel);
        add(sendPanel);
        
	}

    public void paint() {
	    this.initUI();
    }

}
