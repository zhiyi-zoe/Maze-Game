package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class MoveControl {
    public TETile[][] world;
    public int[] avatar;
    public MoveControl(TETile[][] world, int[] avatar) {
        this.world = world;
        this.avatar = avatar;
    }
    public int[] oneMovement(char input) {
        int currentX = avatar[0];
        int currentY = avatar[1];
        int moveX = 0;
        int moveY = 0;
        int[] result = avatar;
        switch (input) {
            case 'W' -> moveY++;
            case 'S' -> moveY--;
            case 'A' -> moveX--;
            case 'D' -> moveX++;
        }
        TETile next = world[currentX + moveX][currentY + moveY];
        if (!next.equals(Tileset.WALL)) {
            world[currentX][currentY] = Tileset.FLOOR;
            world[currentX + moveX][currentY + moveY] = Tileset.AVATAR;
            result[0] = currentX + moveX;
            result[1] = currentY + moveY;
        }
        return result;
    }
}
