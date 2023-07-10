import donut.core.wrapper.ECSystem.Engine;
import donut.core.wrapper.ECSystem.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ECSTests {
    @Test
    void test1()
    {
        Engine engine = new Engine();

        Entity myEntity = new Entity();
        myEntity.add(new TextComp());

        engine.addSystem(new PrintTextSystem());
        engine.addEntity(myEntity);

        engine.update(1f);
    }

    @Test
    void requireCompTest()
    {
        Entity entity = new Entity();

        entity.requireComponent(TextComp.class);
        Assertions.assertNotNull(entity.getComponent(TextComp.class));
    }

    @Test
    void requireCompTest2()
    {
        Entity entity = new Entity();

        boolean shouldBeTrue = false;

        try
        {
            entity.requireComponent(NonDefaultComp.class);
        }
        catch (RuntimeException e)
        {
            shouldBeTrue = true;
        }

        Assertions.assertTrue(shouldBeTrue);
    }
}
