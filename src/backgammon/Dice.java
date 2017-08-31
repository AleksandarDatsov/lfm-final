package backgammon;

import java.util.Random;

public class Dice {
    private final Random rand;
    private final int faces;

    public Dice(int faces) {
        this.rand = new Random();
        this.faces = faces;
    }

    public int roll() {
        return rand.nextInt(faces) + 1;
    }
}