import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.Entity;
import donut.core.wrapper.ECSystem.EntitySystem;
import org.junit.jupiter.api.Assertions;

public class PrintTextSystem extends EntitySystem {
    @Override
    public void onUpdate(EntitiesContainer entitiesContainer) {
        for(Entity entity : entitiesContainer.getEntitiesFor(TextComp.class))
        {
            TextComp text =  entity.getComponent(TextComp.class);
            Assertions.assertEquals("MyText", text.getText());
        }
    }
}
