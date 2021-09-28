
import java.io.Serializable;

public class Request implements Serializable {
    public static String[] requestType = {"CONNECTED", "POST", "GET",
                                                "CLEAR", "SHAKE", "PIN", "UNPIN", "ERROR"};
    int x;
    int y;
    String color;

}
