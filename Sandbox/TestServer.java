import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String argv[]) throws Exception {
        ServerSocket serve = new ServerSocket(5000);
        
        while (true) {
            // Listen for a TCP connection request.
            Socket connection = serve.accept();

            // Construct an object to process the HTTP request message.
            HttpRequest request = new HttpRequest(connection);

            // Create a new thread to process the request.
            Thread thread = new Thread(request);

            // Start the thread.
            thread.start();
        }

    }
}
