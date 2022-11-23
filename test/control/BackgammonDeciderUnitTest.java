package control;

import gamifier.control.Controller;
import gamifier.model.GameElement;
import gamifier.model.Model;
import model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BackgammonDeciderUnitTest {

    @Test
    public void testEatPawnInFinalSection() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(true);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {10, 8});
        possibleMoves.add(new int[] {10, 7});
        possibleMoves.add(new int[] {6, 4});
        possibleMoves.add(new int[] {6, 3});
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

        Pawn p4 = mock(Pawn.class);
        when(p4.getColor()).thenReturn(Pawn.PAWN_RED);

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        when(board.getElement(1, 1)).thenReturn(p1);
        when(board.getElements(1, 1)).thenReturn(liste1);
        when(board.getElement(1, 5)).thenReturn(p2);
        when(board.getElements(1, 5)).thenReturn(liste1);

        List<GameElement> liste3 = new ArrayList<>();
        liste3.add(p3);
        when(board.getElement(1, 4)).thenReturn(p3);
        when(board.getElements(1, 4)).thenReturn(liste3);
        when(board.getElement(1, 7)).thenReturn(p4);
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
        when(board.getElement(1, 11)).thenReturn(null);
        when(board.getElements(1, 11)).thenReturn(liste2);

        when(stageModel.getBoard()).thenReturn(board);
        Model model = mock(Model.class);
        when(model.getGameStage()).thenReturn(stageModel);
        Controller control = mock(Controller.class);

        BackgammonDecider decider = new BackgammonDecider(model, control, mg);
        decider.decide();

        assertEquals(6, decider.getMoveChoosed()[0]);
        assertEquals(4, decider.getMoveChoosed()[1]);

    }

    @Test
    public void testStack2InFinalSection() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(true);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {10, 8});
        possibleMoves.add(new int[] {10, 7});
        possibleMoves.add(new int[] {6, 4});
        possibleMoves.add(new int[] {6, 3});
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
        when(p2.isInFinal()).thenReturn(true);

        Pawn p4 = mock(Pawn.class);
        when(p4.isInFinal()).thenReturn(true);

        Pawn p5 = mock(Pawn.class);
        when(p5.getColor()).thenReturn(Pawn.PAWN_RED);

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(new Pawn(Pawn.PAWN_BLACK, stageModel));
        when(board.getElement(1, 1)).thenReturn(p1);
        when(board.getElements(1, 1)).thenReturn(liste1);
        when(board.getElement(1, 5)).thenReturn(p2);
        when(board.getElements(1, 5)).thenReturn(liste1);
        when(board.getElement(1, 8)).thenReturn(p3);
        when(board.getElements(1, 8)).thenReturn(liste1);

        List<GameElement> liste3 = new ArrayList<>();
        liste3.add(p4);
        liste3.add(p4);
        when(board.getElement(1, 7)).thenReturn(p4);
        when(board.getElements(1, 7)).thenReturn(liste3);

        List<GameElement> liste4 = new ArrayList<>();
        liste3.add(p5);
        when(board.getElement(1, 4)).thenReturn(p5);
        when(board.getElements(1, 4)).thenReturn(liste4);

        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);
        when(board.getElement(0, 1)).thenReturn(null);
        when(board.getElements(0, 1)).thenReturn(liste2);
        when(board.getElement(0, 2)).thenReturn(null);
        when(board.getElements(0, 2)).thenReturn(liste2);
        when(board.getElement(0, 3)).thenReturn(null);
        when(board.getElements(0, 3)).thenReturn(liste2);;
        when(board.getElement(0, 4)).thenReturn(null);
        when(board.getElements(0, 4)).thenReturn(liste2);;
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
        when(board.getElement(1, 9)).thenReturn(null);
        when(board.getElements(1, 9)).thenReturn(liste2);
        when(board.getElement(1, 11)).thenReturn(null);
        when(board.getElements(1, 11)).thenReturn(liste2);

        when(stageModel.getBoard()).thenReturn(board);
        Model model = mock(Model.class);
        when(model.getGameStage()).thenReturn(stageModel);
        Controller control = mock(Controller.class);

        BackgammonDecider decider = new BackgammonDecider(model, control, mg);
        decider.decide();

        assertEquals(6, decider.getMoveChoosed()[0]);
        assertEquals(3, decider.getMoveChoosed()[1]);

    }

    @Test
    public void testEscapeOppFinalSection() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(true);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {10, 8});
        possibleMoves.add(new int[] {10, 7});
        possibleMoves.add(new int[] {20, 18});
        possibleMoves.add(new int[] {20, 17});
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
        liste1.add(p1);
        when(board.getElement(1, 1)).thenReturn(p1);
        when(board.getElements(1, 1)).thenReturn(liste1);
        when(board.getElement(0, 8)).thenReturn(p2);
        when(board.getElements(0, 8)).thenReturn(liste1);

        List<GameElement> liste3 = new ArrayList<>();
        liste3.add(p3);
        when(board.getElement(1, 3)).thenReturn(p3);
        when(board.getElements(1, 3)).thenReturn(liste3);

        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);;
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
        when(board.getElements(0, 7)).thenReturn(liste2);;
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
        when(board.getElement(1, 4)).thenReturn(null);
        when(board.getElements(1, 4)).thenReturn(liste2);
        when(board.getElement(1, 5)).thenReturn(null);
        when(board.getElements(1, 5)).thenReturn(liste2);
        when(board.getElement(1, 6)).thenReturn(null);
        when(board.getElements(1, 6)).thenReturn(liste2);
        when(board.getElement(1, 7)).thenReturn(null);
        when(board.getElements(1, 7)).thenReturn(liste2);
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

        BackgammonDecider decider = new BackgammonDecider(model, control, mg);
        decider.decide();

        assertEquals(20, decider.getMoveChoosed()[0]);
        assertEquals(17, decider.getMoveChoosed()[1]);

    }

    @Test
    public void testRegularEatFurthest() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(true);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {5, 2});
        possibleMoves.add(new int[] {10, 7});
        possibleMoves.add(new int[] {20, 17});
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
        liste1.add(p1);
        when(board.getElement(1, 1)).thenReturn(p1);
        when(board.getElements(1, 1)).thenReturn(liste1);
        when(board.getElement(0, 8)).thenReturn(p2);
        when(board.getElements(0, 8)).thenReturn(liste1);
        when(board.getElement(1, 6)).thenReturn(p3);
        when(board.getElements(1, 6)).thenReturn(liste1);

        Pawn p4 = mock(Pawn.class);
        when(p4.getColor()).thenReturn(Pawn.PAWN_RED);
        Pawn p5 = mock(Pawn.class);
        when(p5.getColor()).thenReturn(Pawn.PAWN_RED);

        List<GameElement> liste3 = new ArrayList<>();
        liste3.add(p5);
        when(board.getElement(1, 4)).thenReturn(p4);
        when(board.getElements(1, 4)).thenReturn(liste3);
        when(board.getElement(0, 5)).thenReturn(p5);
        when(board.getElements(0, 5)).thenReturn(liste3);

        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);;
        when(board.getElement(0, 1)).thenReturn(null);
        when(board.getElements(0, 1)).thenReturn(liste2);
        when(board.getElement(0, 2)).thenReturn(null);
        when(board.getElements(0, 2)).thenReturn(liste2);
        when(board.getElement(0, 3)).thenReturn(null);
        when(board.getElements(0, 3)).thenReturn(liste2);
        when(board.getElement(0, 4)).thenReturn(null);
        when(board.getElements(0, 4)).thenReturn(liste2);
        when(board.getElement(0, 6)).thenReturn(null);
        when(board.getElements(0, 6)).thenReturn(liste2);
        when(board.getElement(0, 7)).thenReturn(null);
        when(board.getElements(0, 7)).thenReturn(liste2);;
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
        when(board.getElement(1, 5)).thenReturn(null);
        when(board.getElements(1, 5)).thenReturn(liste2);
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

        BackgammonDecider decider = new BackgammonDecider(model, control, mg);
        decider.decide();

        assertEquals(20, decider.getMoveChoosed()[0]);
        assertEquals(17, decider.getMoveChoosed()[1]);
    }

    @Test
    public void testRegularMove() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(true);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {5, 2});
        possibleMoves.add(new int[] {10, 7});
        possibleMoves.add(new int[] {17, 14});
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
        liste1.add(p1);
        when(board.getElement(1, 1)).thenReturn(p1);
        when(board.getElements(1, 1)).thenReturn(liste1);
        when(board.getElement(0, 5)).thenReturn(p2);
        when(board.getElements(0, 5)).thenReturn(liste1);
        when(board.getElement(1, 6)).thenReturn(p3);
        when(board.getElements(1, 6)).thenReturn(liste1);


        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);;
        when(board.getElement(0, 1)).thenReturn(null);
        when(board.getElements(0, 1)).thenReturn(liste2);
        when(board.getElement(0, 2)).thenReturn(null);
        when(board.getElements(0, 2)).thenReturn(liste2);
        when(board.getElement(0, 3)).thenReturn(null);
        when(board.getElements(0, 3)).thenReturn(liste2);
        when(board.getElement(0, 4)).thenReturn(null);
        when(board.getElements(0, 4)).thenReturn(liste2);
        when(board.getElement(0, 6)).thenReturn(null);
        when(board.getElements(0, 6)).thenReturn(liste2);
        when(board.getElement(0, 7)).thenReturn(null);
        when(board.getElements(0, 7)).thenReturn(liste2);;
        when(board.getElement(0, 8)).thenReturn(null);
        when(board.getElements(0, 8)).thenReturn(liste2);;
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
        when(board.getElement(1, 4)).thenReturn(null);
        when(board.getElements(1, 4)).thenReturn(liste2);
        when(board.getElement(1, 5)).thenReturn(null);
        when(board.getElements(1, 5)).thenReturn(liste2);
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

        BackgammonDecider decider = new BackgammonDecider(model, control, mg);
        decider.decide();

        assertEquals(10, decider.getMoveChoosed()[0]);
        assertEquals(7, decider.getMoveChoosed()[1]);
    }

    @Test
    public void testMoveFinalSection() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(true);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {5, 1});
        possibleMoves.add(new int[] {3, -2});
        possibleMoves.add(new int[] {1, -2});
        when(mg.generateMoves()).thenReturn(possibleMoves);

        BackgammonBoard board = mock(BackgammonBoard.class);
        Point[] cells = new Point[24];
        initCells(cells);
        when(board.getCells()).thenReturn(cells);

        Pawn p1 = mock(Pawn.class);
        when(p1.isInFinal()).thenReturn(true);
        Pawn p2 = mock(Pawn.class);
        when(p2.isInFinal()).thenReturn(true);
        Pawn p3 = mock(Pawn.class);
        when(p3.isInFinal()).thenReturn(true);

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(p1);
        when(board.getElement(1, 8)).thenReturn(p1);
        when(board.getElements(1, 8)).thenReturn(liste1);
        when(board.getElement(1, 10)).thenReturn(p2);
        when(board.getElements(1, 10)).thenReturn(liste1);
        when(board.getElement(1, 6)).thenReturn(p3);
        when(board.getElements(1, 6)).thenReturn(liste1);


        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);;
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
        when(board.getElements(0, 7)).thenReturn(liste2);;
        when(board.getElement(0, 8)).thenReturn(null);
        when(board.getElements(0, 8)).thenReturn(liste2);;
        when(board.getElement(0, 9)).thenReturn(null);
        when(board.getElements(0, 9)).thenReturn(liste2);
        when(board.getElement(0, 10)).thenReturn(null);
        when(board.getElements(0, 10)).thenReturn(liste2);
        when(board.getElement(0, 11)).thenReturn(null);
        when(board.getElements(0, 11)).thenReturn(liste2);
        when(board.getElement(1, 0)).thenReturn(null);
        when(board.getElements(1, 0)).thenReturn(liste2);
        when(board.getElement(1, 1)).thenReturn(null);
        when(board.getElements(1, 1)).thenReturn(liste2);
        when(board.getElement(1, 2)).thenReturn(null);
        when(board.getElements(1, 2)).thenReturn(liste2);
        when(board.getElement(1, 3)).thenReturn(null);
        when(board.getElements(1, 3)).thenReturn(liste2);
        when(board.getElement(1, 4)).thenReturn(null);
        when(board.getElements(1, 4)).thenReturn(liste2);
        when(board.getElement(1, 5)).thenReturn(null);
        when(board.getElements(1, 5)).thenReturn(liste2);
        when(board.getElement(1, 7)).thenReturn(null);
        when(board.getElements(1, 7)).thenReturn(liste2);
        when(board.getElement(1, 9)).thenReturn(null);
        when(board.getElements(1, 9)).thenReturn(liste2);
        when(board.getElement(1, 11)).thenReturn(null);
        when(board.getElements(1, 11)).thenReturn(liste2);

        when(stageModel.getBoard()).thenReturn(board);
        Model model = mock(Model.class);
        when(model.getGameStage()).thenReturn(stageModel);
        Controller control = mock(Controller.class);

        BackgammonDecider decider = new BackgammonDecider(model, control, mg);
        decider.decide();

        assertEquals(3, decider.getMoveChoosed()[0]);
        assertEquals(-2, decider.getMoveChoosed()[1]);
    }

    @Test
    public void testMoveInJail() {
        BackgammonStageModel stageModel = mock(BackgammonStageModel.class);
        Jail jail = mock(Jail.class);
        when(jail.isEmpty()).thenReturn(false);
        when(stageModel.getBlackJail()).thenReturn(jail);
        when(stageModel.getRedJail()).thenReturn(jail);
        MoveGenerator mg = mock(MoveGenerator.class);
        List<int[]> possibleMoves = new ArrayList<>();
        possibleMoves.add(new int[] {24, 20});
        possibleMoves.add(new int[] {14, 10});
        possibleMoves.add(new int[] {24, 23});
        possibleMoves.add(new int[] {14, 13});
        when(mg.generateMoves()).thenReturn(possibleMoves);

        BackgammonBoard board = mock(BackgammonBoard.class);
        Point[] cells = new Point[24];
        initCells(cells);
        when(board.getCells()).thenReturn(cells);

        Pawn p1 = mock(Pawn.class);
        when(p1.isInFinal()).thenReturn(true);
        Pawn p2 = mock(Pawn.class);
        when(p2.getColor()).thenReturn(Pawn.PAWN_RED);

        List<GameElement> liste1 = new ArrayList<>();
        liste1.add(p1);
        when(board.getElement(0, 2)).thenReturn(p1);
        when(board.getElements(0, 2)).thenReturn(liste1);

        List<GameElement> liste3 = new ArrayList<>();
        liste3.add(p2);
        when(board.getElement(1, 1)).thenReturn(p2);
        when(board.getElements(1, 1)).thenReturn(liste1);

        List<GameElement> liste2 = new ArrayList<>();
        when(board.getElement(0, 0)).thenReturn(null);
        when(board.getElements(0, 0)).thenReturn(liste2);;
        when(board.getElement(0, 1)).thenReturn(null);
        when(board.getElements(0, 1)).thenReturn(liste2);
        when(board.getElement(0, 3)).thenReturn(null);
        when(board.getElements(0, 3)).thenReturn(liste2);
        when(board.getElement(0, 4)).thenReturn(null);
        when(board.getElements(0, 4)).thenReturn(liste2);
        when(board.getElement(0, 5)).thenReturn(null);
        when(board.getElements(0, 5)).thenReturn(liste2);
        when(board.getElement(0, 6)).thenReturn(null);
        when(board.getElements(0, 6)).thenReturn(liste2);
        when(board.getElement(0, 7)).thenReturn(null);
        when(board.getElements(0, 7)).thenReturn(liste2);;
        when(board.getElement(0, 8)).thenReturn(null);
        when(board.getElements(0, 8)).thenReturn(liste2);;
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
        when(board.getElement(1, 4)).thenReturn(null);
        when(board.getElements(1, 4)).thenReturn(liste2);
        when(board.getElement(1, 5)).thenReturn(null);
        when(board.getElements(1, 5)).thenReturn(liste2);
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

        BackgammonDecider decider = new BackgammonDecider(model, control, mg);
        decider.decide();

        assertEquals(24, decider.getMoveChoosed()[0]);
        assertEquals(20, decider.getMoveChoosed()[1]);
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
