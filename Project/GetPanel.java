import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GetPanel extends JPanel {

	private static final long serialVersionUID = -2267852545859607148L;
	private CardLayout resultsLayout;
	private JPanel resultsPanel;
	private CarouselPanel resultsCarousel;
	private SpinnerNumberModel xModel;
	private SpinnerNumberModel yModel;
	JComboBox<String> colorList;

	public GetPanel() {
		this.initUI();
	}
	
	private void initUI() {
		
		setLayout(new GridLayout(2,1));
		
		resultsLayout = new CardLayout();
		resultsPanel = new JPanel(resultsLayout);
		resultsPanel.add(new JPanel());
		resultsCarousel = new CarouselPanel();
		resultsPanel.add(resultsCarousel);
		xModel = new SpinnerNumberModel(0, 0, 0, 1);
		yModel = new SpinnerNumberModel(0, 0, 0, 1);
		
		JPanel controls = new JPanel(new GridLayout(20,2));
		controls.add(new JLabel("Refers to:"));
		JTextField keyword = new JTextField();
		controls.add(keyword);
		colorList = new JComboBox<String>();
		controls.add(new JLabel("Colour:"));
		controls.add(colorList);
		controls.add(new JLabel("X:"));
		JCheckBox anyX = new JCheckBox("Any");
		controls.add(anyX);
		JSpinner xSpinner = new JSpinner(xModel);
		controls.add(xSpinner);
		controls.add(new JLabel("Y:"));
		JCheckBox anyY = new JCheckBox("Any");
		controls.add(anyY);
		JSpinner ySpinner = new JSpinner(yModel);
		controls.add(ySpinner);
		
		xSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				anyX.setSelected(false);
				anyY.setSelected(false);
			}
			
		});
		
		ySpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				anyX.setSelected(false);
				anyY.setSelected(false);
			}
			
		});
		
		JButton send = new JButton("Submit");
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = anyX.isSelected()?-1:xModel.getNumber().intValue();
				int y = anyY.isSelected()?-1:yModel.getNumber().intValue();
				String color = ((String)colorList.getSelectedItem()) == "Any Colour"?"":((String)colorList.getSelectedItem()).toUpperCase();
				get(keyword.getText(),color,x,y);
			}
			
		});
		controls.add(send);
		
		JButton getPins = new JButton("Get Pins");
		getPins.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Request request = new Request(RequestCommand.GET,"PINS");
				try {
					Object response = Client.getInstance().connection.send(request);
				}
				catch (IllegalStateException e1) {
					JOptionPane.showMessageDialog(getPins.getRootPane(), "We couldn't get the pins because we aren't connected to a server", "Not Connected", JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException | IOException e2) {
					JOptionPane.showMessageDialog(getPins.getRootPane(), "We ran into a problem getting the pins", "An Error Occured", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
			}
			
		});
		controls.add(getPins);
		
		add(controls);
		add(resultsPanel);
	}
	
	private void get(String refersTo, String color, int x, int y) {
		Request request = new Request(RequestCommand.GET,x,y,color,refersTo);
		try {
			Object response = Client.getInstance().connection.send(request);
		}
		catch (IllegalStateException e1) {
			JOptionPane.showMessageDialog(this, "We couldn't get the messages because we aren't connected to a server", "Not Connected", JOptionPane.ERROR_MESSAGE);
		}
		catch (ClassNotFoundException | IOException e2) {
			JOptionPane.showMessageDialog(this, "We ran into a problem getting the messages", "An Error Occured", JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}
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