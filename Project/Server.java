import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import objects.*;

public class Server {
    public static void main(String argv[]) throws Exception {
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("red");
        colors.add("blue");

        Database x = new Database(5,5,colors);

        System.out.println(x.colors.toString());

        //Create the server Socket
        ServerSocket serve = new ServerSocket(5555);

        while (true) {
            Socket connection = serve.accept();

            ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

            outputStream.writeObject("Welcome to the Server");
            System.out.println("Someone Connected");
        }
        
    }
}
