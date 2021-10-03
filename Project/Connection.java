import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Connection {

	private Socket socket = null;

	public Grid connect(String host, int port) throws IOException, ClassNotFoundException {

		if(socket != null && !socket.isClosed()) {
			socket.close();
		}
		socket = new Socket(host,port);
		Client.host = host;
		Client.port = port;

		//Send the request for Grid
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(new Request(RequestCommand.CONNECTED));

		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        Grid x = (Grid) inputStream.readObject();
        //Client.getInstance().grid = x;
        Client.grid = x;
        //x.printGrid();
        //Client.grid.printGrid();
        //Client.messagePanel.grid = x;
        //Client.messagePanel.repaint();
        Client.getInstance().messagePanel.grid = x;
        Client.getInstance().messagePanel.repaint();


        this.addColorsToPost(x.colors);


        Client.getInstance().pinPanel.updateDimensions(x.width, x.height);

        return x;
	}

	public void addColorsToPost(ArrayList<String> lst){
	    for(String s: lst)
            Client.getInstance().postPanel.colorList.addItem(s);

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
		else if(request.getCommand() == RequestCommand.POST) {
            Socket s = new Socket("localhost", 5555);

            ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
            outputStream.writeObject(request);

            ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
            obj = inputStream.readObject();
        }
		else{
		    obj = null;
        }

		return obj;

	}

}
