import java.net.Socket;

public class TestClient {
    public static void main(String argv[]) throws Exception {
        Socket s = new Socket("localhost", 5000);
    }
    
}
