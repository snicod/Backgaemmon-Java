package control;

import gamifier.control.Controller;
import gamifier.model.GameElement;
import gamifier.model.Model;
import model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BackgammonDecider1UnitTest {
    @Test
    public void testEatPawn() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(true);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {10, 7});
        possibleMoves.add(new int[] {10, 8});
        possibleMoves.add(new int[] {6, 3});
        possibleMoves.add(new int[] {6, 4});
        when(mg.generateMoves()).thenReturn(possibleMoves);

        BackgammonBoard board = mock(BackgammonBoard.class);
        Point[] cells = new Point[24];
        initCells(cells);
        when(board.getCells()).thenReturn(cells);

        Pawn p1 = mock(Pawn.class);
        when(p1.isInFinal()).thenReturn(false);
        Pawn p2 = mock(Pawn.class);
        when(p2.isInFinal()).thenReturn(false);

        Pawn p3 = mock(Pawn.class);
        when(p3.getColor()).thenReturn(Pawn.PAWN_RED);


        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        when(board.getElement(1, 1)).thenReturn(p1);
        when(board.getElements(1, 1)).thenReturn(liste1);
        when(board.getElement(1, 5)).thenReturn(p2);
        when(board.getElements(1, 5)).thenReturn(liste1);

        List<GameElement> liste3 = new ArrayList<>();
        liste3.add(p3);
        when(board.getElement(1, 7)).thenReturn(p3);
        when(board.getElements(1, 7)).thenReturn(liste3);

        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);
        when(board.getElement(0, 1)).thenReturn(null);
        when(board.getElements(0, 1)).thenReturn(liste2);
        when(board.getElement(0, 2)).thenReturn(null);
        when(board.getElements(0, 2)).thenReturn(liste2);
        when(board.getElement(0, 3)).thenReturn(null);
        when(board.getElements(0, 3)).thenReturn(liste2);
        when(board.getElement(0, 4)).thenReturn(null);
        when(board.getElements(0, 4)).thenReturn(liste2);
        when(board.getElement(0, 5)).thenReturn(null);
        when(board.getElements(0, 5)).thenReturn(liste2);
        when(board.getElement(0, 6)).thenReturn(null);
        when(board.getElements(0, 6)).thenReturn(liste2);
        when(board.getElement(0, 7)).thenReturn(null);
        when(board.getElements(0, 7)).thenReturn(liste2);
        when(board.getElement(0, 8)).thenReturn(null);
        when(board.getElements(0, 8)).thenReturn(liste2);
        when(board.getElement(0, 9)).thenReturn(null);
        when(board.getElements(0, 9)).thenReturn(liste2);
        when(board.getElement(0, 10)).thenReturn(null);
        when(board.getElements(0, 10)).thenReturn(liste2);
        when(board.getElement(0, 11)).thenReturn(null);
        when(board.getElements(0, 11)).thenReturn(liste2);
        when(board.getElement(1, 0)).thenReturn(null);
        when(board.getElements(1, 0)).thenReturn(liste2);
        when(board.getElement(1, 2)).thenReturn(null);
        when(board.getElements(1, 2)).thenReturn(liste2);
        when(board.getElement(1, 3)).thenReturn(null);
        when(board.getElements(1, 3)).thenReturn(liste2);
        when(board.getElement(1, 6)).thenReturn(null);
        when(board.getElements(1, 6)).thenReturn(liste2);
        when(board.getElement(1, 8)).thenReturn(null);
        when(board.getElements(1, 8)).thenReturn(liste2);
        when(board.getElement(1, 9)).thenReturn(null);
        when(board.getElements(1, 9)).thenReturn(liste2);
        when(board.getElement(1, 10)).thenReturn(null);
        when(board.getElements(1, 10)).thenReturn(liste2);
        when(board.getElement(1, 11)).thenReturn(null);
        when(board.getElements(1, 11)).thenReturn(liste2);

        when(stageModel.getBoard()).thenReturn(board);
        Model model = mock(Model.class);
        when(model.getGameStage()).thenReturn(stageModel);
        Controller control = mock(Controller.class);

        BackgammonDecider1 decider = new BackgammonDecider1(model, control, mg);
        decider.decide();

        assertEquals(6, decider.getMoveChoosed()[0]);
        assertEquals(4, decider.getMoveChoosed()[1]);

    }

        @Test
        public void testStack2() {
            BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
            Jail jail = mock(Jail.class);
            when(jail.isEmpty()).thenReturn(true);
            when(stageModel.getBlackJail()).thenReturn(jail);
            when(stageModel.getRedJail()).thenReturn(jail);
            MoveGenerator mg = mock(MoveGenerator.class);
            List<int[]> possibleMoves = new ArrayList<>();
            possibleMoves.add(new int[] {10, 7});
            possibleMoves.add(new int[] {10, 8});
            possibleMoves.add(new int[] {6, 3});
            possibleMoves.add(new int[] {6, 4});
            possibleMoves.add(new int[] {3, 0});
            possibleMoves.add(new int[] {3, 1});
            when(mg.generateMoves()).thenReturn(possibleMoves);

            BackgammonBoard board = mock(BackgammonBoard.class);
            Point[] cells = new Point[24];
            initCells(cells);
            when(board.getCells()).thenReturn(cells);

            Pawn p1 = mock(Pawn.class);
            when(p1.isInFinal()).thenReturn(false);
            Pawn p2 = mock(Pawn.class);
            when(p2.isInFinal()).thenReturn(false);
            Pawn p3 = mock(Pawn.class);
            when(p3.isInFinal()).thenReturn(true);


            List<GameElement> liste1 = new ArrayList<>();
            liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
            when(board.getElement(1, 1)).thenReturn(p1);
            when(board.getElements(1, 1)).thenReturn(liste1);
            when(board.getElement(1, 5)).thenReturn(p2);
            when(board.getElements(1, 5)).thenReturn(liste1);
            when(board.getElement(1, 7)).thenReturn(p3);
            when(board.getElements(1, 7)).thenReturn(liste1);

            List<GameElement> liste2 = new ArrayList<>();
            when(board.getElement(0, 0)).thenReturn(null);
            when(board.getElements(0, 0)).thenReturn(liste2);
            when(board.getElement(0, 1)).thenReturn(null);
            when(board.getElements(0, 1)).thenReturn(liste2);
            when(board.getElement(0, 2)).thenReturn(null);
            when(board.getElements(0, 2)).thenReturn(liste2);
            when(board.getElement(0, 3)).thenReturn(null);
            when(board.getElements(0, 3)).thenReturn(liste2);
            when(board.getElement(0, 4)).thenReturn(null);
            when(board.getElements(0, 4)).thenReturn(liste2);
            when(board.getElement(0, 5)).thenReturn(null);
            when(board.getElements(0, 5)).thenReturn(liste2);
            when(board.getElement(0, 6)).thenReturn(null);
            when(board.getElements(0, 6)).thenReturn(liste2);
            when(board.getElement(0, 7)).thenReturn(null);
            when(board.getElements(0, 7)).thenReturn(liste2);
            when(board.getElement(0, 8)).thenReturn(null);
            when(board.getElements(0, 8)).thenReturn(liste2);
            when(board.getElement(0, 9)).thenReturn(null);
            when(board.getElements(0, 9)).thenReturn(liste2);
            when(board.getElement(0, 10)).thenReturn(null);
            when(board.getElements(0, 10)).thenReturn(liste2);
            when(board.getElement(0, 11)).thenReturn(null);
            when(board.getElements(0, 11)).thenReturn(liste2);
            when(board.getElement(1, 0)).thenReturn(null);
            when(board.getElements(1, 0)).thenReturn(liste2);
            when(board.getElement(1, 2)).thenReturn(null);
            when(board.getElements(1, 2)).thenReturn(liste2);
            when(board.getElement(1, 3)).thenReturn(null);
            when(board.getElements(1, 3)).thenReturn(liste2);
            when(board.getElement(1, 6)).thenReturn(null);
            when(board.getElements(1, 6)).thenReturn(liste2);
            when(board.getElement(1, 8)).thenReturn(null);
            when(board.getElements(1, 8)).thenReturn(liste2);
            when(board.getElement(1, 9)).thenReturn(null);
            when(board.getElements(1, 9)).thenReturn(liste2);
            when(board.getElement(1, 10)).thenReturn(null);
            when(board.getElements(1, 10)).thenReturn(liste2);
            when(board.getElement(1, 11)).thenReturn(null);
            when(board.getElements(1, 11)).thenReturn(liste2);

            when(stageModel.getBoard()).thenReturn(board);
            Model model = mock(Model.class);
            when(model.getGameStage()).thenReturn(stageModel);
            Controller control = mock(Controller.class);

            BackgammonDecider1 decider = new BackgammonDecider1(model, control, mg);
            decider.decide();

            assertEquals(6, decider.getMoveChoosed()[0]);
            assertEquals(4, decider.getMoveChoosed()[1]);

        }

    @Test
    public void testMoveFurthest() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(true);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {10, 7});
        possibleMoves.add(new int[] {10, 8});
        possibleMoves.add(new int[] {6, 3});
        possibleMoves.add(new int[] {6, 4});
        when(mg.generateMoves()).thenReturn(possibleMoves);

        BackgammonBoard board = mock(BackgammonBoard.class);
        Point[] cells = new Point[24];
        initCells(cells);
        when(board.getCells()).thenReturn(cells);

        Pawn p1 = mock(Pawn.class);
        when(p1.isInFinal()).thenReturn(false);
        Pawn p2 = mock(Pawn.class);
        when(p2.isInFinal()).thenReturn(false);


        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        when(board.getElement(1, 1)).thenReturn(p1);
        when(board.getElements(1, 1)).thenReturn(liste1);
        when(board.getElement(1, 5)).thenReturn(p2);
        when(board.getElements(1, 5)).thenReturn(liste1);

        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);
        when(board.getElement(0, 1)).thenReturn(null);
        when(board.getElements(0, 1)).thenReturn(liste2);
        when(board.getElement(0, 2)).thenReturn(null);
        when(board.getElements(0, 2)).thenReturn(liste2);
        when(board.getElement(0, 3)).thenReturn(null);
        when(board.getElements(0, 3)).thenReturn(liste2);
        when(board.getElement(0, 4)).thenReturn(null);
        when(board.getElements(0, 4)).thenReturn(liste2);
        when(board.getElement(0, 5)).thenReturn(null);
        when(board.getElements(0, 5)).thenReturn(liste2);
        when(board.getElement(0, 6)).thenReturn(null);
        when(board.getElements(0, 6)).thenReturn(liste2);
        when(board.getElement(0, 7)).thenReturn(null);
        when(board.getElements(0, 7)).thenReturn(liste2);
        when(board.getElement(0, 8)).thenReturn(null);
        when(board.getElements(0, 8)).thenReturn(liste2);
        when(board.getElement(0, 9)).thenReturn(null);
        when(board.getElements(0, 9)).thenReturn(liste2);
        when(board.getElement(0, 10)).thenReturn(null);
        when(board.getElements(0, 10)).thenReturn(liste2);
        when(board.getElement(0, 11)).thenReturn(null);
        when(board.getElements(0, 11)).thenReturn(liste2);
        when(board.getElement(1, 0)).thenReturn(null);
        when(board.getElements(1, 0)).thenReturn(liste2);
        when(board.getElement(1, 2)).thenReturn(null);
        when(board.getElements(1, 2)).thenReturn(liste2);
        when(board.getElement(1, 3)).thenReturn(null);
        when(board.getElements(1, 3)).thenReturn(liste2);
        when(board.getElement(1, 6)).thenReturn(null);
        when(board.getElements(1, 6)).thenReturn(liste2);
        when(board.getElement(1, 7)).thenReturn(null);
        when(board.getElements(1, 7)).thenReturn(liste2);
        when(board.getElement(1, 8)).thenReturn(null);
        when(board.getElements(1, 8)).thenReturn(liste2);
        when(board.getElement(1, 9)).thenReturn(null);
        when(board.getElements(1, 9)).thenReturn(liste2);
        when(board.getElement(1, 10)).thenReturn(null);
        when(board.getElements(1, 10)).thenReturn(liste2);
        when(board.getElement(1, 11)).thenReturn(null);
        when(board.getElements(1, 11)).thenReturn(liste2);

        when(stageModel.getBoard()).thenReturn(board);
        Model model = mock(Model.class);
        when(model.getGameStage()).thenReturn(stageModel);
        Controller control = mock(Controller.class);

        BackgammonDecider1 decider = new BackgammonDecider1(model, control, mg);
        decider.decide();

        assertEquals(10, decider.getMoveChoosed()[0]);
        assertEquals(7, decider.getMoveChoosed()[1]);

    }

    @Test
    public void testMoveFurthestWithJail() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(false);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {25, 22});
        possibleMoves.add(new int[] {25, 23});

        when(mg.generateMoves()).thenReturn(possibleMoves);

        BackgammonBoard board = mock(BackgammonBoard.class);
        Point[] cells = new Point[24];
        initCells(cells);
        when(board.getCells()).thenReturn(cells);

        Pawn p1 = mock(Pawn.class);
        when(p1.isInFinal()).thenReturn(false);
        Pawn p2 = mock(Pawn.class);
        when(p2.isInFinal()).thenReturn(false);


        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        when(board.getElement(1, 1)).thenReturn(p1);
        when(board.getElements(1, 1)).thenReturn(liste1);
        when(board.getElement(1, 5)).thenReturn(p2);
        when(board.getElements(1, 5)).thenReturn(liste1);

        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);
        when(board.getElement(0, 1)).thenReturn(null);
        when(board.getElements(0, 1)).thenReturn(liste2);
        when(board.getElement(0, 2)).thenReturn(null);
        when(board.getElements(0, 2)).thenReturn(liste2);
        when(board.getElement(0, 3)).thenReturn(null);
        when(board.getElements(0, 3)).thenReturn(liste2);
        when(board.getElement(0, 4)).thenReturn(null);
        when(board.getElements(0, 4)).thenReturn(liste2);
        when(board.getElement(0, 5)).thenReturn(null);
        when(board.getElements(0, 5)).thenReturn(liste2);
        when(board.getElement(0, 6)).thenReturn(null);
        when(board.getElements(0, 6)).thenReturn(liste2);
        when(board.getElement(0, 7)).thenReturn(null);
        when(board.getElements(0, 7)).thenReturn(liste2);
        when(board.getElement(0, 8)).thenReturn(null);
        when(board.getElements(0, 8)).thenReturn(liste2);
        when(board.getElement(0, 9)).thenReturn(null);
        when(board.getElements(0, 9)).thenReturn(liste2);
        when(board.getElement(0, 10)).thenReturn(null);
        when(board.getElements(0, 10)).thenReturn(liste2);
        when(board.getElement(0, 11)).thenReturn(null);
        when(board.getElements(0, 11)).thenReturn(liste2);
        when(board.getElement(1, 0)).thenReturn(null);
        when(board.getElements(1, 0)).thenReturn(liste2);
        when(board.getElement(1, 2)).thenReturn(null);
        when(board.getElements(1, 2)).thenReturn(liste2);
        when(board.getElement(1, 3)).thenReturn(null);
        when(board.getElements(1, 3)).thenReturn(liste2);
        when(board.getElement(1, 6)).thenReturn(null);
        when(board.getElements(1, 6)).thenReturn(liste2);
        when(board.getElement(1, 7)).thenReturn(null);
        when(board.getElements(1, 7)).thenReturn(liste2);
        when(board.getElement(1, 8)).thenReturn(null);
        when(board.getElements(1, 8)).thenReturn(liste2);
        when(board.getElement(1, 9)).thenReturn(null);
        when(board.getElements(1, 9)).thenReturn(liste2);
        when(board.getElement(1, 10)).thenReturn(null);
        when(board.getElements(1, 10)).thenReturn(liste2);
        when(board.getElement(1, 11)).thenReturn(null);
        when(board.getElements(1, 11)).thenReturn(liste2);

        when(stageModel.getBoard()).thenReturn(board);
        Model model = mock(Model.class);
        when(model.getGameStage()).thenReturn(stageModel);
        Controller control = mock(Controller.class);

        BackgammonDecider1 decider = new BackgammonDecider1(model, control, mg);
        decider.decide();

        assertEquals(25, decider.getMoveChoosed()[0]);
        assertEquals(22, decider.getMoveChoosed()[1]);

    }

    private void initCells(Point[] cells) {
        for (int i = 0; i < 12; ++i) {
            Point p1 = mock(Point.class);
            p1.x = i;
            p1.y = 1;
            cells[11 - i]  = p1;
            Point p2 = mock(Point.class);
            p2.x = i;
            p2.y = 0;
            cells[12 + i]  = p2;
        }
    }
}
