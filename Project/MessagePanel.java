
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MessagePanel extends JPanel {

	private static final long serialVersionUID = 3846802518284566285L;

    public int[] colour = { 0, 0, 0 };//The Font Colour
    int x = 50;
    int y = 50;
    int width = 50;
    int height = 50;
    String message = "Hello welcome message";
    Grid grid;

    Map<String, Color> colorMap = Map.ofEntries(Map.entry("BLUE", Color.BLUE),
        Map.entry( "BLACK", Color.BLACK),
        Map.entry( "ORANGE", Color.ORANGE),
        Map.entry( "RED", Color.RED),
        Map.entry( "YELLOW", Color.YELLOW),
        Map.entry( "WHITE", Color.WHITE),
        Map.entry( "GREEN", Color.GREEN),
        Map.entry( "CYAN", Color.CYAN));

    MessagePanel(Grid grid){
        this.grid = grid;
        this.setPreferredSize(new Dimension(500,500));
    }

    void setMessage(){

    }

    public void paint(Graphics g) {
        Graphics2D gr = (Graphics2D) g;

        //gr.setColor(new Color(125, 167, 116));

        //Print Testing
        /*for(int x = this.x; x< this.x + this.width; x+=1)
            for(int y = this.y; y< this.y + this.height; y+=1){
                gr.setColor(Color.getColor("100"));
                gr.fillRect(x, y, 10, 10);
                gr.setColor(new Color(this.colour[0], this.colour[1], this.colour[2]));
                gr.drawString(this.grid != null ? this.grid.messageStack.get(0).message : this.message, this.x,this.y);
            }*/


        //Print All Messages
        if(this.grid != null)
        for(Message m: this.grid.messageStack){
            gr.setColor(colorMap.get(m.color));

            gr.fillRect(m.x * 10, m.y * 10, m.height * 10, m.width * 10);

            //Draw Message
            gr.setColor(new Color(this.colour[0], this.colour[1], this.colour[2]));
            gr.setFont(new Font("Comic Sans MS", Font.BOLD, m.width));
            gr.drawString(m.message, m.x * 10, (m.y * 10) + 10);
        }


    }
}

