
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Service implements Runnable {
    Database database;
    Socket connection;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public Service(Database database, Socket connection) throws Exception{
        this.database = database;
        this.connection = connection;
        inputStream = new ObjectInputStream(connection.getInputStream());
        outputStream = new ObjectOutputStream(connection.getOutputStream());
    }

    //Start a thread to complete the service
    public void run() {
        try {
            processRequest();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Do the request(Business Logic)
    private void processRequest() throws Exception {

        try {
		    while(!connection.isClosed()) {
		    	System.out.println("Waiting for request");
			    Request x = (Request) inputStream.readObject();
			    System.out.println("Recieved request for "+x.getCommand().name());
			    switch(x.getCommand()) {
				case CLEAR:
					outputStream.writeObject(new Serializable() {
						private static final long serialVersionUID = 8933877268367440958L;
					}); //TODO Replace with actual response Object
					break;
				case CONNECTED:
					this.database.grid.setMessage(new Message("message", 2, 2,2,2, "RED"));
				    this.database.grid.setMessage(new Message("other message", 5, 5,10,10, "CYAN"));
				
				    //this.database.grid.setMessage(new Message("BIG message", 30, 30,100,100));
				
				
				    this.newClientConnected();
				
				    //sample messages
				    //this.newMessage("5;5;10;10!$This is a sample message");
				    //this.newMessage("1;2;2;2!$Hello Person");
				    //sendAllMessages();
				    //this.database.grid.printGrid();
					break;
				case GET:
					outputStream.writeObject(new Serializable() {
						private static final long serialVersionUID = 8933877268367440958L;
					}); //TODO Replace with actual response Object
					break;
				case PIN:
					outputStream.writeObject(new Serializable() {
						private static final long serialVersionUID = 8933877268367440958L;
					}); //TODO Replace with actual response Object
					break;
				case POST:
					Message m = new Message(x.getMessage(), x.getX(), x.getY(),x.getWidth(),x.getHeight(), x.getColor().toUpperCase());
					System.out.println(m.getMessage());
			        this.database.grid.setMessage(m);
			        outputStream.writeObject(m);
					break;
				case SHAKE:
					this.shake();
					outputStream.writeObject(this.database.grid);
					break;
				case UNPIN:
					outputStream.writeObject(new Serializable() {
						private static final long serialVersionUID = 8933877268367440958L;
					}); //TODO Replace with actual response Object
					break;
				default:
					System.out.println("STUCK>>>");
					outputStream.writeObject(new Serializable() {
						private static final long serialVersionUID = 8933877268367440958L;
					}); //TODO Replace with actual response Object
					break;
			    }
		    }
        }
        catch (IOException e) {
        }
        finally {
        	System.out.println("Someone disconnected");
        }
        
    }

    //When a client first connects give them the data needed to create their board
    public void newClientConnected() throws Exception{

        //Get the Grid details
        String x = String.valueOf(this.database.boardWidth);
        String y = String.valueOf(this.database.boardHeight);
        String colors = "";
        for(String s: this.database.colors){
            colors+= " " + s;
        }

        //outputStream.writeObject("This is the board details " + x + ":" + y + ";" + colors);
        outputStream.writeObject(this.database.grid);
        //outputStream.writeObject(this.database.messageStack);

    }

    public void addPin(int x, int y){
        this.database.grid.pinCount++;
        this.database.grid.setPin(x,y);
        this.database.grid.getCell(x,y).messagePointers.forEach((e)-> {
            e.status = true;
            e.pinCount++;
        });
    }

    //OLD

    public void sendAllMessages() throws Exception{

        String send = "[";
        for (Message s : this.database.messageStack) {
            send += "{" + s.getMessage() + "},";
            //outputStream.writeObject( s );
        }

        outputStream.writeObject("This is all the messages: " + send + "]");
    }



    public void shake() throws IndexOutOfBoundsException {
        int c = 0;

        List<Message> messages = new ArrayList<Message>(this.database.grid.messageStack);

        for (Message m: messages){
        	System.out.println(m.pinCount);
            if (m.pinCount ==  0){
            	System.out.println("Removing: "+m.getMessage());
                Message removeMessage = this.database.grid.messageStack.remove(c);
            	System.out.println(removeMessage.getMessage());
                this.database.grid.removeMessage(removeMessage);
                c--;
            }
            c++;
        }
    }

    private void sendGrid() throws Exception{

        //outputStream.writeObject("This is the board details " + x + ":" + y + ";" + colors);
        outputStream.writeObject(this.database.grid);
    }

    public void clear(){

    }


}
