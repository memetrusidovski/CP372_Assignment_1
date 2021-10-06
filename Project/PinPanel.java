import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.GridBagConstraints;

public class PinPanel extends JPanel {

	private static final long serialVersionUID = -1923820929443408552L;
	private SpinnerNumberModel xModel;
	private SpinnerNumberModel yModel;

	public PinPanel() {
		this.initUI();
	}

	private void initUI() {

		xModel = new SpinnerNumberModel(0, 0, 0, 1);
		yModel = new SpinnerNumberModel(0, 0, 0, 1);

		JPanel xPanel 	  = new JPanel();
		JLabel xLabel 	  = new JLabel("X:");
		JSpinner xSpinner = new JSpinner(xModel);
		xPanel.add(xLabel);
		xPanel.add(xSpinner);

		JPanel yPanel 	  = new JPanel();
		JLabel yLabel 	  = new JLabel("Y:");
		JSpinner ySpinner = new JSpinner(yModel);
		yPanel.add(yLabel);
		yPanel.add(ySpinner);

		JButton pinButton 	= new JButton("Pin");
		JButton unpinButton = new JButton("Unpin");

		pinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = xModel.getNumber().intValue();
				int y = yModel.getNumber().intValue();
				Request request = new Request(RequestCommand.PIN,x,y);
				try {
					Response response = Client.getInstance().connection.send(request);
					if(response.getX() == -1) {
						JOptionPane.showMessageDialog(unpinButton.getRootPane(), response.getErrorMessage());
					}
					else {
						JOptionPane.showMessageDialog(pinButton.getRootPane(), "Pin placed at "+response.getX()+","+response.getY());
					}
				}
				catch (IllegalStateException e1) {
					JOptionPane.showMessageDialog(pinButton.getRootPane(), "We couldn't place the pin because we aren't connected to a server", "Not Connected", JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException | IOException e2) {
					JOptionPane.showMessageDialog(pinButton.getRootPane(), "We ran into a problem putting a pin in "+x+","+y, "An Error Occured", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
			}

		});

		unpinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = xModel.getNumber().intValue();
				int y = yModel.getNumber().intValue();
				Request request = new Request(RequestCommand.UNPIN,x,y);
				try {
					Response response = Client.getInstance().connection.send(request);
					if(response.getX() == -1){
					    JOptionPane.showMessageDialog(unpinButton.getRootPane(), response.getErrorMessage());
                    }
					else {
						JOptionPane.showMessageDialog(unpinButton.getRootPane(), "Pin at "+response.getX()+","+response.getY()+" removed");
					}
				}
				catch (IllegalStateException e1) {
					JOptionPane.showMessageDialog(pinButton.getRootPane(), "We couldn't remove the pin because we aren't connected to a server", "Not Connected", JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException | IOException e2) {
					JOptionPane.showMessageDialog(pinButton.getRootPane(), "We ran into a problem removing the pin at "+x+","+y, "An Error Occured", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
			}

		});

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy++;

		add(xPanel,gbc);
		gbc.gridy++;
		add(yPanel,gbc);
		gbc.gridy++;
		add(pinButton,gbc);
		gbc.gridy++;
		add(unpinButton,gbc);

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
