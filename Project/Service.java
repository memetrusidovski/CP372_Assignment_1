
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

    public Service(Socket connection) {
        this.connection = connection;
    }

    //Start a thread to complete the service
    public void run() {
        try {
            processRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Do the request(Business Logic)
    private void processRequest() throws IOException, ClassNotFoundException {
    	ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
	    Request x = (Request) inputStream.readObject();
	    System.out.println("Recieved request for "+x.getCommand().name());
	    switch(x.getCommand()) {
			case CLEAR:
				outputStream.writeObject(new Serializable() {
					private static final long serialVersionUID = 8933877268367440958L;
				}); //TODO Replace with actual response Object
				break;
			case CONNECTED:
				Server.getDatabase().grid.setMessage(new Message("message", 2, 2,2,2, "RED"));
			    Server.getDatabase().grid.setMessage(new Message("other message", 5, 5,10,10, "CYAN"));


			    // Send the grid to the client
		        outputStream.writeObject(new Response( Server.getDatabase().grid ) );

		        this.addPin(2,2);
				break;
			case GET:
			    if(x.getMessage().compareTo("PINS") == 0){

                    outputStream.writeObject(new Response(Server.getDatabase().grid.pinLocations.get(0).toString()) );
                }
			    else {
                    ArrayList<Message> messagesReturnList = this.searchMessages(x); //Send This Back to the user

                    outputStream.writeObject(new Response(messagesReturnList));
                }

				break;
			case PIN:
				outputStream.writeObject(new Serializable() {
					private static final long serialVersionUID = 8933877268367440958L;
				}); //TODO Replace with actual response Object
				break;
			case POST:
				Message m = new Message(x.getMessage(), x.getX(), x.getY(),x.getWidth(),x.getHeight(), x.getColor().toUpperCase());
				System.out.println(m.getMessage());
		        Server.getDatabase().grid.setMessage(m);
		        outputStream.writeObject(new Response(m));
				break;
			case SHAKE:
				this.shake();
				outputStream.writeObject(new Response(Server.getDatabase().grid) );
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
    	System.out.println("Someone disconnected");


    }

    public void addPin(int x, int y){
        Server.getDatabase().grid.pinCount++;
        Server.getDatabase().grid.setPin(x,y);
        Server.getDatabase().grid.getCell(x,y).messagePointers.forEach((e)-> {
            e.status = true;
            e.pinCount++;
        });
    }

    public void shake() throws IndexOutOfBoundsException {
        int c = 0;

        List<Message> messages = new ArrayList<Message>(Server.getDatabase().grid.messageStack);

        for (Message m: messages){
        	System.out.println(m.pinCount);
            if (m.pinCount ==  0){
            	System.out.println("Removing: "+m.getMessage());
                Message removeMessage = Server.getDatabase().grid.messageStack.remove(c);
            	System.out.println(removeMessage.getMessage());
                Server.getDatabase().grid.removeMessage(removeMessage);
                c--;
            }
            c++;
        }
    }

    public void clear(){

    }

    public ArrayList<Message> searchMessages(Request request){
        ArrayList<Message> lst = new ArrayList<Message>();

        System.out.println("<><><><><>" + request.getColor());
        if(request.getX() == -1 && request.getY() == -1 && request.getColor().isBlank() && request.getMessage().isBlank())
            lst = Server.getDatabase().grid.messageStack;
        else if(request.getX() == -1 && request.getY() == -1 && request.getColor().isBlank())
            lst = Server.getDatabase().searchMessagesByString(request.getMessage());
        else if(request.getX() == -1 && request.getY() == -1 && request.getMessage().isBlank()) {

            lst = Server.getDatabase().searchMessagesByColour(request.getColor());

        }
        else if(request.getColor().isBlank() && request.getMessage().isBlank())
            lst = Server.getDatabase().searchMessagesByLocation(request.getX(), request.getY());

        else {
            lst = Server.getDatabase().searchMessagesByMulti(request.getMessage(), request.getColor(), request.getX(), request.getY());
            System.out.println("++++++++");
        }

        return lst;

    }


}
