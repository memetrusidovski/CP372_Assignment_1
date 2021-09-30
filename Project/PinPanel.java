import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
		
		JSpinner x = new JSpinner(xModel);
		JSpinner y = new JSpinner(yModel);
		
		JButton pinButton 	= new JButton("Pin");
		JButton unpinButton = new JButton("Unpin");
		
		add(x);
		add(y);
		add(pinButton);
		add(unpinButton);
		
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
