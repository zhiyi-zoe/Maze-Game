package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Decipher {
    private long seed;
    private TERenderer space;
    private TETile[][] worldFrameFin;
    private int[] avatarPos;
    public Decipher(String input, int width, int height) {
        int len = input.length();
        String num = "";
        boolean a = false;//判断seed是否输入完成
        boolean b = false;//判断是否是退出指令
        for (int i = 0; i < len; i++) {
            //判断seed输入结束，创建新世界
            if (input.charAt(i) == 's' || input.charAt(i) == 'S') {
                a = false;
                seed = Long.parseLong(num);
                BuildRooms rooms = new BuildRooms(seed, width, height);
                TETile[][]worldFrame = rooms.getTile();
                List doorLocation = rooms.getOpen();
                SetHallways setHallways = new SetHallways(worldFrame, doorLocation);
                worldFrame = setHallways.getWorldAfterHallways();
                SetWall setWall = new SetWall(worldFrame);
                worldFrameFin = setWall.getWorld();
                space.renderFrame(worldFrameFin);
            }
            //记录seed
            if (a) {
                num = num + input.charAt(i);
            }
            //new game
            if (input.charAt(i) == 'N' || input.charAt(i) == 'n') {
                //创建新的随机世界
                a = true;
                space = new TERenderer();//render a world without anything
                space.initialize(width, height);
            }
            //准备输入quit指令
            if (input.charAt(i) == ':') {
                b = true;
            }
            //The four movements，实时更新绘图
            if (input.charAt(i) == 'W' || input.charAt(i) == 'w') {
                MoveControl MC = new MoveControl(worldFrameFin, avatarPos);
                Character thisChar = 'W';
                avatarPos = MC.oneMovement(thisChar);
                space.renderFrame(worldFrameFin);

            }
            if (input.charAt(i) == 'S' || input.charAt(i) == 's') {
                MoveControl MC = new MoveControl(worldFrameFin, avatarPos);
                Character thisChar = 'S';
                avatarPos = MC.oneMovement(thisChar);
            }
            if (input.charAt(i) == 'A' || input.charAt(i) == 'a') {
                MoveControl MC = new MoveControl(worldFrameFin, avatarPos);
                Character thisChar = 'A';
                avatarPos = MC.oneMovement(thisChar);
            }
            if (input.charAt(i) == 'D' || input.charAt(i) == 'd') {
                MoveControl MC = new MoveControl(worldFrameFin, avatarPos);
                Character thisChar = 'D';
                avatarPos = MC.oneMovement(thisChar);
            }
            //not finished
            if (b == true && (input.charAt(i) == 'Q' || input.charAt(i) == 'q')) {
                try {
                    File myFile = new File("output.txt");
                    if (!myFile.createNewFile()) {
                        //Nothing happens (not sure)
                    }
                    FileWriter myWriter = new FileWriter(myFile);
                    //把图输入进去，默认只有四种，且全屏都是地图
                    for (int j = 0; j < height; j++) {
                        for (int k = 0; k < width; k++) {
                            String m;
                            if (worldFrameFin[k][j] == Tileset.FLOOR) {
                                m = "F";
                            } else if (worldFrameFin[k][j] == Tileset.WALL) {
                                m = "W";
                            } else if (worldFrameFin[k][j] == Tileset.NOTHING) {
                                m = "N";
                            } else {
                                m = "A";
                            }
                            myWriter.write(m);
                        }
                        myWriter.write("\n");
                    }
                    //最后两行是avatar的位置
                    myWriter.write(avatarPos[0]);
                    myWriter.write("\n");
                    myWriter.write(avatarPos[1]);
                    myWriter.close();
                } catch (IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;

            } else {
                b = false;
            }
            if (input.charAt(i) == 'L' || input.charAt(i) == 'l') {

            }
        }

    }
    public long showSeed() {
        return seed;
    }
    public TERenderer showTE() {
        return space;
    }
    public TETile[][] showTile() {
        return worldFrameFin;
    }
}
