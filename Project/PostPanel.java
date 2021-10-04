import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PostPanel extends JPanel {

	private static final long serialVersionUID = -2494723166396755145L;
    Grid grid;
    JComboBox<String> colorList;

    public PostPanel() {
        System.out.println("APPLE");
	    this.initUI();
	}

	private void initUI() {
        GridLayout experimentLayout = new GridLayout(0,2);

        JLabel label 	= new JLabel("Message: ");
        JTextField text = new JTextField(10);

        JButton send = new JButton("Send");

        add(label);
        add(text);
        add(send);



        colorList = new JComboBox<String>();
        //petList.setSelectedIndex(0);

        add(colorList);

        colorList.addActionListener((e -> {
            System.out.println(colorList.getSelectedIndex());
        }));

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
	}

    public void paint() {
	    this.initUI();
    }

}
