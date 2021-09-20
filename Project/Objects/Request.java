package objects;

import java.io.Serializable;

public class Request implements Serializable {
    public static String[] requestType = {"CONNECTED", "POST", "GET",
                                                "CLEAR", "SHAKE", "PIN", "UNPIN"};
    int x;
    int y;
    String color;


}
