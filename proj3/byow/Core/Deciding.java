package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    //private String hasString = "wWaAsSdDqQ:NnLl";
    public Deciding(int width, int height) {
        num = "";//seed
        already = "";//用来记录前面都输入了啥
        a = false;
        b = false;
        WIDTH = width;
        HEIGHT = height;
    }
    public void changeEnv(char input) {
        //判断seed输入结束，创建新世界
        already += input;
        if (input == 's' || input == 'S') {
            a = false;
            seed = Long.parseLong(num);
            BuildRooms rooms = new BuildRooms(seed, WIDTH, HEIGHT);
            TETile[][]worldFrame = rooms.getTile();
            List doorLocation = rooms.getOpen();
            SetHallways setHallways = new SetHallways(worldFrame, doorLocation);
            worldFrame = setHallways.getWorldAfterHallways();
            SetWall setWall = new SetWall(worldFrame);
            worldFrameFin = setWall.getWorld();
        }
        if (a) {
            num = num + input;
        }
        //new game
        if (input == 'n' || input == 'N') {
            a = true;
            space = new TERenderer();
            space.initialize(WIDTH + 20, HEIGHT + 20, 10, 10);
            Out out = new Out("output.txt");
            out.print();
        }
        //准备输入quit指令
        if (input == ':') {
            b = true;
        }
        if (b && (input == 'Q' || input == 'q')) {
            Out out = new Out("output.txt");
            String str = already.substring(0,already.length() - 2);
            out.print(str);

        } else {
            b = false;
        }
        if (input == 'w' || input == 'W') {
            MoveControl MC = new MoveControl(worldFrameFin, avatarPos);
            avatarPos = MC.oneMovement('W');
        }
        if (input == 's' || input == 'S') {
            MoveControl MC = new MoveControl(worldFrameFin, avatarPos);
            avatarPos = MC.oneMovement('S');
        }
        if (input == 'A' || input == 'a') {
            MoveControl MC = new MoveControl(worldFrameFin, avatarPos);
            avatarPos = MC.oneMovement('A');
        }
        if (input == 'D' || input == 'd') {
            MoveControl MC = new MoveControl(worldFrameFin, avatarPos);
            avatarPos = MC.oneMovement('D');
        }
        if (input == 'L' || input == 'l') {
            In in = new In("output.txt");
            String savedLine = in.readLine();
            already += savedLine;
            already = already.substring(1);
        }

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
}
