import javax.swing.*;

public class PostPanel extends JPanel {

	private static final long serialVersionUID = -2494723166396755145L;
    Grid grid;

	public PostPanel() {
	    this.initUI();
	}

	private void initUI() {
        JLabel label 	= new JLabel("Message: ");
        JTextField text = new JTextField(10);

        JButton send = new JButton("Send");

        add(label);
        add(text);
        add(send);



        String[] petStrings = Client.grid == null ? new String[]{"No Connection"} : new String[]{"connection"};

        //if(Client.grid != null)
            System.out.println("BANANA");

        JComboBox petList = new JComboBox(petStrings);
        petList.setSelectedIndex(0);

        add(petList);

        petList.addActionListener((e -> {
            System.out.println(petList.getSelectedIndex());
        }));
	}

}
