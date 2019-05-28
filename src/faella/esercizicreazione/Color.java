package faella.esercizicreazione;

import java.util.List;
import java.util.Arrays;

public class Color {

    private int r, g, b;

    public static final Color RED = new Color(255,0,0);
    public static final Color GREEN = new Color(0,255,0);
    public static final Color BLUE = new Color(0,0,255);
    private static List<Color> predefiniti = Arrays.asList(RED, GREEN, BLUE);

    private Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Color make(int r, int g, int b) {
        Color ret;

        ret = getPredefinito(r,g,b);
        if (ret == null) {
            ret = new Color(r,g,b);
        }
        return ret;
    }

    private static Color getPredefinito(int r, int g, int b) {

        for (Color c : predefiniti) {
            if (c.r == r && c.g == g && c.b ==b) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (this == RED) {
            return "red";
        }
        if (this == GREEN) {
            return "green";
        }
        if (this == BLUE) {
            return "blue";
        }
        return "(" + r + ", " + g + ", " + b + ")";
    }

    public static void main(String[] args) {

        Color rosso = Color.RED;
        Color giallo = Color.make(255, 255, 0);
        Color verde = Color.make(0, 255,0);
        System.out.println(rosso) ;
        System.out.println( giallo ) ;
        System.out.println(verde);
        System.out.println(verde == Color.GREEN);
    }

}
