package control;

import gamifier.control.Controller;
import gamifier.model.GameElement;
import gamifier.model.Model;
import gamifier.model.Player;
import gamifier.view.View;
import model.BackgammonStageModel;
import model.Dice;
import model.MoveGenerator;
import model.Pawn;

import java.util.Random;

public class ControllerBackgammon extends Controller {
    private int aiSelected;
    public ControllerBackgammon(Model model, View view) {
        super(model, view);
        setControlAction(new ControllerBackgammonAction(model, view, this));
        setControlKey(new ControllerBackgammonKey(model, view, this));
        setControlMouse(new ControllerBackgammonMouse(model, view, this));
    }

    public void setAi(int aiSelected) {
        this.aiSelected = aiSelected;
    }

    public void nextPlayer() {



        model.setNextPlayer();

        Player p = model.getCurrentPlayer();

        BackgammonStageModel stageModel = (BackgammonStageModel) model.getGameStage();
        //stageModel.getPlayerName().setText(p.getName());

        Dice dices = new Dice(6, stageModel);
        dices.generateValuesofDice(new Random());
        stageModel.setDice(dices);

        //do {

            if(p.getType() == Player.HUMAN) {
                System.out.println("HUMAN PLAYER PLAYS");
                ConsoleMode playerDecider = new ConsoleMode(model, this, view);
                playerDecider.playATurn();
            }else {
                MoveGenerator mg= new MoveGenerator(stageModel, model.getIdPlayer(), stageModel.getDice());
                System.out.println("COMPUTER PLAYS");
                if (aiSelected == 1) {
                    BackgammonDecider ai = new BackgammonDecider(model, this, mg);
                    ai.playATurn();
                }else {
                    BackgammonDecider1 ai = new BackgammonDecider1(model, this, mg);
                    ai.playATurn();
                }


                /*
                BackgammonDecider computerDecider = new BackgammonDecider(model, this);
                ActionPlayer play = new ActionPlayer(model, view, this, computerDecider, null);
                play.start();*/
            }


        //} while (dices.getValuesToPlay().size() > 0);
    }
    public void printBoard(BackgammonStageModel stageModel) {
        System.out.println();
        for (int i = 12; i < 24; ++i) {
            System.out.print("   " + i + " ");
        }
        System.out.println();
        for (int i = 12; i < 24; ++i) {
            int row = stageModel.getBoard().getCells()[i].y;
            int col = stageModel.getBoard().getCells()[i].x;
            GameElement element = stageModel.getBoard().getElement(row, col);
            int size = stageModel.getBoard().getElements(row, col).size();
            if (element != null) {
                if (((Pawn) element).getColor() == Pawn.PAWN_RED)
                    System.out.print("| \u001B[31mR");
                else
                    System.out.print("| \u001B[34mB");
            } else
                System.out.print("| X");
            if (size < 10)
                System.out.print(0);
            System.out.print(size + " \u001B[0m");
        }
        System.out.println("|");
        System.out.println("-------------------------------------------------------------------------");
        for (int i = 11; i > -1; --i) {
            int row = stageModel.getBoard().getCells()[i].y;
            int col = stageModel.getBoard().getCells()[i].x;
            GameElement element = stageModel.getBoard().getElement(row, col);
            int size = stageModel.getBoard().getElements(row, col).size();
            if (element != null) {
                if (((Pawn) element).getColor() == Pawn.PAWN_RED)
                    System.out.print("| \u001B[31mR");
                else
                    System.out.print("| \u001B[34mB");
            } else
                System.out.print("| X");
            if (size < 10)
                System.out.print(0);
            System.out.print(size + " \u001B[0m");
        }
        System.out.println("|");
        for (int i = 11; i > -1; --i) {
            if (i >= 10)
                System.out.print("   " + i + " ");
            else
                System.out.print("   " + i + "  ");

        }
        System.out.println("\n");

        System.out.println("Jail Black (n° 24) : " + stageModel.getBlackJail().getNbPawns());
        System.out.println("Jail Red (n° -1) : " + stageModel.getRedJail().getNbPawns());
        System.out.println("EndCell Black  (n° -2) : " + stageModel.getBlackEndCell().getNbPawns());
        System.out.println("EndCell Red (n° 25  ) : " + stageModel.getRedEndCell().getNbPawns());

    }
}
