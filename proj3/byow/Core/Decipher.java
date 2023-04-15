package byow.Core;

import byow.TileEngine.TERenderer;

public class Decipher {
    private long seed;
    private TERenderer space;
    public Decipher(String input, int width, int height) {
        int len = input.length();

        String num = "";
        boolean a = false;
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == 's' || input.charAt(i) == 'S') {
                a = false;
            }
            if (a) {
                num = num + input.charAt(i);
            }
            if (input.charAt(i) == 'N' || input.charAt(i) == 'n') {
                a = true;
                space = new TERenderer();//render a world without anything
                //space.initialize(width, height, 2, 5);
            }
        }
        seed = Long.parseLong(num);

    }
    public long showSeed() {
        return seed;
    }
    public TERenderer showTE() {
        return space;
    }
}
