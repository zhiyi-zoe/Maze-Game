package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdDraw;

import java.util.List;
import java.io.File;
import java.io.IOException;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private String hasString = "wWaAsSdD";
    TETile[][] finalWorldFrame;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {

    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, running both of these:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        SetMenu menu = new SetMenu(WIDTH + 20, HEIGHT + 20);
        menu.creatMenu();
        Deciding set = new Deciding(WIDTH, HEIGHT);

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'l' || input.charAt(i) == 'L') {
                File file = new File("output.txt");
                if (!file.exists() || file.length() == 0) {
                    System.exit(0);
                    break;
                }
                set.changeEnv(input.charAt(i));
                String smallStr = set.showString();
                set = new Deciding(WIDTH, HEIGHT);
                for (int j = 0; j < smallStr.length(); j++) {
                    char thisChar= smallStr.charAt(j);
                    set.changeEnv(thisChar);

                }
                ter = set.showTE();
                finalWorldFrame = set.showTile();
                ter.renderFrame(finalWorldFrame);
                StdDraw.pause(1000);
                //以上为恢复上次保存的东西
            }
            set.changeEnv(input.charAt(i));
            if (input.charAt(i) == 'q' || input.charAt(i) == 'Q') {
                if (set.isQuit()) {
                    System.exit(0);
                    break;
                }
            }
            //生成初始界面
            if (input.charAt(i) == 's' || input.charAt(i) == 'S') {
                ter = set.showTE();
                finalWorldFrame = set.showTile();
                ter.renderFrame(finalWorldFrame);
                StdDraw.pause(1000);
            }
            //四个移动，每次pause1秒
            char m = input.charAt(i);
            if (hasString.indexOf(m) != -1) {
                finalWorldFrame = set.showTile();
                ter.renderFrame(finalWorldFrame);
                StdDraw.pause(1000);
            }
        }
        return set.showTile();
    }
}
