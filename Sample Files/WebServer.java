import java.net.* ;
import java.util.* ;

public final class WebServer {
	public ArrayList<String> colors = new ArrayList<String>();
	public ArrayList<MessageOLD> messageStack = new ArrayList<MessageOLD>();

    public static void main(String argv[]) throws Exception {
	// Get the port number from the command line.
	int port = 5555;

	WebServer server = new WebServer();
 	//int port = new Integer(argv[0]).intValue();

	// Establish the listen socket.
	ServerSocket socket = new ServerSocket(port);

	// Process HTTP service requests in an infinite loop.
	while (true) {
	    // Listen for a TCP connection request.
	    Socket connection = socket.accept();

	    // Construct an object to process the HTTP request message.
	    //HttpRequest request = new HttpRequest(connection);

	    // Create a new thread to process the request.
	    //Thread thread = new Thread(request);

	    // Start the thread.
	    //thread.start();
	}
    }
}

