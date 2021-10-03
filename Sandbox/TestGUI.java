

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestGUI {
    public static void main(String args[]) throws Exception {

        Socket connection = new Socket("localhost", 5555);

        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

        String x = (String) inputStream.readObject();

        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);



        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("IP Address");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JTextField port = new JTextField(4);
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        tf.setText("localhost");
        port.setText("5555");
        panel.add(port);
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);


        JRadioButton button1 = new JRadioButton("Red");
        JRadioButton button2 = new JRadioButton("Green");
        button2.setMnemonic(KeyEvent.VK_G);
        JRadioButton button3 = new JRadioButton("Blue");



        JPanel p = new panel(2,200,300,300, new int[]{125, 167, 116});
        JLabel l = new JLabel(x);

        ButtonGroup colorButtonGroup = new ButtonGroup();
        colorButtonGroup.add(button1);
        colorButtonGroup.add(button2);
        colorButtonGroup.add(button3);
        button1.setSelected(true);

        p.add(new JLabel("Color:"));
        p.add(button1);
        p.add(button2);
        p.add(button3);


        reset.addActionListener((e) ->{
            frame.getContentPane().remove(p);
            frame.revalidate(); // in- and validate in one !!
            frame.repaint();

            label.setText("Hello");
        });



        l.setMinimumSize(new Dimension(20, 20));
        l.setBackground(Color.CYAN);
        l.setOpaque(true);
        p.add(l);
        frame.getContentPane().add(BorderLayout.EAST, p);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, panel);
        frame.setVisible(true);
    }


}
