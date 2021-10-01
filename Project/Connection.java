import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Connection {

	private Socket socket = null;
	
	public void connect(String host, int port) throws IOException, ClassNotFoundException {
		
		if(socket != null && !socket.isClosed()) {
			socket.close();
		}
		socket = new Socket(host,port);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        Grid x = (Grid) inputStream.readObject();
        Client.getInstance().grid = x;
        x.printGrid();
        Client.getInstance().pinPanel.updateDimensions(x.width, x.height);
        
	}
	
	public void disconnect() throws IOException {
		
		if(!socket.isClosed()) {
			socket.close();
		}
		
	}
	
}
