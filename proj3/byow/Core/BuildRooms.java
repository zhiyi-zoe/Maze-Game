package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuildRooms {
    private long seed;
    private Random RANDOM;
    private int roomNum;
    private ArrayList<int[]> openList;
    private int WIDTH;
    private int HEIGHT;
    private TETile[][] world;
    public BuildRooms(long sed, int width, int height) {
        seed = sed;
        RANDOM = new Random(seed);
        WIDTH = width;
        HEIGHT = height;
        roomNum = RANDOM.nextInt(10) + 3;
        world = new TETile[width][height];//initialize tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        openList = new ArrayList<>();
        for (int i = 0; i < roomNum; i++) {
            oneRoom a = new oneRoom(world);
            int x = a.getxCord();
            int y = a.getyCord();
            int m = a.getWidth();
            int n = a.getHeight();
            for (int o = x; o < x + m; o++) {
                for (int p = y; p < y + n; p++) {
                    world[o][p] = Tileset.FLOOR;
                }
            }
            int[] gg = new int[2];
            gg[0] = a.getOpenX();
            gg[1] = a.getOpenY();
            openList.add(gg);
        }
    }
    private class oneRoom {
        private int width;
        private int height;
        private int xCord;
        private int yCord;
        private int roomSizeMin = 2;
        private int roomSizeMax = 8;
        private TETile[][] spaceAll;
        private int openX;
        private int openY;
        public oneRoom(TETile[][] world) {
            spaceAll = world;
            boolean isOverlap = false;
            while (true) {
                isOverlap = false;
                width = RANDOM.nextInt(roomSizeMax - roomSizeMin + 1) + roomSizeMin;
                height = RANDOM.nextInt(roomSizeMax - roomSizeMin + 1) + roomSizeMin;
                xCord = RANDOM.nextInt(WIDTH - 4) + 2;
                yCord = RANDOM.nextInt(HEIGHT - 5) + 2;
                if (xCord + width >= WIDTH) {
                    width = WIDTH - xCord - 1;
                }
                if (yCord + height >= HEIGHT) {
                    height = HEIGHT - yCord - 1;
                }
                for (int i = xCord - 1; i < xCord + width; i++) {
                    for (int j = yCord - 1; j < yCord + height; j++) {
                        if (world[i][j] == Tileset.FLOOR) {
                            isOverlap = true;//有重叠
                        }
                    }
                }
                if (!isOverlap) {
                    break;
                }
            }
            //确定开口
            boolean gg = false;
            while (true) {
                gg = false;
                boolean t = RANDOM.nextBoolean();
                openX = xCord + 1;
                if (t) {
                    openY = yCord;
                } else {
                    openY = yCord + height - 1;
                }
                for (int[] i : openList) {
                    if (i[0] == openX && i[1] == openY) {
                        gg = true;
                    }
                }
                if (!gg) {
                    break;
                }
            }
        }
        public int getWidth() {
            return width;
        }
        public int getHeight() {
            return height;
        }
        public int getxCord() {
            return xCord;
        }
        public int getyCord() {
            return yCord;
        }
        public int getOpenX() {
            return openX;
        }
        public int getOpenY() {
            return openY;
        }
    }
    public List getOpen() {
        return openList;
    }
    public TETile[][] getTile() {
        return world;
    }
    public static void main(String[] args) {
        //用于测试，可删
        TERenderer ter = new TERenderer();
        ter.initialize(80, 30);
        BuildRooms room = new BuildRooms(100, 80, 30);
        TETile[][] world = room.getTile();
        ter.renderFrame(world);
        System.out.println(room.getOpen());
    }

}
