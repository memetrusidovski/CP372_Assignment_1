import GUI.ConnectPanel;
import GUI.getGrid;
import objects.Grid;
import objects.GridCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    Grid grid;

    public static void main(String argv[]) throws Exception {
        String st = "Google";

        //Set up Main Frame
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);


        JPanel connectPanel = new ConnectPanel(st);


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, connectPanel);


        frame.setVisible(true);



        System.out.println(((ConnectPanel) connectPanel).getS());

    }



        void getGrid() throws Exception{
            Socket connection = new Socket("localhost", 5555);

            ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());


            //String x = (String) inputStream.readObject();
            //System.out.println(x);
            Grid x = (Grid) inputStream.readObject();
            x.printGrid();
            //System.out.println(x);
    }
}
