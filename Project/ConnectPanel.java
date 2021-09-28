
import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectPanel extends JPanel {

    String s;

    public ConnectPanel(String st){
        try {
            this.initUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.s = st;
    }

    private void initUI() throws Exception{
        JPanel panel = new JPanel(); // the panel is not visible in output

        JLabel label = new JLabel("IP Address");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JTextField port = new JTextField(4);
        JButton send = new JButton("Connect");
        JButton reset = new JButton("Reset");

        tf.setText("localhost");
        port.setText("5555");

        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(port);
        panel.add(send);
        panel.add(reset);

        send.addActionListener( (e)->{
            String x = null;
            try {
                x = this.getData();
            } catch (Exception exception) {
                exception.printStackTrace();
            }


            label.setText(this.s);
        });

        add(panel);

        panel.setVisible(true);
    }

    private String getData() throws Exception{
        Socket connection = new Socket("localhost", 5555);

        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

        Grid x = (Grid) inputStream.readObject();
        x.printGrid();

        return "Grid Received";
    }

    public String getS(){return this.s;}
}
