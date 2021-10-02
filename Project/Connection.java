import java.io.IOException;
import java.io.ObjectInputStream;
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
        Client.messagePanel.grid = x;
        Client.messagePanel.repaint();

        Client.getInstance().pinPanel.updateDimensions(x.width, x.height);

        return x;
	}

	public void disconnect() throws IOException {

		if(!socket.isClosed()) {
			socket.close();
		}

	}

}
