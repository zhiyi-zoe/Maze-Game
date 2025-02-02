package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HeadsUpDisplay {
    private static final int OFFSET = 10;
    private int width;
    private int height;
    private TETile[][] world;
    private int[] avatar;
    private List<TETile> appearanceList;
    public HeadsUpDisplay(TETile[][] world, int[] avatar) {
        this.width = world.length;
        this.height = world[0].length;
        this.world = world;
        this.avatar = avatar;
        this.appearanceList = creatAppearanceList();
    }
    public void mouseText() {
        StdDraw.setPenColor(Color.WHITE);
        int mouseX = (int) StdDraw.mouseX() - OFFSET;
        int mouseY = (int) StdDraw.mouseY() - OFFSET;
        String description = "";
        if ((mouseX >= 0 && mouseX < width) && (mouseY >= 0 && mouseY < height)) {
            TETile current = world[mouseX][mouseY];
            description = current.description();
        }
        StdDraw.textLeft(OFFSET, OFFSET, description);
    }
    public TETile[][] getWorld() {
        return world;
    }

    public List<TETile> creatAppearanceList() {
        List<TETile> al = new ArrayList<>();
        al.add(Tileset.AVATAR);
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

    public void normalMenu() {
        StdDraw.textLeft(OFFSET, height + OFFSET + OFFSET / 2, "Menu");
        StdDraw.textLeft(OFFSET * 2, height + OFFSET + OFFSET / 2, "Up (W)");
        StdDraw.textLeft(OFFSET * (2 + 1), height + OFFSET + OFFSET / 2, "Down (S)");
        StdDraw.textLeft(OFFSET * 2 * 2, height + OFFSET + OFFSET / 2, "Left (A)");
        StdDraw.textLeft(OFFSET * (2 * 2 + 1), height + OFFSET + OFFSET / 2, "Right (D)");
        StdDraw.textLeft(OFFSET * (2 * 2 + 2), height + OFFSET + OFFSET / 2, "Change Appearance (P)");
        StdDraw.textLeft(OFFSET * 2 * 2 * 2, height + OFFSET + OFFSET / 2, "Quit Game (:Q)");
    }
}
