import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Connection {

	private Socket socket = null;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	public Grid connect(String host, int port) throws IOException, ClassNotFoundException {

		if(socket != null && !socket.isClosed()) {
			socket.close();
		}
		socket = new Socket(host,port);
		Client.getInstance().host = host;
		Client.getInstance().port = port;

		//Send the request for Grid
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(new Request(RequestCommand.CONNECTED));

		inputStream = new ObjectInputStream(socket.getInputStream());

        Grid x = (Grid) inputStream.readObject();
        Client.getInstance().grid = x;
        Client.getInstance().messagePanel.grid = x;
        Client.getInstance().messagePanel.repaint();


        this.addColorsToPost(x.colors);


        Client.getInstance().pinPanel.updateDimensions(x.width, x.height);

        return x;
	}

	public void addColorsToPost(ArrayList<String> lst){
	    for(String s: lst) {
            Client.getInstance().postPanel.colorList.addItem(s);
	    }
        Client.getInstance().postPanel.revalidate();
        Client.getInstance().postPanel.repaint();
    }

	public void disconnect() throws IOException {

		if(socket != null && !socket.isClosed()) {
			socket.close();
		}

	}

	public Object send(Request request) throws IllegalStateException, IOException, ClassNotFoundException {

        Object obj;
		if(socket == null || socket.isClosed()) {
			throw new IllegalStateException("Not connected");
		}
        outputStream.writeObject(request);

        obj = inputStream.readObject();

		return obj;

	}

}
