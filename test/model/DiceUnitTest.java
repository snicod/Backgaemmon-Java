package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiceUnitTest {

    @Test
    public void testGenerateValuesOfDiceSameValues() {
        BackgammonStageModel model = mock(BackgammonStageModel.class);
        Dice dice = new Dice(6, model);
        Random random = mock(Random.class);
        when(random.nextInt(6)).thenReturn(0);
        List<Integer> result = new ArrayList<>(Arrays.asList(1, 1, 1, 1));
        assertEquals(result, dice.generateValuesofDice(random));
        assertEquals(4, dice.getNumberOfDice());
    }

    @Test
    public void testGenerateValuesOfDiceDifferentValues() {
        BackgammonStageModel model = mock(BackgammonStageModel.class);
        Dice dice = new Dice(6, model);
        Random random = mock(Random.class);
        when(random.nextInt(6)).thenReturn(2, 0);
        List<Integer> result = new ArrayList<>(Arrays.asList(3, 1));
        assertEquals(result, dice.generateValuesofDice(random));
        assertEquals(2, dice.getNumberOfDice());
    }

    @Test
    public void testGenerateValuesOfDiceDecreasingOrder() {
        BackgammonStageModel model = mock(BackgammonStageModel.class);
        Dice dice = new Dice(6, model);
        Random random = mock(Random.class);
        when(random.nextInt(6)).thenReturn(1, 5);
        List<Integer> result = new ArrayList<>(Arrays.asList(6, 2));
        assertEquals(result, dice.generateValuesofDice(random));
    }

    @Test
    void testRemoveValueSameValues() {
        BackgammonStageModel model = mock(BackgammonStageModel.class);
        Dice dice = new Dice(6, model);
        Random random = mock(Random.class);
        when(random.nextInt(6)).thenReturn(0);
        List<Integer> result = new ArrayList<>(Arrays.asList(1, 1, 1));
        dice.generateValuesofDice(random);
        dice.removeValue(1);
        assertEquals(result, dice.getValuesToPlay());
    }

    @Test
    void testRemoveValueDifferentValues() {
        BackgammonStageModel model = mock(BackgammonStageModel.class);
        Dice dice = new Dice(6, model);
        Random random = mock(Random.class);
        when(random.nextInt(6)).thenReturn(0, 4);
        List<Integer> result = new ArrayList<>(Arrays.asList(5));
        dice.generateValuesofDice(random);
        dice.removeValue(1);
        assertEquals(result, dice.getValuesToPlay());
    }
}
