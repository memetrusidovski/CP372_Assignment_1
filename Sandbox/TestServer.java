import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TestServer {
    private String colors;
    private Message message;
  

    public static void main(String argv[]) throws Exception {
        Message server = new Message("Hello Message");
        ServerSocket serve = new ServerSocket(5555);

        while(true){
        Socket connection = serve.accept();
        
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());


        outputStream.writeObject(server);
        }

    }
}
