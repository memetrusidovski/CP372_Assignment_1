import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TestServer {
    public ArrayList<String> colors = new ArrayList<String>();
    public ArrayList<Message> messageStack = new ArrayList<Message>();
    public ArrayList<Message> pins = new ArrayList<Message>();
    public int boardWidth;
    public int boardHeight;

    public static void main(String argv[]) throws Exception {
        TestServer testServer = new TestServer();
        Message server = new Message("Hello Message");

        testServer.messageStack.add(server);

        ServerSocket serve = new ServerSocket(5555);

        while(true){
        Socket connection = serve.accept();
        
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

        outputStream.writeObject(testServer.messageStack.get(0));

        
        }

    }
}
