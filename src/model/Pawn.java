package model;

import gamifier.model.ElementTypes;
import gamifier.model.GameElement;
import gamifier.model.GameStageModel;
import gamifier.view.GridGeometry;

public class Pawn extends GameElement {
    public static final int PAWN_BLACK = 0;
    public static final int PAWN_RED = 1;

    private int color;
    //Flags
    private boolean isOut;
    private boolean isInFinal;
    private boolean isInJail;



    public Pawn(int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        //Create a new type of game element
        ElementTypes.register("pawn", 50);
        type = ElementTypes.getType("pawn");

        this.color = color;
        //initialise the flags
        this.isOut = false;
        this.isInJail = false;
        this.isInFinal = false;
    }

    public boolean isOut() {
        return isOut;
    }
    public void setOut(boolean out) {
        this.isOut = out;
    }

    public boolean isInJail() {
        return isInJail;
    }
    public void setJailState(boolean inJail) {
        isInJail = inJail;
    }

    public boolean isInFinal() {
        return isInFinal;
    }
    public void setInFinal(boolean inFinal) {
        isInFinal = inFinal;
    }

    public int getColor() {
        return color;
    }

    @Override
    public void update(double width, double height, GridGeometry gridGeometry) {

    }
}
