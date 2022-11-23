package model.action;


import gamifier.control.Controller;
import gamifier.model.GridElement;
import gamifier.model.Model;
import model.*;

import java.awt.*;
import java.util.List;

public class BackgammonMoveAction {

    private Model model;
    private Controller control;
    private BackgammonStageModel usedStageModel;
    private BackgammonBoard board;

    private int playerColor;

    public BackgammonMoveAction(Model model, Controller control){
        this.model = model;
        this.control = control;
        this.usedStageModel = (BackgammonStageModel) model.getGameStage();
        this.board = usedStageModel.getBoard();
        this.playerColor = model.getIdPlayer();
    }

    public void move(int[] moveToPlay) {
        //the values required to make a move
        int rowDest;
        int colDest;
        GridElement gridDest;

        //check if the player can move, if not : pass the turn
        if (moveToPlay.length == 0)
            control.nextPlayer();

        //get the jail of the current player
        Jail playerJail;
        if (playerColor == Pawn.PAWN_BLACK)
            playerJail = usedStageModel.getBlackJail();
        else
            playerJail = usedStageModel.getRedJail();

        int originCell = moveToPlay[0]; //get the cell from where the pawn come
        int destCell = moveToPlay[1]; //get the cell where the pawn go

        //check  if the destiniation cell is in the main board
        if(destCell >= 0 && destCell< 24) {
            Point destPoint = board.getCells()[destCell];
            if (board.isElementAt(destPoint.y, destPoint.x)) {
                Pawn destPawn = (Pawn) board.getElement(destPoint.y, destPoint.x);
                if (destPawn.getColor() != playerColor)
                    sendJail(destPawn);
            }
            //define the value we need to make the move
            rowDest = destPoint.y;
            colDest = destPoint.x;
            gridDest = board;
        } else {
            //get the endcell depending on the player color
            EndCell endCell;
            if (playerColor == Pawn.PAWN_BLACK)
                endCell = usedStageModel.getBlackEndCell();
            else
                endCell = usedStageModel.getRedEndCell();

            //define the value we need to make the move
            rowDest = endCell.getNbPawns();
            colDest = 0;
            gridDest = endCell;

            //increase th number of pawn in the endcell
            endCell.increaseNbPawns();
        }

        //Now that we have the required value to make a move, we can move our pawn
        //First, we need to get the pawn we want to move :
        Pawn pawn;

        //verify if the jail is empty, if not, we must move the pawn in before moving the pawn on the board
        if(!playerJail.isEmpty()) {
            pawn = (Pawn) playerJail.getElement(playerJail.getNbPawns()-1, 0);
            playerJail.decreaseNbPawns();
            playerJail.removeElement(pawn);
            //update pawn flag
            pawn.setJailState(false);
        } else {
            Point originPoint = board.getCells()[originCell];
            pawn = (Pawn) board.getElement(originPoint.y, originPoint.x);
            board.removeElement(pawn);
            //change the flag of the pawn if needed
            if(playerColor == Pawn.PAWN_BLACK && destCell < 6)
                pawn.setInFinal(true);
            if(playerColor == Pawn.PAWN_BLACK && destCell < 0)
                pawn.setOut(true);
            if(playerColor == Pawn.PAWN_RED && destCell > 17)
                pawn.setInFinal(true);
            if(playerColor == Pawn.PAWN_RED && destCell > 23)
                pawn.setOut(true);
        }
        gridDest.putElement(pawn, rowDest, colDest);
        usedStageModel.getDice().removeValue(computeValueToRemove(originCell, destCell));

    }

    public void sendJail(Pawn pawn) {
        //delete the pawn from the main board
        board.removeElement(pawn);

        //reset the flag of the pawn :
        pawn.setJailState(true);
        pawn.setOut(false);
        pawn.setInFinal(false);

        // get the jail in wich the pawn will go
        Jail jail;
        if (playerColor == Pawn.PAWN_BLACK)
            jail = usedStageModel.getRedJail();
        else
            jail = usedStageModel.getBlackJail();

        //put the pawn in the jail
        jail.putElement(pawn, jail.getNbPawns(), 0);
        //increase the number of pawn in jail
        jail.increaseNbPawns();

    }

    public int computeValueToRemove(int originCell, int destCell) {
        int valueToRemove;
        List<Integer> diceValues = usedStageModel.getDice().getValuesToPlay();
        //Compute the value we used to move
        if(originCell > destCell)
            valueToRemove = originCell-destCell;
        else
            valueToRemove = destCell-originCell;
        //compute the value we used when we put a pawn out of the grid
        if (destCell > 23) {
            int val1 = 100;
            for (int value : diceValues) {
                if (value >= 23 - originCell && value < val1) {
                    valueToRemove = value;
                    val1 = value;
                }

            }
        }
        if (destCell < 0) {
            int val1 = 100;
            for (int value : diceValues) {
                if (value >= originCell && value < val1) {
                    valueToRemove = value;
                    val1 = value;
                }
            }
        }
        return valueToRemove;
    }
}
