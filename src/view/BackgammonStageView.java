package view;

import gamifier.model.GameStageModel;
import gamifier.view.GameStageView;
import model.BackgammonStageModel;

public class BackgammonStageView extends GameStageView {
    public BackgammonStageView(String name, GameStageModel gameStageModel) {
        super(name, gameStageModel);
        width = 1500;
        height = 800;
    }

    @Override
    public void createLooks() {
        int boardWidth = 1005;
        int boardHeight = 700;
        BackgammonStageModel model = (BackgammonStageModel) gameStageModel;

        addLook(new BackgammonBoardLook(boardWidth, boardHeight, model.getBoard()));
        for(int i = 0; i<15; i++) {
            addLook(new PawnLook(30, model.getBlackPawns()[i]));
            addLook(new PawnLook(30, model.getRedPawns()[i]));
        }



    }
}
