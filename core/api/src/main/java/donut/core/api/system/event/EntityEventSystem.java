package donut.core.api.system.event;

import donut.core.api.GameSystem;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.Entity;

public class EntityEventSystem extends GameSystem
{
    @Override
    public void onUpdate(EntitiesContainer entitiesContainer, Float deltaTime) {
        for(Entity entity: entitiesContainer.getEntitiesFor(EntityCallbackReceiver.class))
        {
            EntityCallbackReceiver eventComp = entity.getComponent(EntityCallbackReceiver.class);
            eventComp.onUpdate(deltaTime);
        }
    }
}
