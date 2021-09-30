import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
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
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy++;
		
		add(clearButton,gbc);
		gbc.gridy++;
		add(shakeButton,gbc);
		gbc.gridy++;
		add(dcButton,gbc);
		
	}
	
}
