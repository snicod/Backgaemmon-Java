package model;

import gamifier.model.GameElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveGeneratorUnitTest {

    @Test
    public void testGenMovesDefault() {
        BackgammonStageModel stageModel = Mockito.mock(BackgammonStageModel.class);
        Jail jail = Mockito.mock(Jail.class);
        Mockito.when(jail.isEmpty()).thenReturn(true);
        Mockito.when(stageModel.getBlackJail()).thenReturn(jail);
        Mockito.when(stageModel.getRedJail()).thenReturn(jail);
        BackgammonBoard board = Mockito.mock(BackgammonBoard.class);
        Mockito.when(stageModel.getBoard()).thenReturn(board);
        Point[] cells = new Point[24];
        initCells(cells);
        Mockito.when(board.getCells()).thenReturn(cells);

        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.getValuesToPlay()).thenReturn(new ArrayList<>(Arrays.asList(6, 3)));

        Pawn p1 = Mockito.mock(Pawn.class);
        Mockito.when(p1.isInFinal()).thenReturn(true);
        Pawn p2 = Mockito.mock(Pawn.class);
        Mockito.when(p2.isInFinal()).thenReturn(false);

        Mockito.when(stageModel.getBlackPawns()).thenReturn(new Pawn[] {p1, p2});
        Mockito.when(stageModel.getRedPawns()).thenReturn(new Pawn[] {p1, p2});

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        Mockito.when(board.getElement(1, 8)).thenReturn(p1);
        Mockito.when(board.getElements(1, 8)).thenReturn(liste1);
        Mockito.when(board.getElement(0, 4)).thenReturn(p2);
        Mockito.when(board.getElements(0, 4)).thenReturn(liste1);

        List<GameElement> liste2 = new ArrayList<>();
        Mockito.when(board.getElement(0, 0)).thenReturn(null);
        Mockito.when(board.getElements(0, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 1)).thenReturn(null);
        Mockito.when(board.getElements(0, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 2)).thenReturn(null);
        Mockito.when(board.getElements(0, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 3)).thenReturn(null);
        Mockito.when(board.getElements(0, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 5)).thenReturn(null);
        Mockito.when(board.getElements(0, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 6)).thenReturn(null);
        Mockito.when(board.getElements(0, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 7)).thenReturn(null);
        Mockito.when(board.getElements(0, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 8)).thenReturn(null);
        Mockito.when(board.getElements(0, 8)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 9)).thenReturn(null);
        Mockito.when(board.getElements(0, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 10)).thenReturn(null);
        Mockito.when(board.getElements(0, 10)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 11)).thenReturn(null);
        Mockito.when(board.getElements(0, 11)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 0)).thenReturn(null);
        Mockito.when(board.getElements(1, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 1)).thenReturn(null);
        Mockito.when(board.getElements(1, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 2)).thenReturn(null);
        Mockito.when(board.getElements(1, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 3)).thenReturn(null);
        Mockito.when(board.getElements(1, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 4)).thenReturn(null);
        Mockito.when(board.getElements(1, 4)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 5)).thenReturn(null);
        Mockito.when(board.getElements(1, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 6)).thenReturn(null);
        Mockito.when(board.getElements(1, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 7)).thenReturn(null);
        Mockito.when(board.getElements(1, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 9)).thenReturn(null);
        Mockito.when(board.getElements(1, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 10)).thenReturn(null);
        Mockito.when(board.getElements(1, 10)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 11)).thenReturn(null);
        Mockito.when(board.getElements(1, 11)).thenReturn(liste2);


        MoveGenerator mg = new MoveGenerator(stageModel, Pawn.PAWN_BLACK, dice);

        mg.generateMoves();

        List<int[]> result = new ArrayList<>();
        result.add(new int[] {16, 10});
        result.add(new int[] {3, 0});
        result.add(new int[] {16, 13});

        Assertions.assertEquals(result.size(), mg.getDistinctMoves().size());

        for(int i = 0; i < result.size(); ++i) {
            Assertions.assertEquals(result.get(i)[0], mg.getDistinctMoves().get(i)[0]);
            Assertions.assertEquals(result.get(i)[1], mg.getDistinctMoves().get(i)[1]);
        }

    }

    @Test
    public void testGenMovesDefaultOpponentPawn() {
        BackgammonStageModel stageModel = Mockito.mock(BackgammonStageModel.class);
        Jail jail = Mockito.mock(Jail.class);
        Mockito.when(jail.isEmpty()).thenReturn(true);
        Mockito.when(stageModel.getBlackJail()).thenReturn(jail);
        Mockito.when(stageModel.getRedJail()).thenReturn(jail);
        BackgammonBoard board = Mockito.mock(BackgammonBoard.class);
        Mockito.when(stageModel.getBoard()).thenReturn(board);
        Point[] cells = new Point[24];
        initCells(cells);
        Mockito.when(board.getCells()).thenReturn(cells);

        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.getValuesToPlay()).thenReturn(new ArrayList<>(Arrays.asList(3)));

        Pawn p1 = Mockito.mock(Pawn.class);
        Mockito.when(p1.isInFinal()).thenReturn(true);
        Pawn p2 = Mockito.mock(Pawn.class);
        Mockito.when(p2.isInFinal()).thenReturn(false);

        Pawn p3 = Mockito.mock(Pawn.class);
        Mockito.when(p3.getColor()).thenReturn(Pawn.PAWN_RED);

        Pawn p4 = Mockito.mock(Pawn.class);
        Mockito.when(p4.getColor()).thenReturn(Pawn.PAWN_RED);

        Mockito.when(stageModel.getBlackPawns()).thenReturn(new Pawn[] {p1, p2});
        Mockito.when(stageModel.getRedPawns()).thenReturn(new Pawn[] {p3, p4});

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        Mockito.when(board.getElement(1, 8)).thenReturn(p1);
        Mockito.when(board.getElements(1, 8)).thenReturn(liste1);
        Mockito.when(board.getElement(0, 4)).thenReturn(p2);
        Mockito.when(board.getElements(0, 4)).thenReturn(liste1);

        List<GameElement> liste3 = new ArrayList<>();
        liste3.add(p3);
        Mockito.when(board.getElement(0, 1)).thenReturn(p3);
        Mockito.when(board.getElements(0, 1)).thenReturn(liste3);
        List<GameElement> liste4 = new ArrayList<>();
        liste4.add(p4);
        liste4.add(p4);
        Mockito.when(board.getElement(1, 11)).thenReturn(p4);
        Mockito.when(board.getElements(1, 11)).thenReturn(liste4);

        List<GameElement> liste2 = new ArrayList<>();
        Mockito.when(board.getElement(0, 0)).thenReturn(null);
        Mockito.when(board.getElements(0, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 2)).thenReturn(null);
        Mockito.when(board.getElements(0, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 3)).thenReturn(null);
        Mockito.when(board.getElements(0, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 5)).thenReturn(null);
        Mockito.when(board.getElements(0, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 6)).thenReturn(null);
        Mockito.when(board.getElements(0, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 7)).thenReturn(null);
        Mockito.when(board.getElements(0, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 8)).thenReturn(null);
        Mockito.when(board.getElements(0, 8)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 9)).thenReturn(null);
        Mockito.when(board.getElements(0, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 10)).thenReturn(null);
        Mockito.when(board.getElements(0, 10)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 11)).thenReturn(null);
        Mockito.when(board.getElements(0, 11)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 0)).thenReturn(null);
        Mockito.when(board.getElements(1, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 1)).thenReturn(null);
        Mockito.when(board.getElements(1, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 2)).thenReturn(null);
        Mockito.when(board.getElements(1, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 3)).thenReturn(null);
        Mockito.when(board.getElements(1, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 4)).thenReturn(null);
        Mockito.when(board.getElements(1, 4)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 5)).thenReturn(null);
        Mockito.when(board.getElements(1, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 6)).thenReturn(null);
        Mockito.when(board.getElements(1, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 7)).thenReturn(null);
        Mockito.when(board.getElements(1, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 9)).thenReturn(null);
        Mockito.when(board.getElements(1, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 10)).thenReturn(null);
        Mockito.when(board.getElements(1, 10)).thenReturn(liste2);


        MoveGenerator mg = new MoveGenerator(stageModel, Pawn.PAWN_BLACK, dice);

        mg.generateMoves();

        List<int[]> result = new ArrayList<>();
        result.add(new int[] {16, 13});

        Assertions.assertEquals(result.size(), mg.getDistinctMoves().size());

        for(int i = 0; i < result.size(); ++i) {
            Assertions.assertEquals(result.get(i)[0], mg.getDistinctMoves().get(i)[0]);
            Assertions.assertEquals(result.get(i)[1], mg.getDistinctMoves().get(i)[1]);
        }

    }

    @Test
    public void testGenMovesJail() {
        BackgammonStageModel stageModel = Mockito.mock(BackgammonStageModel.class);
        Jail jail = Mockito.mock(Jail.class);
        Mockito.when(jail.isEmpty()).thenReturn(false);
        Mockito.when(stageModel.getBlackJail()).thenReturn(jail);
        Mockito.when(stageModel.getRedJail()).thenReturn(jail);
        BackgammonBoard board = Mockito.mock(BackgammonBoard.class);
        Mockito.when(stageModel.getBoard()).thenReturn(board);
        Point[] cells = new Point[24];
        initCells(cells);
        Mockito.when(board.getCells()).thenReturn(cells);

        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.getValuesToPlay()).thenReturn(new ArrayList<>(Arrays.asList(2)));

        Pawn p1 = Mockito.mock(Pawn.class);
        Mockito.when(p1.isInFinal()).thenReturn(true);
        Pawn p2 = Mockito.mock(Pawn.class);
        Mockito.when(p2.isInFinal()).thenReturn(false);

        Mockito.when(stageModel.getBlackPawns()).thenReturn(new Pawn[] {p1, p2});
        Mockito.when(stageModel.getRedPawns()).thenReturn(new Pawn[] {p1, p2});

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        Mockito.when(board.getElement(1, 8)).thenReturn(p1);
        Mockito.when(board.getElements(1, 8)).thenReturn(liste1);

        List<GameElement> liste2 = new ArrayList<>();
        Mockito.when(board.getElement(0, 0)).thenReturn(null);
        Mockito.when(board.getElements(0, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 1)).thenReturn(null);
        Mockito.when(board.getElements(0, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 2)).thenReturn(null);
        Mockito.when(board.getElements(0, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 3)).thenReturn(null);
        Mockito.when(board.getElements(0, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 4)).thenReturn(null);
        Mockito.when(board.getElements(0, 4)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 5)).thenReturn(null);
        Mockito.when(board.getElements(0, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 6)).thenReturn(null);
        Mockito.when(board.getElements(0, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 7)).thenReturn(null);
        Mockito.when(board.getElements(0, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 8)).thenReturn(null);
        Mockito.when(board.getElements(0, 8)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 9)).thenReturn(null);
        Mockito.when(board.getElements(0, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 10)).thenReturn(null);
        Mockito.when(board.getElements(0, 10)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 11)).thenReturn(null);
        Mockito.when(board.getElements(0, 11)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 0)).thenReturn(null);
        Mockito.when(board.getElements(1, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 1)).thenReturn(null);
        Mockito.when(board.getElements(1, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 2)).thenReturn(null);
        Mockito.when(board.getElements(1, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 3)).thenReturn(null);
        Mockito.when(board.getElements(1, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 4)).thenReturn(null);
        Mockito.when(board.getElements(1, 4)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 5)).thenReturn(null);
        Mockito.when(board.getElements(1, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 6)).thenReturn(null);
        Mockito.when(board.getElements(1, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 7)).thenReturn(null);
        Mockito.when(board.getElements(1, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 9)).thenReturn(null);
        Mockito.when(board.getElements(1, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 10)).thenReturn(null);
        Mockito.when(board.getElements(1, 10)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 11)).thenReturn(null);
        Mockito.when(board.getElements(1, 11)).thenReturn(liste2);


        MoveGenerator mg = new MoveGenerator(stageModel, Pawn.PAWN_BLACK, dice);

        mg.generateMoves();

        List<int[]> result = new ArrayList<>();
        result.add(new int[] {24, 22});

        Assertions.assertEquals(result.size(), mg.getDistinctMoves().size());

        for(int i = 0; i < result.size(); ++i) {
            Assertions.assertEquals(result.get(i)[0], mg.getDistinctMoves().get(i)[0]);
            Assertions.assertEquals(result.get(i)[1], mg.getDistinctMoves().get(i)[1]);
        }

    }

    @Test
    public void testGenMovesFinalDefault() {
        BackgammonStageModel stageModel = Mockito.mock(BackgammonStageModel.class);
        Jail jail = Mockito.mock(Jail.class);
        Mockito.when(jail.isEmpty()).thenReturn(true);
        Mockito.when(stageModel.getBlackJail()).thenReturn(jail);
        Mockito.when(stageModel.getRedJail()).thenReturn(jail);
        BackgammonBoard board = Mockito.mock(BackgammonBoard.class);
        Mockito.when(stageModel.getBoard()).thenReturn(board);
        Point[] cells = new Point[24];
        initCells(cells);
        Mockito.when(board.getCells()).thenReturn(cells);

        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.getValuesToPlay()).thenReturn(new ArrayList<>(Arrays.asList(6, 4)));

        Pawn p1 = Mockito.mock(Pawn.class);
        Mockito.when(p1.isInFinal()).thenReturn(true);
        Pawn p2 = Mockito.mock(Pawn.class);
        Mockito.when(p2.isInFinal()).thenReturn(true);

        Mockito.when(stageModel.getBlackPawns()).thenReturn(new Pawn[] {p1, p2});
        Mockito.when(stageModel.getRedPawns()).thenReturn(new Pawn[] {p1, p2});

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        Mockito.when(board.getElement(1, 8)).thenReturn(p1);
        Mockito.when(board.getElements(1, 8)).thenReturn(liste1);
        Mockito.when(board.getElement(1, 6)).thenReturn(p1);
        Mockito.when(board.getElements(1, 6)).thenReturn(liste1);

        List<GameElement> liste2 = new ArrayList<>();
        Mockito.when(board.getElement(0, 0)).thenReturn(null);
        Mockito.when(board.getElements(0, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 1)).thenReturn(null);
        Mockito.when(board.getElements(0, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 2)).thenReturn(null);
        Mockito.when(board.getElements(0, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 3)).thenReturn(null);
        Mockito.when(board.getElements(0, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 4)).thenReturn(null);
        Mockito.when(board.getElements(0, 4)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 5)).thenReturn(null);
        Mockito.when(board.getElements(0, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 6)).thenReturn(null);
        Mockito.when(board.getElements(0, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 7)).thenReturn(null);
        Mockito.when(board.getElements(0, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 8)).thenReturn(null);
        Mockito.when(board.getElements(0, 8)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 9)).thenReturn(null);
        Mockito.when(board.getElements(0, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 10)).thenReturn(null);
        Mockito.when(board.getElements(0, 10)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 11)).thenReturn(null);
        Mockito.when(board.getElements(0, 11)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 0)).thenReturn(null);
        Mockito.when(board.getElements(1, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 1)).thenReturn(null);
        Mockito.when(board.getElements(1, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 2)).thenReturn(null);
        Mockito.when(board.getElements(1, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 3)).thenReturn(null);
        Mockito.when(board.getElements(1, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 4)).thenReturn(null);
        Mockito.when(board.getElements(1, 4)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 5)).thenReturn(null);
        Mockito.when(board.getElements(1, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 7)).thenReturn(null);
        Mockito.when(board.getElements(1, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 9)).thenReturn(null);
        Mockito.when(board.getElements(1, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 10)).thenReturn(null);
        Mockito.when(board.getElements(1, 10)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 11)).thenReturn(null);
        Mockito.when(board.getElements(1, 11)).thenReturn(liste2);


        MoveGenerator mg = new MoveGenerator(stageModel, Pawn.PAWN_BLACK, dice);

        mg.generateMoves();

        List<int[]> result = new ArrayList<>();
        result.add(new int[] {5, -2});
        result.add(new int[] {3, -2});
        result.add(new int[] {5, 1});

        Assertions.assertEquals(result.size(), mg.getDistinctMoves().size());

        for(int i = 0; i < result.size(); ++i) {
            Assertions.assertEquals(result.get(i)[0], mg.getDistinctMoves().get(i)[0]);
            Assertions.assertEquals(result.get(i)[1], mg.getDistinctMoves().get(i)[1]);
        }

    }

    @Test
    public void testGenMovesFinalTooHighValue() {
        BackgammonStageModel stageModel = Mockito.mock(BackgammonStageModel.class);
        Jail jail = Mockito.mock(Jail.class);
        Mockito.when(jail.isEmpty()).thenReturn(true);
        Mockito.when(stageModel.getBlackJail()).thenReturn(jail);
        Mockito.when(stageModel.getRedJail()).thenReturn(jail);
        BackgammonBoard board = Mockito.mock(BackgammonBoard.class);
        Mockito.when(stageModel.getBoard()).thenReturn(board);
        Point[] cells = new Point[24];
        initCells(cells);
        Mockito.when(board.getCells()).thenReturn(cells);

        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.getValuesToPlay()).thenReturn(new ArrayList<>(Arrays.asList(6, 4)));

        Pawn p1 = Mockito.mock(Pawn.class);
        Mockito.when(p1.isInFinal()).thenReturn(true);
        Pawn p2 = Mockito.mock(Pawn.class);
        Mockito.when(p2.isInFinal()).thenReturn(true);

        Mockito.when(stageModel.getBlackPawns()).thenReturn(new Pawn[] {p1, p2});
        Mockito.when(stageModel.getRedPawns()).thenReturn(new Pawn[] {p1, p2});

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        Mockito.when(board.getElement(1, 8)).thenReturn(p1);
        Mockito.when(board.getElements(1, 8)).thenReturn(liste1);
        Mockito.when(board.getElement(1, 10)).thenReturn(p1);
        Mockito.when(board.getElements(1, 10)).thenReturn(liste1);

        List<GameElement> liste2 = new ArrayList<>();
        Mockito.when(board.getElement(0, 0)).thenReturn(null);
        Mockito.when(board.getElements(0, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 1)).thenReturn(null);
        Mockito.when(board.getElements(0, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 2)).thenReturn(null);
        Mockito.when(board.getElements(0, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 3)).thenReturn(null);
        Mockito.when(board.getElements(0, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 4)).thenReturn(null);
        Mockito.when(board.getElements(0, 4)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 5)).thenReturn(null);
        Mockito.when(board.getElements(0, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 6)).thenReturn(null);
        Mockito.when(board.getElements(0, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 7)).thenReturn(null);
        Mockito.when(board.getElements(0, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 8)).thenReturn(null);
        Mockito.when(board.getElements(0, 8)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 9)).thenReturn(null);
        Mockito.when(board.getElements(0, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 10)).thenReturn(null);
        Mockito.when(board.getElements(0, 10)).thenReturn(liste2);
        Mockito.when(board.getElement(0, 11)).thenReturn(null);
        Mockito.when(board.getElements(0, 11)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 0)).thenReturn(null);
        Mockito.when(board.getElements(1, 0)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 1)).thenReturn(null);
        Mockito.when(board.getElements(1, 1)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 2)).thenReturn(null);
        Mockito.when(board.getElements(1, 2)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 3)).thenReturn(null);
        Mockito.when(board.getElements(1, 3)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 4)).thenReturn(null);
        Mockito.when(board.getElements(1, 4)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 5)).thenReturn(null);
        Mockito.when(board.getElements(1, 5)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 6)).thenReturn(null);
        Mockito.when(board.getElements(1, 6)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 7)).thenReturn(null);
        Mockito.when(board.getElements(1, 7)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 9)).thenReturn(null);
        Mockito.when(board.getElements(1, 9)).thenReturn(liste2);
        Mockito.when(board.getElement(1, 11)).thenReturn(null);
        Mockito.when(board.getElements(1, 11)).thenReturn(liste2);


        MoveGenerator mg = new MoveGenerator(stageModel, Pawn.PAWN_BLACK, dice);

        mg.generateMoves();

        List<int[]> result = new ArrayList<>();
        result.add(new int[] {1, -2});
        result.add(new int[] {3, -2});

        Assertions.assertEquals(result.size(), mg.getDistinctMoves().size());

        for(int i = 0; i < result.size(); ++i) {
            Assertions.assertEquals(result.get(i)[0], mg.getDistinctMoves().get(i)[0]);
            Assertions.assertEquals(result.get(i)[1], mg.getDistinctMoves().get(i)[1]);
        }

    }

    private void initCells(Point[] cells) {
        for (int i = 0; i < 12; ++i) {
            Point p1 = Mockito.mock(Point.class);
            p1.x = i;
            p1.y = 1;
            cells[11 - i]  = p1;
            Point p2 = Mockito.mock(Point.class);
            p2.x = i;
            p2.y = 0;
            cells[12 + i]  = p2;
        }
    }
}
