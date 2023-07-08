package donut.core.api.system.dispose;
import donut.core.api.context.Application;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.Entity;
import donut.core.wrapper.ECSystem.EntitySystem;

public class EntityDisposeSystem extends EntitySystem {
    @Override
    public void onUpdate(EntitiesContainer entitiesContainer) {
        for (Entity entity : entitiesContainer.getEntitiesFor(Disposer.class)) {
            Application.Current().gameEngine().removeEntity(entity);
        }
    }
}
