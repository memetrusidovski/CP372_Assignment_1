import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient {
    public static void main(String argv[]) throws Exception {
        Socket connection = new Socket("localhost", 5555);

        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());

        outputStream.writeObject("testServer.messageStack.get(0)");

        MessageOLD x = (MessageOLD)inputStream.readObject();
        System.out.println(x.message);

    }

}
