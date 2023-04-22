package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdDraw;

import java.io.File;

public class Replay {
    TERenderer ter = new TERenderer();
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final int OFFSET = 10;
    public static final int PAUSETIME = 500;
    private String hasString = "wWaAdD";
    private TETile[][] finalWorldFrame;
    private String input;
    private Deciding set;
    public Replay(String input1) {
        set = new Deciding(WIDTH, HEIGHT);
        input = input1;


    }
    public void play() {
        for (int i = 0; i < input.length(); i++) {
            set.changeEnv(input.charAt(i));
            if (input.charAt(i) == 'p' || input.charAt(i) == 'P') {
                finalWorldFrame = set.showTile();
                ter.renderFrame(finalWorldFrame);
                HeadsUpDisplay display = new HeadsUpDisplay(finalWorldFrame, set.showAva());
                display.normalMenu();
                StdDraw.textLeft(3 * OFFSET, OFFSET, "Already input: " + set.getAlready());
                display.changeAppearance();
                StdDraw.show();
                StdDraw.pause(PAUSETIME);
            }
            if (input.charAt(i) == 's' || input.charAt(i) == 'S') {
                if (set.isNew()) {
                    ter = set.showTE();
                    finalWorldFrame = set.showTile();
                    ter.renderFrame(finalWorldFrame);
                    HeadsUpDisplay display = new HeadsUpDisplay(finalWorldFrame, set.showAva());
                    display.normalMenu();
                    StdDraw.textLeft(3 * OFFSET, OFFSET, "Already input: " + set.getAlready());
                    StdDraw.show();
                    StdDraw.pause(PAUSETIME);
                } else {
                    finalWorldFrame = set.showTile();
                    ter.renderFrame(finalWorldFrame);
                    HeadsUpDisplay display = new HeadsUpDisplay(finalWorldFrame, set.showAva());
                    display.normalMenu();
                    StdDraw.textLeft(3 * OFFSET, OFFSET, "Already input: " + set.getAlready());
                    StdDraw.show();
                    StdDraw.pause(PAUSETIME);
                }
            }
            char m = input.charAt(i);
            if (hasString.indexOf(m) != -1) {
                finalWorldFrame = set.showTile();
                ter.renderFrame(finalWorldFrame);
                HeadsUpDisplay display = new HeadsUpDisplay(finalWorldFrame, set.showAva());
                display.normalMenu();
                StdDraw.textLeft(3 * OFFSET, OFFSET, "Already input: " + set.getAlready());
                StdDraw.show();
                StdDraw.pause(PAUSETIME);
            }
        }
    }
    public Deciding getDeciding() {
        return set;
    }

}
