package objects;

public class Service implements Runnable {
    Database database;
    String command;

    public Service(Database database){
        this.database = database;
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
        System.out.println("Hello Moto");
        this.database.grid.printGrid();
    }

    //Request Types
    public void postRequest(String command){
        
    }


}
