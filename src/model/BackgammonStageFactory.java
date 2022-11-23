package model;

import gamifier.model.GameStageModel;
import gamifier.model.StageElementsFactory;

import java.util.Random;

public class BackgammonStageFactory extends StageElementsFactory {
    private BackgammonStageModel stageModel;

    public BackgammonStageFactory(GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (BackgammonStageModel) gameStageModel;
    }

    @Override
    public void setup() {
        // create the board
        stageModel.setBoard(new BackgammonBoard(20, 20, stageModel));
        // create the pawns
        Pawn[] blackPawns = new Pawn[15];
        for (int i = 0; i<15; i++) {
            blackPawns[i] = new Pawn(Pawn.PAWN_BLACK, stageModel);
        }
        stageModel.setBlackPawns(blackPawns);
        Pawn[] redPawns = new Pawn[15];
        for (int i = 0; i<15; i++) {
            redPawns[i] = new Pawn(Pawn.PAWN_RED, stageModel);
        }
        stageModel.setRedPawns(redPawns);

        //create the Jails
        stageModel.setBlackJail(new Jail(Pawn.PAWN_BLACK, 250, 20, stageModel));
        stageModel.setRedJail(new Jail(Pawn.PAWN_RED, 250, 250, stageModel));

        //create the EndCell
        stageModel.setBlackEndCell(new EndCell(Pawn.PAWN_BLACK ,500, 20, stageModel));
        stageModel.setRedEndCell(new EndCell(Pawn.PAWN_RED, 500, 250, stageModel));

        //create and generate the value of the first dice
        Dice dice = new Dice(6, stageModel);
        dice.generateValuesofDice(new Random());
        stageModel.setDice(dice);

        //Put the pawn on the board
        int lastPawnPlaced = 0;
        for(int i = 0; i<2; i++){
            stageModel.getBoard().putElement(stageModel.getRedPawns()[lastPawnPlaced], 1, 11);
            stageModel.getBoard().putElement(stageModel.getBlackPawns()[lastPawnPlaced], 0, 11);
            lastPawnPlaced++;
        }
        for(int i = 0; i<5; i++){
            stageModel.getBoard().putElement(stageModel.getRedPawns()[lastPawnPlaced], 1, 0);
            stageModel.getBoard().putElement(stageModel.getBlackPawns()[lastPawnPlaced], 0, 0);
            lastPawnPlaced++;
        }
        for(int i = 0; i<3; i++){
            stageModel.getBoard().putElement(stageModel.getRedPawns()[lastPawnPlaced], 0, 4);
            stageModel.getBoard().putElement(stageModel.getBlackPawns()[lastPawnPlaced], 1, 4);
            lastPawnPlaced++;
        }
        for(int i = 0; i<5; i++){
            stageModel.getBoard().putElement(stageModel.getRedPawns()[lastPawnPlaced], 0, 6);
            stageModel.getRedPawns()[lastPawnPlaced].setInFinal(true);
            stageModel.getBoard().putElement(stageModel.getBlackPawns()[lastPawnPlaced], 1, 6);
            stageModel.getBlackPawns()[lastPawnPlaced].setInFinal(true);
            lastPawnPlaced++;
        }
    }
}
