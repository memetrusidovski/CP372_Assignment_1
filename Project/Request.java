
import java.io.Serializable;

public class Request implements Serializable {
    
	private static final long serialVersionUID = -6096595360876385808L;
	private RequestCommand command;
    private int x;
    private int y;
    private String request;
    private String color;

    Request(RequestCommand command, String request){
    	this.command = command;
        this.request = request;
    }

    Request(RequestCommand command, int x, int y){
    	this.command = command;
    	this.x = x;
    	this.y = y;
    }

}
