package donut.core.api.system.event;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.EntitySystemWrapper;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.EntitySystem;

public class EntityEventSystem extends EntitySystem
{
    @Override
    public void onUpdate(EntitiesContainer entitiesContainer) {
        ImmutableArray<Entity> entites =
                getEngine().getEntitiesFor(Family.all(EntityCallbackReceiver.Wrapable.class).get());

        for(Entity entity : entites)
        {
            EntityCallbackReceiver eventComp = EntityCallbackReceiver.mapper.get(entity).getWrapper();
            eventComp.onUpdate(deltaTime);
        }
    }
}
