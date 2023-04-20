package byow.Core;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class SetMenu {
    public int widthTotal;
    public int heightTotal;
    public SetMenu(int widthTotal, int heightTotal) {
        this.widthTotal = widthTotal;
        this.heightTotal = heightTotal;
    }
    public void creatMenu() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontSmall = new Font("Monaco", Font.BOLD, 10);
        StdDraw.setFont(fontSmall);
        int textGap = heightTotal / 2 / 2;
        int centralWidth = widthTotal / 2;
        int centralHeight = heightTotal / 2;
        StdDraw.text(centralWidth, centralHeight + textGap, "Welcome to BYOW");
        StdDraw.text(centralWidth, centralHeight, "New Game (N)");
        StdDraw.text(centralWidth, centralHeight - textGap / 2 / 2, "Load Game (L)");
        StdDraw.text(centralWidth, centralHeight - textGap / 2, "Replay Game (R)");
        StdDraw.text(centralWidth, centralHeight - textGap / 2 / 2 - textGap / 2, "Quit (Q)");
    }
}