/* LIST OF COLORS

 private static final Map<String, Color> NAMED_COLORS = Map.ofEntries(
            Map.entry("aliceblue",            ALICEBLUE),
            Map.entry("antiquewhite",         ANTIQUEWHITE),
            Map.entry("aqua",                 AQUA),
            Map.entry("aquamarine",           AQUAMARINE),
            Map.entry("azure",                AZURE),
            Map.entry("beige",                BEIGE),
            Map.entry("bisque",               BISQUE),
            Map.entry("black",                BLACK),
            Map.entry("blanchedalmond",       BLANCHEDALMOND),
            Map.entry("blue",                 BLUE),
            Map.entry("blueviolet",           BLUEVIOLET),
            Map.entry("brown",                BROWN),
            Map.entry("burlywood",            BURLYWOOD),
            Map.entry("cadetblue",            CADETBLUE),
            Map.entry("chartreuse",           CHARTREUSE),
            Map.entry("chocolate",            CHOCOLATE),
            Map.entry("coral",                CORAL),
            Map.entry("cornflowerblue",       CORNFLOWERBLUE),
            Map.entry("cornsilk",             CORNSILK),
            Map.entry("crimson",              CRIMSON),
            Map.entry("cyan",                 CYAN),
            Map.entry("darkblue",             DARKBLUE),
            Map.entry("darkcyan",             DARKCYAN),
            Map.entry("darkgoldenrod",        DARKGOLDENROD),
            Map.entry("darkgray",             DARKGRAY),
            Map.entry("darkgreen",            DARKGREEN),
            Map.entry("darkgrey",             DARKGREY),
            Map.entry("darkkhaki",            DARKKHAKI),
            Map.entry("darkmagenta",          DARKMAGENTA),
            Map.entry("darkolivegreen",       DARKOLIVEGREEN),
            Map.entry("darkorange",           DARKORANGE),
            Map.entry("darkorchid",           DARKORCHID),
            Map.entry("darkred",              DARKRED),
            Map.entry("darksalmon",           DARKSALMON),
            Map.entry("darkseagreen",         DARKSEAGREEN),
            Map.entry("darkslateblue",        DARKSLATEBLUE),
            Map.entry("darkslategray",        DARKSLATEGRAY),
            Map.entry("darkslategrey",        DARKSLATEGREY),
            Map.entry("darkturquoise",        DARKTURQUOISE),
            Map.entry("darkviolet",           DARKVIOLET),
            Map.entry("deeppink",             DEEPPINK),
            Map.entry("deepskyblue",          DEEPSKYBLUE),
            Map.entry("dimgray",              DIMGRAY),
            Map.entry("dimgrey",              DIMGREY),
            Map.entry("dodgerblue",           DODGERBLUE),
            Map.entry("firebrick",            FIREBRICK),
            Map.entry("floralwhite",          FLORALWHITE),
            Map.entry("forestgreen",          FORESTGREEN),
            Map.entry("fuchsia",              FUCHSIA),
            Map.entry("gainsboro",            GAINSBORO),
            Map.entry("ghostwhite",           GHOSTWHITE),
            Map.entry("gold",                 GOLD),
            Map.entry("goldenrod",            GOLDENROD),
            Map.entry("gray",                 GRAY),
            Map.entry("green",                GREEN),
            Map.entry("greenyellow",          GREENYELLOW),
            Map.entry("grey",                 GREY),
            Map.entry("honeydew",             HONEYDEW),
            Map.entry("hotpink",              HOTPINK),
            Map.entry("indianred",            INDIANRED),
            Map.entry("indigo",               INDIGO),
            Map.entry("ivory",                IVORY),
            Map.entry("khaki",                KHAKI),
            Map.entry("lavender",             LAVENDER),
            Map.entry("lavenderblush",        LAVENDERBLUSH),
            Map.entry("lawngreen",            LAWNGREEN),
            Map.entry("lemonchiffon",         LEMONCHIFFON),
            Map.entry("lightblue",            LIGHTBLUE),
            Map.entry("lightcoral",           LIGHTCORAL),
            Map.entry("lightcyan",            LIGHTCYAN),
            Map.entry("lightgoldenrodyellow", LIGHTGOLDENRODYELLOW),
            Map.entry("lightgray",            LIGHTGRAY),
            Map.entry("lightgreen",           LIGHTGREEN),
            Map.entry("lightgrey",            LIGHTGREY),
            Map.entry("lightpink",            LIGHTPINK),
            Map.entry("lightsalmon",          LIGHTSALMON),
            Map.entry("lightseagreen",        LIGHTSEAGREEN),
            Map.entry("lightskyblue",         LIGHTSKYBLUE),
            Map.entry("lightslategray",       LIGHTSLATEGRAY),
            Map.entry("lightslategrey",       LIGHTSLATEGREY),
            Map.entry("lightsteelblue",       LIGHTSTEELBLUE),
            Map.entry("lightyellow",          LIGHTYELLOW),
            Map.entry("lime",                 LIME),
            Map.entry("limegreen",            LIMEGREEN),
            Map.entry("linen",                LINEN),
            Map.entry("magenta",              MAGENTA),
            Map.entry("maroon",               MAROON),
            Map.entry("mediumaquamarine",     MEDIUMAQUAMARINE),
            Map.entry("mediumblue",           MEDIUMBLUE),
            Map.entry("mediumorchid",         MEDIUMORCHID),
            Map.entry("mediumpurple",         MEDIUMPURPLE),
            Map.entry("mediumseagreen",       MEDIUMSEAGREEN),
            Map.entry("mediumslateblue",      MEDIUMSLATEBLUE),
            Map.entry("mediumspringgreen",    MEDIUMSPRINGGREEN),
            Map.entry("mediumturquoise",      MEDIUMTURQUOISE),
            Map.entry("mediumvioletred",      MEDIUMVIOLETRED),
            Map.entry("midnightblue",         MIDNIGHTBLUE),
            Map.entry("mintcream",            MINTCREAM),
            Map.entry("mistyrose",            MISTYROSE),
            Map.entry("moccasin",             MOCCASIN),
            Map.entry("navajowhite",          NAVAJOWHITE),
            Map.entry("navy",                 NAVY),
            Map.entry("oldlace",              OLDLACE),
            Map.entry("olive",                OLIVE),
            Map.entry("olivedrab",            OLIVEDRAB),
            Map.entry("orange",               ORANGE),
            Map.entry("orangered",            ORANGERED),
            Map.entry("orchid",               ORCHID),
            Map.entry("palegoldenrod",        PALEGOLDENROD),
            Map.entry("palegreen",            PALEGREEN),
            Map.entry("paleturquoise",        PALETURQUOISE),
            Map.entry("palevioletred",        PALEVIOLETRED),
            Map.entry("papayawhip",           PAPAYAWHIP),
            Map.entry("peachpuff",            PEACHPUFF),
            Map.entry("peru",                 PERU),
            Map.entry("pink",                 PINK),
            Map.entry("plum",                 PLUM),
            Map.entry("powderblue",           POWDERBLUE),
            Map.entry("purple",               PURPLE),
            Map.entry("red",                  RED),
            Map.entry("rosybrown",            ROSYBROWN),
            Map.entry("royalblue",            ROYALBLUE),
            Map.entry("saddlebrown",          SADDLEBROWN),
            Map.entry("salmon",               SALMON),
            Map.entry("sandybrown",           SANDYBROWN),
            Map.entry("seagreen",             SEAGREEN),
            Map.entry("seashell",             SEASHELL),
            Map.entry("sienna",               SIENNA),
            Map.entry("silver",               SILVER),
            Map.entry("skyblue",              SKYBLUE),
            Map.entry("slateblue",            SLATEBLUE),
            Map.entry("slategray",            SLATEGRAY),
            Map.entry("slategrey",            SLATEGREY),
            Map.entry("snow",                 SNOW),
            Map.entry("springgreen",          SPRINGGREEN),
            Map.entry("steelblue",            STEELBLUE),
            Map.entry("tan",                  TAN),
            Map.entry("teal",                 TEAL),
            Map.entry("thistle",              THISTLE),
            Map.entry("tomato",               TOMATO),
            Map.entry("transparent",          TRANSPARENT),
            Map.entry("turquoise",            TURQUOISE),
            Map.entry("violet",               VIOLET),
            Map.entry("wheat",                WHEAT),
            Map.entry("white",                WHITE),
            Map.entry("whitesmoke",           WHITESMOKE),
            Map.entry("yellow",               YELLOW),
            Map.entry("yellowgreen",          YELLOWGREEN));

 */
