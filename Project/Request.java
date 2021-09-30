
import java.io.Serializable;

public class Request implements Serializable {
    
	private static final long serialVersionUID = -6096595360876385808L;
	public static String[] requestType = {"CONNECTED", "POST", "GET",
                                                "CLEAR", "SHAKE", "PIN", "UNPIN", "ERROR"};
    int x;
    int y;
    String request;
    String color;

    Request(String request){
        this.request = request;
    }


}
