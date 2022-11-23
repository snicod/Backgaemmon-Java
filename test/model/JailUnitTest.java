package model;

import gamifier.model.GameStageModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JailUnitTest {
    @Test
    public void testIncreaseAndDecreaseNbPawns() {
        GameStageModel model = Mockito.mock(GameStageModel.class);
        Jail jail = new Jail(0, 0, 0, model);
        Assertions.assertEquals(jail.getNbPawns(), 0);
        Assertions.assertTrue(jail.isEmpty());

        jail.increaseNbPawns();
        Assertions.assertFalse(jail.isEmpty());
        Assertions.assertEquals(jail.getNbPawns(), 1);

        jail.increaseNbPawns();
        Assertions.assertFalse(jail.isEmpty());
        Assertions.assertEquals(jail.getNbPawns(), 2);

        jail.decreaseNbPawns();
        Assertions.assertFalse(jail.isEmpty());
        Assertions.assertEquals(jail.getNbPawns(), 1);

        jail.decreaseNbPawns();
        Assertions.assertTrue(jail.isEmpty());
        Assertions.assertEquals(jail.getNbPawns(), 0);

        jail.decreaseNbPawns();
        Assertions.assertTrue(jail.isEmpty());
        Assertions.assertEquals(jail.getNbPawns(), 0);
    }
}
