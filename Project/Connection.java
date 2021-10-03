import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {

	private Socket socket = null;

	public Grid connect(String host, int port) throws IOException, ClassNotFoundException {

		if(socket != null && !socket.isClosed()) {
			socket.close();
		}
		socket = new Socket(host,port);

		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        Grid x = (Grid) inputStream.readObject();
        //Client.getInstance().grid = x;
        //Client.grid = x;
        //x.printGrid();
        //Client.grid.printGrid();
        //Client.messagePanel.grid = x;
        //Client.messagePanel.repaint();
        Client.getInstance().messagePanel.grid = x;
        Client.getInstance().messagePanel.repaint();

        Client.getInstance().pinPanel.updateDimensions(x.width, x.height);

        return x;
	}

	public void disconnect() throws IOException {
		
		if(socket != null && !socket.isClosed()) {
			socket.close();
		}

	}
	
	public Object send(Request request) throws IllegalStateException, IOException, ClassNotFoundException {
		
		if(socket == null || socket.isClosed()) {
			throw new IllegalStateException("Not connected");
		}
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		
		outputStream.writeObject(request);
		
		Object obj = inputStream.readObject();
		return obj;
		
	}
	
}
