package donut.core.api.system.dispose;

import donut.core.api.GameEngine;
import donut.core.api.GameSystem;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.Entity;
import donut.core.wrapper.RESquest.IConsumeCallback;

public class EntityDisposeSystem extends GameSystem implements IConsumeCallback
{
    private GameEngine gameEngine;
    public EntityDisposeSystem()
    {
        getConsumer().postRequest(GameEngine.class, EntityDisposeSystem.class, this);
    }

    @Override
    public void onUpdate(EntitiesContainer entitiesContainer, Float detaTime) {
        for (Entity entity : entitiesContainer.getEntitiesFor(Disposer.class)) {
            gameEngine.removeEntity(entity);
        }
    }

    @Override
    public boolean consume(Object resource) {
        if(resource instanceof GameEngine)
        {
            this.gameEngine = (GameEngine) resource;
            return true;
        }
        return false;
    }
}
