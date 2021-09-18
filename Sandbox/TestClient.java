import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient {
    public static void main(String argv[]) throws Exception {
        Socket connection = new Socket("localhost", 5555);
        
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

        Message x = (Message)inputStream.readObject();
        System.out.println(x.message);

    }
    
}
