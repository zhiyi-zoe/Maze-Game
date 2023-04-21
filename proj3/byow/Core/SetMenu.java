package byow.Core;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class SetMenu {
    public int widthTotal;
    public int heightTotal;
    public boolean gameBegin;
    public SetMenu(int widthTotal, int heightTotal) {
        this.widthTotal = widthTotal;
        this.heightTotal = heightTotal;
        StdDraw.setCanvasSize(this.widthTotal * 16, this.heightTotal * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.widthTotal);
        StdDraw.setYscale(0, this.heightTotal);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }
    public void creatMenu() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontSmall = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(fontSmall);
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
    public void startMenu() {
        this.gameBegin = false;
        while (!gameBegin) {
            creatMenu();
            StdDraw.pause(1000);
        }
    }
    public static void main(String[] args) {
        SetMenu menu = new SetMenu(100, 50);
        menu.startMenu();
    }
}
