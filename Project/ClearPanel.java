import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ClearPanel extends JPanel {

	private static final long serialVersionUID = 5608630856207475319L;

	public ClearPanel() {
		this.initUI();
	}
	
	private void initUI() {
		
		JButton clearButton = new JButton("Clear");
		JButton shakeButton = new JButton("Shake");
		JButton dcButton	= new JButton("Disconnect");
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Request request = new Request(RequestCommand.CLEAR);
				try {
					Client.getInstance().connection.send(request);
				}
				catch (IllegalStateException e1) {
					JOptionPane.showMessageDialog(clearButton.getRootPane(), "We couldn't clear the board because we aren't connected to a server", "Not Connected", JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException | IOException e2) {
					JOptionPane.showMessageDialog(clearButton.getRootPane(), "We ran into a problem clearing the board", "An Error Occured", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
			}
			
		});
		
		shakeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Request request = new Request(RequestCommand.SHAKE);
				try {
					Client.getInstance().connection.send(request);
				}
				catch (IllegalStateException e1) {
				}
				catch (ClassNotFoundException | IOException e2) {
					e2.printStackTrace();
				}
			}
			
		});
		
		dcButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Client.getInstance().connection.disconnect();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
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
