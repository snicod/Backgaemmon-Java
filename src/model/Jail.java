package model;

import gamifier.model.GameStageModel;
import gamifier.model.GridElement;

public class Jail extends GridElement {
    private int color;
    private int nbPawns;
    private boolean empty;

    public Jail(int color, int x, int y, GameStageModel gameStageModel) {
        super("Jail" + String.valueOf(color), x, y, 15, 1, gameStageModel);
        this.color = color;
        this.nbPawns = 0;
        // initializing flag
        this.empty = true;
    }

    public void increaseNbPawns() {
        ++nbPawns;
        if (nbPawns == 1)
            empty = false;
    }

    public void decreaseNbPawns() {
        if (nbPawns > 0)
            --nbPawns;
        if (nbPawns == 0)
            empty = true;
    }

    public int getColor() {
        return color;
    }

    public int getNbPawns() {
        return nbPawns;
    }

    @Override
    public boolean isEmpty() {
        return empty;
    }
}
