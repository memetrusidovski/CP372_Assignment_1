
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Service implements Runnable {
    Database database;
    Socket connection;
    Request request;

    public Service(Database database, Socket connection) throws Exception{
        this.database = database;
        this.connection = connection;
    }
    public Service(Database database, Socket connection, Request request) throws Exception{
        this.database = database;
        this.connection = connection;
        this.request = request;
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
        this.database.grid.setMessage(new Message("message", 2, 2,2,2));

        this.newClientConnected();

        //sample messages
        this.newMessage("5;5;10;10!$This is a sample message");
        //this.newMessage("1;2;2;2!$Hello Person");
        //sendAllMessages();
        //this.shake();
        //this.addPin(1,1);
        this.database.grid.printGrid();

    }

    //When a client first connects give them the data needed to create their board
    public void newClientConnected() throws Exception{
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());

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
    public void newMessage(String s){

        //PARSE THE MESSAGE DETAILS
        String[] parse = s.split("!\\$");
        String[] sizing = parse[0].split(";");


        if (sizing.length == 4) {
            Message message = new Message(parse[1], Integer.valueOf(sizing[0]),
                                    Integer.valueOf(sizing[1]), Integer.valueOf(sizing[2]),
                                        Integer.valueOf(sizing[3]));

            this.database.grid.setMessage(message);
            this.database.messageStack.add(message);
        }

    }

    public void sendAllMessages() throws Exception{
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());

        String send = "[";
        for (Message s : this.database.messageStack) {
            send += "{" + s.message + "},";
            //outputStream.writeObject( s );
        }

        outputStream.writeObject("This is all the messages: " + send + "]");
    }

    //Request Types
    public void postRequest(String command){

    }

    public void shake() throws Exception {
        int c = 0;
        for (Message m: this.database.messageStack){
            if (m.pinCount == 0){
                this.database.messageStack.remove(c);

            }
            c++;
        }
        this.sendGrid();
    }

    private void sendGrid() throws Exception{
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());

        //outputStream.writeObject("This is the board details " + x + ":" + y + ";" + colors);
        outputStream.writeObject(this.database.grid);
    }

    public void clear(){

    }


}
