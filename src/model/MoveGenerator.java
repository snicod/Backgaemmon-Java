package model;

import gamifier.model.GameElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveGenerator {
    private List<int[]> possibleMoves;
    private BackgammonStageModel stageModel;
    private int color;
    private Jail jail;
    private Dice dice;
    private BackgammonBoard board;
    private Point[] cells;
    private List<Integer> colorCells;

    public MoveGenerator(BackgammonStageModel stageModel, int color, Dice dice) {
        this.stageModel = stageModel;
        this.color = color;
        this.dice = dice;
        this.possibleMoves = new ArrayList<>();
        if (color == Pawn.PAWN_BLACK) {
            jail = stageModel.getBlackJail();
        }
        else {
            jail = stageModel.getRedJail();
        }
        board = stageModel.getBoard();
        cells = board.getCells();

        colorCells = new ArrayList<>();
        for(int i = 0; i < 24; ++i) {
            GameElement elt = board.getElement(board.getCells()[i].y, board.getCells()[i].x);
            if (elt != null && ((Pawn) elt).getColor() == color)
                colorCells.add(i);
        }
    }

    public List<int[]> generateMoves() {
        if (!jail.isEmpty()) {
            genMovesJail();
        }
        else if (isInFinal()) {
            genMovesFinal();
        }
        else {
            genMovesDefault();
        }
        return possibleMoves;
    }



    private void genMovesJail() {
        for(int value : dice.getValuesToPlay()) {
            if (color == Pawn.PAWN_BLACK) {
                if (canMove(cells[24 - value].x, cells[24 - value].y))
                    possibleMoves.add(new int[] {24, 24 - value});
            }
            else {
                if (canMove(cells[value - 1].x, cells[value - 1].y))
                    possibleMoves.add(new int[] {-1, value - 1});
            }
        }
    }

    private void genMovesFinal() {
        for(int value : dice.getValuesToPlay()) {
            System.out.println(isTooHighValue(value));
            if (isTooHighValue(value)) {

                if (color == Pawn.PAWN_BLACK) {
                    for(int i = 5; i > -1; ++i) {
                        if (colorCells.contains(i)) {
                            possibleMoves.add(new int[]{i, -2});
                            break;
                        }
                    }
                }
                else {
                    for(int i = 18; i < 24; ++i) {
                        if (colorCells.contains(i)) {
                            possibleMoves.add(new int[]{i, 25});
                            break;
                        }
                    }
                }
            }

            else {
                if (color == Pawn.PAWN_BLACK) {
                    for(int i = 0; i < 6; ++i) {
                        if (i - value == -1) {
                            if (colorCells.contains(i))
                                possibleMoves.add(new int[]{i, -2});
                        }
                        else if (i - value > -1) {
                            if (colorCells.contains(i))
                                possibleMoves.add(new int[]{i, i - value});
                        }
                    }
                }
                else {
                    for(int i = 18; i < 24; ++i) {
                        if (i + value == 24) {
                            if (colorCells.contains(i))
                                possibleMoves.add(new int[]{i, 25});
                        }
                        else if (i + value < 24) {
                            if (colorCells.contains(i))
                                possibleMoves.add(new int[]{i, i + value});
                        }
                    }
                }
            }
        }
    }

    private void genMovesDefault() {
        for(int value : dice.getValuesToPlay()) {
            for(int i = 0; i < 24; ++i) {
                if (colorCells.contains(i)) {
                    if (color == Pawn.PAWN_BLACK) {
                        if (i - value >= 0 && canMove(cells[i - value].x, cells[i - value].y))
                            possibleMoves.add(new int[] {i, i - value});
                    }
                    else {
                        if (i + value <= 23 && canMove(cells[i + value].x, cells[i + value].y))
                            possibleMoves.add(new int[] {i, i + value});
                    }
                }
            }
        }
    }

    private boolean canMove(int x, int y) {
        GameElement element = board.getElement(y, x);
        if (element == null) {
            return true;
        }
        else if (element instanceof Pawn) {
            Pawn pawn = (Pawn) element;
            if (pawn.getColor() == color) {
                return true;
            }
            else if (board.getElements(y, x).size() < 2) {
                return true;
            }
        }
        return false;
    }

    private boolean isInFinal() {
        if (!jail.isEmpty())
            return false;
        if (color == Pawn.PAWN_BLACK) {
            Pawn[] blackPawns = stageModel.getBlackPawns();
            for (Pawn blackPawn : blackPawns) {
                if (!blackPawn.isInFinal())
                    return false;
            }
        } else {
            Pawn[] redPawns = stageModel.getRedPawns();
            for (Pawn redPawn : redPawns) {
                if (!redPawn.isInFinal())
                    return false;
            }
        }
        return true;
    }

    private boolean isOnCell(int x, int y) {
        GameElement element = board.getElement(y, x);
        if (element instanceof Pawn) {
            Pawn pawn = (Pawn) element;
            if (pawn.getColor() == color)
                return true;
        }
        return false;
    }

    private boolean isTooHighValue(int value) {
        if (color == Pawn.PAWN_BLACK) {
            for (int i = value - 1; i < 6; ++i) {
                System.out.println(cells[i]);
                if (isOnCell(cells[i].x, cells[i].y))
                    return false;
            }
        }
        else {
            for (int i = 18; i < 24 - value; ++i) {
                System.out.println(cells[i]);

                if (isOnCell(cells[i].x, cells[i].y))
                    return false;
            }
        }
        return true;
    }

    public List<int[]> getDistinctMoves() {
        List<int[]> distinctMoves = new ArrayList<>();
        for (int[] possibleMove : possibleMoves) {
            if (!containsMove(distinctMoves, possibleMove))
                distinctMoves.add(possibleMove);
        }
        return distinctMoves;
    }

    private boolean containsMove(List<int[]> distinctMoves, int[] move) {
        for (int[] distinctMove : distinctMoves) {
            if (distinctMove[0] == move[0] && distinctMove[1] == move[1])
                return true;
        }
        return false;
    }

}