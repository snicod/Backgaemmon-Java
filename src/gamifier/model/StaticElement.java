package gamifier.model;

/*
 gamifier.model.StaticElement class :
 Describes an element of the game that will never move and
 never update for example the background image.
 */

public abstract class StaticElement extends GameElement {
    public StaticElement(int x, int y, GameStageModel gameStageModel) {
        super(x,y, gameStageModel, ElementTypes.getType("static"));
    }
    public StaticElement(int x, int y, GameStageModel gameStageModel, int type) {
        super(x,y, gameStageModel, type);
    }
    public void setLocation(int x, int y) {}
}
