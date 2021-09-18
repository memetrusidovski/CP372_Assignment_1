import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TestServer {
    public String colors = null;
    public static void main(String argv[]) throws Exception {
        ServerSocket serve = new ServerSocket(5000);
        Socket connection = serve.accept();

          
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());



    }
}
