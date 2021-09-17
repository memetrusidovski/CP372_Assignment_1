import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String argv[]) throws Exception {
        ServerSocket serve = new ServerSocket(5000);
        Socket s = serve.accept();

        System.out.print("Hello client");
    }
}
