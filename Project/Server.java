import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String argv[]) throws Exception {
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("red");
        colors.add("blue");

        Database database = new Database(50,50,colors);

        //Create the server Socket
        ServerSocket server = new ServerSocket(5555);

        //database.grid.grid.get(4).get(3).hasPin=true;

        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        	
        }));

        while (true) {
            Socket connection = server.accept();

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
