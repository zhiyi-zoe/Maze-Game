package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HeadsUpDisplay {
    public TETile[][] world;
    public int[] avatar;
    public List<TETile> appearanceList;
    public HeadsUpDisplay(TETile[][] world, int[] avatar) {
        this.world = world;
        this.avatar = avatar;
        this.appearanceList = creatAppearanceList();
    }
    public String mouseText() {
        Font fontSmall = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(fontSmall);
        int mouseX = (int) StdDraw.mouseX();
        int mouseY = (int) StdDraw.mouseY();
        TETile current = world[mouseX][mouseY];
        String description = current.description();
        return description;
        //int numYTiles = world[0].length;
        //StdDraw.text(0, numYTiles, description);
        //renderFrame(world);
        //StdDraw.pause(10);
    }

    public List<TETile> creatAppearanceList() {
        List<TETile> al = new ArrayList<>();
        al.add(Tileset.AVATAR);
        al.add(Tileset.FLOWER);
        al.add(Tileset.GRASS);
        al.add(Tileset.MOUNTAIN);
        al.add(Tileset.SAND);
        al.add(Tileset.TREE);
        al.add(Tileset.WATER);
        return al;
    }
    public void changeAppearance() {
        int currentX = avatar[0];
        int currentY = avatar[1];
        TETile currentAppearance = world[currentX][currentY];
        int currentAppearanceIndex = appearanceList.indexOf(currentAppearance);
        TETile nextAppearance;
        if (currentAppearanceIndex + 1 == appearanceList.size()) {
            nextAppearance = appearanceList.get(0);
        } else {
            nextAppearance = appearanceList.get(currentAppearanceIndex + 1);
        }
        world[currentX][currentY] = nextAppearance;
    }
}
