package control;

import gamifier.control.Controller;
import gamifier.model.Model;
import gamifier.model.action.ActionList;
import gamifier.view.View;
import model.BackgammonStageModel;
import model.Jail;
import model.MoveGenerator;
import model.Pawn;
import model.action.BackgammonMoveAction;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMode {
    private BackgammonStageModel stageModel;
    private Model model;
    private View view;
    private Controller control;

    public ConsoleMode(Model model, Controller control, View view){
        this.model = model;
        this.control = control;
        this.view = view;
        stageModel = (BackgammonStageModel) model.getGameStage();
    }

    public void playATurn() {
        List<Integer> diceValues =  stageModel.getDice().getValuesToPlay();
        ActionList actions;
        do{
            if (diceValues.size() == 1) {
                actions = new ActionList(true);
            } else {
                actions = new ActionList(false);//false
            }

            playAmove();
        }while (!actions.mustDoNextPlayer());

        control.nextPlayer();
    }

    public void playAmove() {

        int playerColor = model.getIdPlayer(); ;
        String endCellName = "EndCell" + String.valueOf(playerColor);
        String jailName;
        Jail playerJail;
        if (playerColor == Pawn.PAWN_BLACK) {
            jailName = "Jail" + String.valueOf(Pawn.PAWN_RED);
            playerJail = stageModel.getBlackJail();
        }
        else {
            jailName = "Jail" + String.valueOf(Pawn.PAWN_BLACK);
            playerJail = stageModel.getRedJail();
        }
        String boardName = "backgammonboard";
        //
        // CHECK IF IT'S THE LAST VALUE OF THE DICE TO PLAY
        //
        List<Integer> diceValues =  stageModel.getDice().getValuesToPlay();
        ActionList actions;
        if (diceValues.size() == 1) {
            actions = new ActionList(true);
        } else {
            actions = new ActionList(false);//false
        }
        //Show on the console the value we have on the dice
        System.out.println("Values to play : ");
        for(int val : diceValues)
            System.out.println("\t" + val);

        ((ControllerBackgammon) control).printBoard(stageModel);
        System.out.println();

        // get the cell on wich we want to playAmove
        int[] moveToPlay = askMove(new Scanner(System.in), playerColor);

        //make the move :
        BackgammonMoveAction move = new BackgammonMoveAction(model, control);
        move.move(moveToPlay);

        /*int originCell = moveToPlay[0];
        int destCell = moveToPlay[1];
        if(moveToPlay.length == 0) {
            control.nextPlayer();
        }


        String gridDestName; // the name of the grid of destination(backgammonboard or the jail or the endCell)
        int rowDest;
        int colDest;

        if (destCell >= 0 && destCell < 24) {
            Point pToJail = stageModel.getBoard().getCells()[destCell];
            if(stageModel.getBoard().isElementAt(pToJail.y, pToJail.x)) {
                Pawn pawnToJail = (Pawn) stageModel.getBoard().getElement(pToJail.y, pToJail.x);
                if (pawnToJail.getColor() != playerColor) {
                    stageModel.getBoard().removeElement(pawnToJail);
                    Jail jail = null;
                    if (playerColor == Pawn.PAWN_BLACK)
                        jail = stageModel.getRedJail();
                    else
                        jail = stageModel.getBlackJail();
                    model.getGrid(jailName).putElement(pawnToJail, jail.getNbPawns(), 0);
                    System.out.println("one pawn go to jail");
                    ((ControllerBackgammon) control).printBoard(stageModel);
                    System.out.println();
                    /*
                    GameAction moveToJail = new MoveAction(model, pawnToJail, jailName, jail.getNbPawns(), 0, AnimationTypes.MOVELINEARPROP_NAME, 0, 0, 20);
                    actions.addSingleAction(moveToJail);
                     */
        /*
                    jail.increaseNbPawns();
                }
            }
            Point pDest = stageModel.getBoard().getCells()[destCell];
            rowDest = pDest.y;
            colDest = pDest.x;
            gridDestName = boardName;
        } else {
            EndCell endCell = null;
            if (playerColor == Pawn.PAWN_BLACK)
                endCell = stageModel.getRedEndCell();
            else
                endCell = stageModel.getBlackEndCell();
            endCell.increaseNbPawns();
            rowDest = endCell.getNbPawns();
            colDest = 0;
            gridDestName = endCellName;
        }
        int valueToRemove;
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

        stageModel.getDice().removeValue(valueToRemove);

        Pawn pawn;
        if(!playerJail.isEmpty()) {
            pawn = (Pawn) playerJail.getElement(playerJail.getNbPawns()-1, 0);
            playerJail.removeElement(pawn);
            model.getGrid(gridDestName).putElement(pawn, rowDest, colDest);
            playerJail.decreaseNbPawns();
        }else {

            pawn = (Pawn) stageModel.getBoard().getElement(stageModel.getBoard().getCells()[originCell].y, stageModel.getBoard().getCells()[originCell].x); // the pawn you want to move
            model.getGrid(boardName).removeElement(pawn);
            model.getGrid(gridDestName).putElement(pawn, rowDest, colDest);
            if(playerColor == Pawn.PAWN_BLACK && destCell < 6)
                pawn.setInFinal(true);
            if(playerColor == Pawn.PAWN_BLACK && destCell < 0)
                pawn.setOut(true);
            if(playerColor == Pawn.PAWN_RED && destCell > 17)
                pawn.setInFinal(true);
            if(playerColor == Pawn.PAWN_RED && destCell > 23)
                pawn.setOut(true);

        }*/



        /*
        GameAction move = new MoveAction(model, pawn, gridDestName, rowDest, colDest, AnimationTypes.MOVELINEARPROP_NAME, 20, 20, 10);
        actions.addSingleAction(move);
        ActionPlayer playAmove = new ActionPlayer(model, view, control, actions);
        move.start();
         */
    }

    public int[] askMove(Scanner input, int color) {
        MoveGenerator mg = new MoveGenerator(stageModel, color, stageModel.getDice());
        mg.generateMoves();
        List<int[]> possibleMoves = mg.getDistinctMoves();
        System.out.println("Origin\tFinish");
        for (int[] possibleMove : possibleMoves) {
            System.out.println(possibleMove[0] + "\t" + possibleMove[1]);
        }
        int origin = 0;
        int finish = 0;
        boolean isValid;
        do {
            try {
                System.out.println("Enter the origin cell and then the finish cell : ");
                origin = input.nextInt();
                finish = input.nextInt();
                isValid = isMoveValid(possibleMoves, origin, finish);

                if (!isValid)
                    System.out.println("This move cannot be played");
            }
            catch (InputMismatchException e) {
                isValid = false;
                System.err.println("Please enter a number");
                input.next(); //to avoid infinite loop
            }
        } while (!isValid);
        return new int[] {origin, finish};
    }

    public boolean isMoveValid(List<int[]> possibleMoves, int origin, int finish) {
        for(int i = 0; i < possibleMoves.size(); ++i) {
            if (possibleMoves.get(i)[0] == origin && possibleMoves.get(i)[1] == finish)
                return true;
        }
        return false;
    }
}
