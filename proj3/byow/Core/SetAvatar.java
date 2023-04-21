package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;

public class SetAvatar {
    private TETile[][] world;
    private List<int[]> doorLocation;
    private int[] avatar;
    public SetAvatar(TETile[][] world, List<int[]> d) {
        this.world = world;
        this.doorLocation = d;
        this.avatar = buildAvatar();
    }
    public int[] buildAvatar() {
        int door0X = doorLocation.get(0)[0];
        int door0Y = doorLocation.get(0)[1];
        world[door0X][door0Y] = Tileset.AVATAR;
        int[] avatarLocation = new int[2];
        avatarLocation[0] = door0X;
        avatarLocation[1] = door0Y;
        return avatarLocation;
    }
}
