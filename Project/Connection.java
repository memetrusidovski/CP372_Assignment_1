import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Connection {

	private String host = null;
	private int port = -1;

	public Grid connect(String host, int port) throws IOException, ClassNotFoundException {

		Client.getInstance().host = host;
		Client.getInstance().port = port;
		this.host = host;
		this.port = port;

		//Send the request for Grid
        Grid x = (Grid) this.send(new Request(RequestCommand.CONNECTED));
        Client.getInstance().grid = x;
        Client.getInstance().messagePanel.grid = x;
        Client.getInstance().frame.repaint();


        this.addColorsToPost(x.colors);

        Client.getInstance().pinPanel.updateDimensions(x.width, x.height);
        Client.getInstance().postPanel.updateDimensions(x.width, x.height);
        Client.getInstance().getPanel.updateDimensions(x.width, x.height);

        return x;
	}

	public void addColorsToPost(ArrayList<String> lst){
		Client.getInstance().postPanel.colorList.removeAllItems();
		Client.getInstance().getPanel.colorList.removeAllItems();
		Client.getInstance().getPanel.colorList.addItem("Any Colour");
	    for(String s: lst) {
            Client.getInstance().postPanel.colorList.addItem(s);
            Client.getInstance().getPanel.colorList.addItem(s);
	    }
    }

	public void disconnect() {

		host = null;
		port = -1;

	}

	public Object send(Request request) throws IllegalStateException, IOException, ClassNotFoundException {

        Object obj;
		if(host == null || port <= 0) {
			throw new IllegalStateException("Not connected");
		}
		Socket socket = new Socket(host,port);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		
		outputStream.writeObject(request);
        obj = inputStream.readObject();
        socket.close();

		return obj;

	}

}
