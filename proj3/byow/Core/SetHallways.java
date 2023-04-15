package byow.Core;

import java.util.List;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class SetHallways {
    public TETile[][] world;
    private List<int[]> doorLocation;
    public SetHallways(TETile[][] w, List<int[]> d) {
        this.world = w;
        this.doorLocation = d;
    }

    public TETile[][] getWorldAfterHallways() {
        buildHallways();
        return world;
    }

    public void buildHallways() {
        int roomNum = doorLocation.size();
        for (int i = 0; i < roomNum - 1; i++) {
            int x1 = doorLocation.get(i)[0];
            int y1 = doorLocation.get(i)[1];
            int x2 = doorLocation.get(i + 1)[0];
            int y2 = doorLocation.get(i + 1)[1];
            roomConnect(x1, y1, x2, y2);
        }
    }
    public void roomConnect (int x1, int y1, int x2, int y2) {
        int currX = x1;
        int currY = y1;
        while (currX != x2 || currY != y2) {
            while (currX != x2) {
                if (currX < x2) {
                    currX += 1;
                    world[currX][currY] = Tileset.FLOOR;
                } else {
                    currX -= 1;
                    world[currX][currY] = Tileset.FLOOR;
                }
            }
            while (currY != y2) {
                if (currY < y2) {
                    currY += 1;
                    world[currX][currY] = Tileset.FLOOR;
                } else {
                    currY -= 1;
                    world[currX][currY] = Tileset.FLOOR;
                }
            }
        }
    }
}
