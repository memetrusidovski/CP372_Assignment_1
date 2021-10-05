
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Service implements Runnable {
    Database database;
    Socket connection;

    public Service(Database database, Socket connection) throws Exception{
        this.database = database;
        this.connection = connection;
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
				this.database.grid.setMessage(new Message("message", 2, 2,2,2, "RED"));
			    this.database.grid.setMessage(new Message("other message", 5, 5,10,10, "CYAN"));


			    // Send the grid to the client
		        outputStream.writeObject(new Response( this.database.grid ) );
                //outputStream.close();
                //connection.close();

		        this.addPin(2,2);
				break;
			case GET:
			    if(x.getMessage().compareTo("PINS") == 0){

                    outputStream.writeObject(new Response(this.database.grid.pinLocations.get(0).toString()) );
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
		        this.database.grid.setMessage(m);
		        outputStream.writeObject(m);
				break;
			case SHAKE:
				this.shake();
				outputStream.writeObject(new Response(this.database.grid) );
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
        this.database.grid.pinCount++;
        this.database.grid.setPin(x,y);
        this.database.grid.getCell(x,y).messagePointers.forEach((e)-> {
            e.status = true;
            e.pinCount++;
        });
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

    public void clear(){

    }

    public ArrayList<Message> searchMessages(Request request){
        ArrayList<Message> lst = new ArrayList<Message>();

        System.out.println("<><><><><>" + request.getColor());
        if(request.getX() == -1 && request.getY() == -1 && request.getColor().isBlank() && request.getMessage().isBlank())
            lst = this.database.grid.messageStack;
        else if(request.getX() == -1 && request.getY() == -1 && request.getColor().isBlank())
            lst = this.database.searchMessagesByString(request.getMessage());
        else if(request.getX() == -1 && request.getY() == -1 && request.getMessage().isBlank()) {

            lst = this.database.searchMessagesByColour(request.getColor());

        }
        else if(request.getColor().isBlank() && request.getMessage().isBlank())
            lst = this.database.searchMessagesByLocation(request.getX(), request.getY());

        else {
            lst = this.database.searchMessagesByMulti(request.getMessage(), request.getColor(), request.getX(), request.getY());
            System.out.println("++++++++");
        }

        return lst;

    }


}
