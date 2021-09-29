import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String argv[]) throws Exception {
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("red");
        colors.add("blue");

        Database database = new Database(500,500,colors);

        //Create the server Socket
        ServerSocket serve = new ServerSocket(5555);

        //database.grid.grid.get(4).get(3).hasPin=true;


        while (true) {
            Socket connection = serve.accept();

            //ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
            //ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
            //Request r = (Request) inputStream.readObject();

            System.out.println("Someone Connected");

            //Spin off the request into a service thread
            Service service = new Service(database, connection);
            Thread thread = new Thread(service);
            thread.start();
        }

    }
}
