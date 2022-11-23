package model;

import gamifier.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class BackgammonBoardUnitTest {
    @Test
    public void testGetCellNumberInGrid() {
        Point cell = new Point(1,7);
        BackgammonStageModel stageModel = new BackgammonStageModel("test", new Model());
        BackgammonBoard board = new BackgammonBoard(20, 20, stageModel);
        Assertions.assertEquals(5, board.getCellNumber(cell));
    }
    @Test
    public void testGetCellNumberOutOfGrid() {
        Point cell = new Point(2,3);
        BackgammonStageModel stageModel = new BackgammonStageModel("test", new Model());
        BackgammonBoard board = new BackgammonBoard(20, 20, stageModel);
        Assertions.assertEquals(-1, board.getCellNumber(cell));
    }

    @Test
    public void testComputeValidCells(){
        Point cell = new Point(1, 4);
        BackgammonStageModel stageModel = new BackgammonStageModel("test", new Model());
        BackgammonStageFactory stageFactory = new BackgammonStageFactory(stageModel);
        stageFactory.setup();
        BackgammonBoard board = stageModel.getBoard();
        Dice dice = mock(Dice.class);
        List<Integer> mockDicesValue = new ArrayList<>();
        mockDicesValue.add(2);
        mockDicesValue.add(4);
        mockDicesValue.add(7);
        Mockito.when(dice.getValuesOfDice()).thenReturn(mockDicesValue);
        List<Point> exeptedPoints = new ArrayList<>();
        exeptedPoints.add(new Point(1, 7));
        exeptedPoints.add(new Point(1, 9));
        Assertions.assertEquals(exeptedPoints, board.computeValidCells(dice, Pawn.PAWN_BLACK, cell));

    }
}
