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
		widthModel = new SpinnerNumberModel(1,1,null,1);
		heightModel = new SpinnerNumberModel(1,1,null,1);

		JPanel positionPanel = new JPanel();
		JLabel xLabel 	     = new JLabel("X:");
		JSpinner xSpinner    = new JSpinner(xModel);
		JLabel yLabel 	     = new JLabel("Y:");
		JSpinner ySpinner    = new JSpinner(yModel);
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
            	String msg = text.getText() == "" ? "????" : text.getText();
            	String color = (String) colorList.getSelectedItem();
            	int x = xModel.getNumber().intValue();
            	int y = yModel.getNumber().intValue();
            	int width = widthModel.getNumber().intValue();
            	int height = heightModel.getNumber().intValue();
            	Request request = new Request(RequestCommand.POST, x, y, width, height, color, msg);
                Response response = (Response) Client.getInstance().connection.send(request);
            	Message m = response.getMessage();
                System.out.println(m.getMessage());


                Client.getInstance().messagePanel.grid.messageStack.add(m);
                Client.getInstance().frame.repaint();

            }
            catch (IllegalStateException e1) {
				JOptionPane.showMessageDialog(send.getRootPane(), "We couldn't post the message because we aren't connected to a server", "Not Connected", JOptionPane.ERROR_MESSAGE);
			}
			catch (ClassNotFoundException | IOException e2) {
				JOptionPane.showMessageDialog(send.getRootPane(), "We ran into a problem posting the message", "An Error Occured", JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
        });
        sendPanel.add(send);

        add(messagePanel);
        add(positionPanel);
        add(sizePanel);
        add(sendPanel);

	}

	public void updateDimensions(int x, int y) throws IllegalArgumentException {
		if(x < 0 || y < 0) {
			throw new IllegalArgumentException("Dimensions of the grid can't be negative");
		}
		xModel.setMaximum(x);
		yModel.setMaximum(y);
	}

	public void resetDimensions() {
		xModel.setMaximum(0);
		yModel.setMaximum(0);
	}

}
