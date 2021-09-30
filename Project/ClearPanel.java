import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JButton;

public class ClearPanel extends JPanel {

	private static final long serialVersionUID = 5608630856207475319L;

	public ClearPanel() {
		this.initUI();
	}
	
	private void initUI() {
		
		JButton clearButton = new JButton("Clear");
		JButton shakeButton = new JButton("Shake");
		JButton dcButton	= new JButton("Disconnect");
		
		setLayout(new GridLayout(4,1));
		add(clearButton);
		add(shakeButton);
		add(dcButton);
		
	}
	
}
