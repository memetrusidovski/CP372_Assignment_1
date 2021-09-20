import objects.Grid;
import objects.GridCell;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String argv[]) throws Exception {
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
