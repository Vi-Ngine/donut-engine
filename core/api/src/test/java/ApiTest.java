import wrapper.ecsystem.GameEngine;
import wrapper.ecsystem.GameEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ApiTest {
    @Test
    void test1()
    {
        GameEngine gameEngine = new GameEngine();
        GameEntity gameEntity = new GameEntity();
        gameEngine.addEntity(gameEntity);

        gameEntity.dispose();
        gameEngine.update(1f);

        Assertions.assertFalse(Arrays.asList(gameEngine.getEntities()).contains(gameEntity));
    }
}
