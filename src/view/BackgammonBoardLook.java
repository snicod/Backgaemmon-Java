package view;

import gamifier.model.GameElement;
import gamifier.view.GridLook;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BackgammonBoardLook extends GridLook {

    private Rectangle[][] cells;

    public BackgammonBoardLook(int width, int height, GameElement element) {
        super(width, height, (width-4)/12, (height-4)/2, 2, "0x814512", element);
        cells = new Rectangle[2][13];
        for (int i=0;i<2;i++) {
            for(int j=0;j<12;j++) {

                Color c;
                if ((i+j)%2 == 0) {
                    c = Color.BEIGE;
                }
                else {
                    c = Color.DARKGRAY;
                }
                cells[i][j] = new Rectangle(cellWidth, cellHeight, c);
                cells[i][j].setX(j*cellWidth+borderWidth);
                cells[i][j].setY(i*cellHeight+borderWidth);
                addShape(cells[i][j]);

            }
        }
    }

/*
    @Override
    public void onChange() {
        BackgammonBoard board = (BackgammonBoard) element;
        boolean[][] reach = board.getReachableCells();
        for(int i = 0;i<2; i++) {
            for(int j = 0; j<13; j++) {
            if(j == 6)
                    continue;
                else {
                    if(reach[i][j]) {
                        cells[i][j].setStrokeWidth(3);
                        cells[i][j].setStrokeMiterLimit(10);
                        cells[i][j].setStrokeType(StrokeType.CENTERED);
                        cells[i][j].setStroke(Color.valueOf("0x333333"));
                    } else {
                        cells[i][j].setStrokeWidth(0);
                    }
                }
            }
        }
    }

 */
}
