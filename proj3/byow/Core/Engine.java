package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.File;


public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final int OFFSET = 10;
    public static final int PAUSETIME = 500;
    private String hasString = "wWaAdD";
    TETile[][] finalWorldFrame;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        //初始页面呈现
        SetMenu menu = new SetMenu(WIDTH + 2 * OFFSET, HEIGHT + 2 * OFFSET);
        boolean gameBegin = false;
        boolean showMap = false;
        Deciding set1 = new Deciding(WIDTH, HEIGHT);
        boolean showSeed = false;
        boolean isReplay = true;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char a = StdDraw.nextKeyTyped();
                if (isReplay && (a == 'r' || a == 'R')) {
                    gameBegin = true;
                    File file = new File("output.txt");
                    if (!file.exists() || file.length() == 0) {
                        System.exit(0);
                    }
                    set1.changeEnv('l');//读个文件，改个already
                    String smallStr = set1.showString();
                    Replay play = new Replay(smallStr);
                    play.play();
                    set1 = new Deciding(WIDTH, HEIGHT);
                    for (int j = 0; j < smallStr.length(); j++) {
                        char thisChar = smallStr.charAt(j);
                        set1.changeEnv(thisChar);
                    }
                    showMap = true;
                    ter = set1.showTE();
                }
                isReplay = false;
                if (a == 'l' || a == 'L') {
                    gameBegin = true;
                    File file = new File("output.txt");
                    if (!file.exists() || file.length() == 0) {
                        System.exit(0);
                    }
                    set1.changeEnv(a);
                    String smallStr = set1.showString();
                    set1 = new Deciding(WIDTH, HEIGHT);
                    for (int j = 0; j < smallStr.length(); j++) {
                        char thisChar = smallStr.charAt(j);
                        set1.changeEnv(thisChar);
                    }
                    showMap = true;
                    ter = set1.showTE();
                }

                set1.changeEnv(a); //进行改变
                if (a == 'n' || a == 'N') {
                    showSeed = true;
                    gameBegin = true;
                }
                if (a == 'q' || a == 'Q') {
                    gameBegin = true;
                    if (set1.isQuit()) {
                        System.exit(0);
                    }
                }
                //生成初始界面
                if (a == 's' || a == 'S') {
                    showMap = true;
                    showSeed = false;
                    if (set1.isNew()) {
                        ter = set1.showTE();
                    }
                }
                if (a == 'p' || a == 'P') {
                    HeadsUpDisplay display1 = new HeadsUpDisplay(finalWorldFrame, set1.showAva());
                    display1.changeAppearance();
                }

            }
            if (!gameBegin) {
                menu.creatMenu();
            }
            if (showSeed) {
                StdDraw.clear(Color.BLACK);
                String into = set1.getAlready();
                into = into.substring(1);
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.text(WIDTH / 2 + OFFSET, HEIGHT / 2 + OFFSET, "Your Seed: " + into);
                StdDraw.show();
            }

            if (showMap) {
                finalWorldFrame = set1.showTile();
                ter.renderFrame(finalWorldFrame);
                HeadsUpDisplay display = new HeadsUpDisplay(finalWorldFrame, set1.showAva());
                display.mouseText();
                StdDraw.textLeft(3 * OFFSET, OFFSET, "Already input: " + set1.getAlready());
                display.normalMenu();
                StdDraw.show();
            }
        }
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
        SetMenu menu = new SetMenu(WIDTH + OFFSET * 2, HEIGHT + OFFSET * 2);
        menu.creatMenu();
        StdDraw.pause(PAUSETIME * 3);
        Deciding set = new Deciding(WIDTH, HEIGHT);



        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'l' || input.charAt(i) == 'L') {
                File file = new File("output.txt");
                if (!file.exists() || file.length() == 0) {
                    System.exit(0);
                }
                set.changeEnv(input.charAt(i));
                String smallStr = set.showString();
                set = new Deciding(WIDTH, HEIGHT);
                for (int j = 0; j < smallStr.length(); j++) {
                    char thisChar = smallStr.charAt(j);
                    set.changeEnv(thisChar);
                }
                ter = set.showTE();
                finalWorldFrame = set.showTile();
                ter.renderFrame(finalWorldFrame);
                StdDraw.pause(PAUSETIME);
                continue;
            }
            set.changeEnv(input.charAt(i));
            if (input.charAt(i) == 'q' || input.charAt(i) == 'Q') {
                if (set.isQuit()) {
                    //System.exit(0);
                    break;
                }
            }
            if (input.charAt(i) == 's' || input.charAt(i) == 'S') {
                if (set.isNew()) {
                    ter = set.showTE();
                    finalWorldFrame = set.showTile();
                    ter.renderFrame(finalWorldFrame);
                    HeadsUpDisplay display = new HeadsUpDisplay(finalWorldFrame, set.showAva());
                    display.mouseText();
                    display.normalMenu();
                    StdDraw.show();
                    StdDraw.pause(PAUSETIME);
                } else {
                    finalWorldFrame = set.showTile();
                    ter.renderFrame(finalWorldFrame);
                    HeadsUpDisplay display = new HeadsUpDisplay(finalWorldFrame, set.showAva());
                    display.mouseText();
                    display.normalMenu();
                    StdDraw.show();
                    StdDraw.pause(PAUSETIME);
                }
            }
            char m = input.charAt(i);
            if (hasString.indexOf(m) != -1) {
                finalWorldFrame = set.showTile();
                ter.renderFrame(finalWorldFrame);
                HeadsUpDisplay display = new HeadsUpDisplay(finalWorldFrame, set.showAva());
                display.mouseText();
                display.normalMenu();
                StdDraw.show();
                StdDraw.pause(PAUSETIME);
            }
        }
        return set.showTile();
    }
}
