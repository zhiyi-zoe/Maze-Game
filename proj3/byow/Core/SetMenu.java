package byow.Core;

import byow.TileEngine.TERenderer;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class SetMenu {
    private int widthTotal;
    private int heightTotal;
    private TERenderer space;
    public SetMenu(int widthTotal, int heightTotal) {
        this.widthTotal = widthTotal;
        this.heightTotal = heightTotal;
        this.space = new TERenderer();
        this.space.initialize(widthTotal, heightTotal);
    }
    public void creatMenu() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        int textGap = heightTotal / 2 / 2;
        int centralWidth = widthTotal / 2;
        int centralHeight = heightTotal / 2;
        StdDraw.text(centralWidth, centralHeight + textGap, "Welcome to BYOW");
        StdDraw.text(centralWidth, centralHeight, "New Game (N)");
        StdDraw.text(centralWidth, centralHeight - textGap / 2 / 2, "Load Game (L)");
        StdDraw.text(centralWidth, centralHeight - textGap / 2, "Replay Game (R)");
        StdDraw.text(centralWidth, centralHeight - textGap / 2 / 2 - textGap / 2, "Quit (Q)");
        StdDraw.show();
    }
}
