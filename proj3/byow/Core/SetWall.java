package byow.Core;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class SetWall {
    private TETile[][] world;
    private int width;
    private int height;
    public SetWall(TETile[][] w) {
        this.world = w;
        this.width = world.length;
        this.height = world[0].length;
    }

    public TETile[][] getWorld() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                buildWall(x, y);
            }
        }
        return world;
    }

    public void buildWall(int x, int y) {
        TETile currType = world[x][y];
        if (!(currType.equals(Tileset.WALL) || currType.equals(Tileset.NOTHING))) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    TETile adjacentType = world[x + i][y + j];
                    if (adjacentType.equals(Tileset.NOTHING)) {
                        world[x + i][y + j] = Tileset.WALL;
                    }
                }
            }
        }
    }
}
