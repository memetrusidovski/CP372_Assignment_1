import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private static Database database;

    public static void main(String argv[]) throws Exception {
        ArrayList<String> colors = new ArrayList<String>();

        /*for(int c = 3; c < argv.length; c++){
            colors.add(argv[c]);
        }

        System.out.println("Welcome to The Server, to Open a Client App Please Run: java Client");
        System.out.println( argv.length +  "Port: " +  argv[0] + ", Width: " + argv[1] +
                                    ", Height: " + argv[2] );
        database = new Database(argv[1],argv[2],colors);

        ServerSocket server = new ServerSocket(argv[0]);

                                    */

        //Create Testing Server
        colors.add("red");
        colors.add("blue");
        database = new Database(50,50,colors);

        //Create the server Socket
        ServerSocket server = new ServerSocket(5555);


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

            System.out.println("Someone Connected");

            //Spin off the request into a service thread
            Service service = new Service(connection);
            Thread thread = new Thread(service);
            thread.start();
        }

    }

    public static synchronized Database getDatabase() {
    	return database;
    }

}
