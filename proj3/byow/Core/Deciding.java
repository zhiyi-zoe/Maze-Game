package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import java.util.List;

public class Deciding {
    private boolean a;//判断seed是否输入完成
    private boolean b;//判断是否是退出指令
    private String num;
    private String already;
    private long seed;
    private TETile[][] worldFrameFin;
    private TERenderer space;
    private int WIDTH;
    private int HEIGHT;
    private int[] avatarPos;
    private boolean quit;
    private boolean isNew;
    private boolean first;
    private boolean notInclude;


    //private String hasString = "wWaAsSdDqQ:NnLl";
    public Deciding(int width, int height) {
        num = "";//seed
        already = "";//用来记录前面都输入了啥
        a = false;
        b = false;
        isNew = false;
        WIDTH = width;
        HEIGHT = height;
        quit = false;
        first = true;
        notInclude = false;
        worldFrameFin = new TETile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                worldFrameFin[i][j] = Tileset.NOTHING;
            }
        }
    }

    public void changeEnv(char input) {
        //判断seed输入结束，创建新世界
        if (!notInclude) {
            already += input;
        }
        if (a && (input == 's' || input == 'S')) {
            a = false;
            seed = Long.parseLong(num);
            BuildRooms rooms = new BuildRooms(seed, WIDTH, HEIGHT);
            TETile[][] worldFrame = rooms.getTile();
            List doorLocation = rooms.getOpen();
            SetHallways setHallways = new SetHallways(worldFrame, doorLocation);
            worldFrame = setHallways.getWorldAfterHallways();
            SetWall setWall = new SetWall(worldFrame);
            worldFrameFin = setWall.getWorld();
            SetAvatar newAvatar = new SetAvatar(worldFrameFin, doorLocation);
            avatarPos = newAvatar.buildAvatar();
            isNew = true;
        } else {
            isNew = false;
        }
        if (a) {
            num = num + input;
        }
        //new game
        if (input == 'n' || input == 'N') {
            a = true;
            space = new TERenderer();
            space.initialize(WIDTH + 2 * 2 * 2 * 2 + 2 * 2, HEIGHT + 2 * 2 * 2 * 2 + 2 * 2, 2 * 2 * 2 + 2, 2 * 2 * 2 + 2);

        }
        if (b && (input == 'Q' || input == 'q')) {
            String str = already.substring(0, already.length() - 2);
            try {
                Out out = new Out("output.txt");
                out.print(str);

            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            //Out out = new Out("output.txt");

            //out.print(str);
            quit = true;
        } else {
            b = false;
            quit = false;
        }
        //准备输入quit指令
        if (input == ':') {
            b = true;
            System.out.println("hh");
        }

        if (input == 'w' || input == 'W') {
            MoveControl mControl = new MoveControl(worldFrameFin, avatarPos);
            avatarPos = mControl.oneMovement('W');
        }
        if (!a && (input == 's' || input == 'S')) {
            MoveControl mControl = new MoveControl(worldFrameFin, avatarPos);
            avatarPos = mControl.oneMovement('S');
        }
        if (input == 'A' || input == 'a') {
            MoveControl mControl = new MoveControl(worldFrameFin, avatarPos);
            avatarPos = mControl.oneMovement('A');
        }
        if (input == 'D' || input == 'd') {
            MoveControl mControl = new MoveControl(worldFrameFin, avatarPos);
            avatarPos = mControl.oneMovement('D');
        }
        if (first && (input == 'r' || input == 'R')) {
            In in = new In("output.txt");
            String savedLine = in.readLine();
            already += savedLine;
            already = already.substring(1);
            first = false;
        }
        if (first && (input == 'L' || input == 'l')) {
            In in = new In("output.txt");
            String savedLine = in.readLine();
            already += savedLine;
            already = already.substring(1);
            first = false;
        }

        notInclude = false;
    }

    public TERenderer showTE() {
        return space;
    }

    public TETile[][] showTile() {
        return worldFrameFin;
    }

    public long showSeed() {
        return seed;
    }

    public String showString() {
        return already;
    }

    public boolean isQuit() {
        return quit;
    }
    public boolean isNew() {
        return isNew;
    }
    public int[] showAva() {
        return avatarPos;
    }
    public String getAlready() {
        return already;
    }
    public void Do() {
        first = false;
    }
    public void Include() {
        notInclude = true;
    }

}
