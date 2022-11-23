package model;

import gamifier.model.GameElement;
import gamifier.model.GameStageModel;
import gamifier.model.GridElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class BackgammonBoard extends GridElement {
    private Point[] cells;
    public BackgammonBoard(int x, int y, GameStageModel gameStageModel) {
        super("backgammonboard", x, y, 2, 12, gameStageModel);
        resetReachableCells(false);
        cells = new Point[24];
        int cellNumber = 0;
        for(int i = 1; i>=0; i--) {
            for(int j = 0; j< 12; j++) {
                int X = i;
                int Y = 11*i+((-2*i+1)*j);
                cells[cellNumber] = new Point(Y, X);
                cellNumber++;
            }
        }
    }

    public Point[] getCells() {
        return cells;
    }

    public void setValidCells(Dice dice, int color, Point cell) {
        resetReachableCells(false);
        List<Point> valid = computeValidCells(dice, color, cell);
        if (valid != null) {
            for(Point p : valid) {
                reachableCells[p.y][p.x] = true;
            }
        }
        lookChanged = true;
    }

    public List<Point> computeValidCells(Dice dice, int color, Point cell) {
        List<Point> lst = new ArrayList<>();
        // get the number of the cell of the pawn we want to move
        int cellNumber = getCellNumber(cell);
        // get the values of the dices
        List<Integer> dicesValues = dice.getValuesOfDice();

        /*
         * Define in which direction we need to browse the board
         */
        int direction;
        if(color == Pawn.PAWN_RED)
            direction = 1;
        else
            direction = -1;


        /*
         * for each dice value, add all reachable cell coord to a list
         */
        for(int value : dicesValues){

            if(cellNumber+(value*direction) < 24 && cellNumber+(value*direction) > 0) {

                Point nextCell = cells[cellNumber+value*direction];

                if(isEmptyAt(nextCell.x, nextCell.y)) {
                    lst.add(nextCell);
                }else {
                    GameElement element = getElement(nextCell.x, nextCell.y);
                    if (element instanceof Pawn) {
                        Pawn pawn = (Pawn) element;
                        if (pawn.getColor() == color) {
                            lst.add(nextCell);
                        }
                        else if (getElements(nextCell.x, nextCell.y).size() == 1) {
                            lst.add(nextCell);
                        }
                    }
                }
            }
        }
        return lst;
    }

    public int getCellNumber(Point cell) {
        int number = -1;
        for(int i = 0; i<cells.length; i++)
            if(cells[i].equals(cell))
                number = i;
        return number;
    }
}
