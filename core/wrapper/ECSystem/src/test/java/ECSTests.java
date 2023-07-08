import donut.core.wrapper.ECSystem.Engine;
import donut.core.wrapper.ECSystem.Entity;
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
}
