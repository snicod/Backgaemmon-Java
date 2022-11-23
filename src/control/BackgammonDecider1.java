package control;

import gamifier.control.Controller;
import gamifier.model.GameElement;
import gamifier.model.Model;
import gamifier.model.action.ActionList;
import model.*;
import model.action.BackgammonMoveAction;

import java.util.Arrays;
import java.util.List;

public class BackgammonDecider1 {

    private int[] pawnsOnCells;
    private MoveGenerator mg;
    private int[] moveChoosed;
    private Model model;
    private Controller control;

    public BackgammonDecider1(Model model, Controller control, MoveGenerator mg) {
        //super(model, control);
        this.model = model;
        this.control = control;
        this.mg = mg;
    }

    public void playATurn() {
        BackgammonStageModel stageModel = (BackgammonStageModel) model.getGameStage();
        List<Integer> diceValues =  stageModel.getDice().getValuesToPlay();
        ActionList actions;
        do{
            if (diceValues.size() == 1) {
                actions = new ActionList(true);
            } else {
                actions = new ActionList(false);//false
            }

            decide();
        }while (!actions.mustDoNextPlayer());

        control.nextPlayer();
    }


    public void decide() {
        BackgammonStageModel stage = (BackgammonStageModel) model.getGameStage();
        BackgammonBoard board = stage.getBoard();

        int color = model.getIdPlayer();

        pawnsOnCells = new int[24];

        for(int i = 0; i < 24; ++i) {
            GameElement element = board.getElement(board.getCells()[i].y, board.getCells()[i].x);
            if (element != null) {
                if (((Pawn) element).getColor() == color)
                    pawnsOnCells[i] = board.getElements(board.getCells()[i].y, board.getCells()[i].x).size();
                else
                    pawnsOnCells[i] = -board.getElements(board.getCells()[i].y, board.getCells()[i].x).size();
            }
        }

        Jail jail;
        if (color == Pawn.PAWN_BLACK)
            jail = stage.getBlackJail();
        else
            jail = stage.getRedJail();

        List<int[]> moveList = mg.generateMoves();

        moveChoosed = null;

        if (canBeOut(moveList)) {
            for(int[] possibleMove : moveList) {
                if (possibleMove[1] == -2 || possibleMove[1] == 25) {
                    moveChoosed = Arrays.copyOf(possibleMove, 2);
                    break;
                }
            }
        }

        else if (!jail.isEmpty()) {
            moveChoosed = Arrays.copyOf(moveList.get(0), 2);
        }

        else {
            for(int[] possibleMove : moveList) {
                if (pawnsOnCells[possibleMove[1]] == -1) {
                    if (moveChoosed == null || !isFurther(moveChoosed, possibleMove, color))
                        moveChoosed = Arrays.copyOf(possibleMove, 2);
                }
            }

            if (moveChoosed == null) {
                for(int[] possibleMove : moveList) {
                    if (pawnsOnCells[possibleMove[1]] == 1) {
                        if (moveChoosed == null || !isFurther(moveChoosed, possibleMove, color))
                            moveChoosed = Arrays.copyOf(possibleMove, 2);
                    }
                }
            }


            if (moveChoosed == null) {
                for(int[] possibleMove : moveList) {
                    if (moveChoosed == null || isFurther(moveChoosed, possibleMove, color))
                        moveChoosed = Arrays.copyOf(possibleMove, 2);
                }
            }
        }

        BackgammonMoveAction move = new BackgammonMoveAction(model, control);
        move.move(moveChoosed);


    }

    private boolean isFurther(int[] move1, int[] move2, int color) {
        if (color == Pawn.PAWN_BLACK)
            return move1[0] < move2[0];
        return move1[0] > move2[0];
    }

    private boolean canBeOut(List<int[]> moves) {
        for(int[] move : moves) {
            if (move[1] == -2 || move[1] == 25)
                return true;
        }
        return false;
    }

    public int[] getMoveChoosed() {
        return moveChoosed;
    }
}