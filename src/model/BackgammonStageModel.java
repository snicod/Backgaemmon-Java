package model;

import gamifier.model.GameStageModel;
import gamifier.model.Model;
import gamifier.model.StageElementsFactory;

public class BackgammonStageModel extends GameStageModel {

    public final static int STATE_SELECTPAWNSTACK = 1; //We must select a pawn
    public final static int STATE_SELECTDEST = 2; // We must select a destination

    private Dice dice;

    private BackgammonBoard board;

    private Pawn[] blackPawns;
    private Pawn[] redPawns;

    private Jail blackJail;
    private Jail redJail;

    private EndCell blackEndCell;
    private EndCell redEndCell;

    public BackgammonStageModel(String name, Model model) {
        super(name, model);
        state = STATE_SELECTPAWNSTACK;
    }

    public BackgammonBoard getBoard() {
        return board;
    }
    public void setBoard(BackgammonBoard board) {
        this.board = board;
        addGrid(board);
    }

    public Pawn[] getBlackPawns() {
        return blackPawns;
    }
    public void setBlackPawns(Pawn[] blackPawns) {
        this.blackPawns = blackPawns;
        for(int i = 0; i< blackPawns.length; i++) {
            addElement(blackPawns[i]);
        }
    }

    public Pawn[] getRedPawns() {
        return redPawns;
    }
    public void setRedPawns(Pawn[] redPawns) {
        this.redPawns = redPawns;
        for(int i = 0; i< redPawns.length; i++) {
            addElement(redPawns[i]);
        }
    }

    public Dice getDice() {
        return dice;
    }
    public void setDice(Dice dices) {
        this.dice = dices;
        addElement(dices);
    }

    public Jail getBlackJail() {
        return blackJail;
    }
    public void setBlackJail(Jail blackJail) {
        this.blackJail = blackJail;
        addGrid(this.blackJail);
    }

    public Jail getRedJail() {
        return redJail;
    }
    public void setRedJail(Jail redJail) {
        this.redJail = redJail;
        addGrid(this.redJail);
    }

    public EndCell getBlackEndCell() {
        return blackEndCell;
    }
    public void setBlackEndCell(EndCell blackEndCase) {
        this.blackEndCell = blackEndCase;
        addGrid(this.blackEndCell);
    }

    public EndCell getRedEndCell() {
        return redEndCell;
    }
    public void setRedEndCell(EndCell redEndCase) {
        this.redEndCell = redEndCase;
        addGrid(this.redEndCell);
    }

    @Override
    public StageElementsFactory getDefaultElementFactory() {
        return new BackgammonStageFactory(this);
    }
}
