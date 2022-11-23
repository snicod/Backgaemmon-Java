package model;

import gamifier.model.DiceElement;
import gamifier.model.GameStageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice extends DiceElement {
    private List<Integer> values;
    private List<Integer> valuesToPlay;
    private int numberOfDice;

    public Dice(int nbOfFaces, GameStageModel gameStageModel) {
        super(nbOfFaces, gameStageModel);
        values = new ArrayList<>();
        numberOfDice = 0;
    }

    public List<Integer> generateValuesofDice(Random random) {
        int nb1 = random.nextInt(6) + 1;
        int nb2 = random.nextInt(6) + 1;
        if (nb1 > nb2) {
            values.add(nb1);
            values.add(nb2);
        }
        else {
            values.add(nb2);
            values.add(nb1);
        }
        numberOfDice = 2;
        if(nb1 == nb2) {
            values.add(nb1);
            values.add(nb1);
            numberOfDice = 4;
        }
        valuesToPlay = new ArrayList<>(values);
        return values;
    }

    public void removeValue(int value) {
        valuesToPlay.remove(new Integer(value));
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public List<Integer> getValuesOfDice() {
        return values;
    }

    public List<Integer> getValuesToPlay() {
        return valuesToPlay;
    }

}
