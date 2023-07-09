package donut.core.api;

import donut.core.api.system.debug.DebugSystem;
import donut.core.api.system.dispose.EntityDisposeSystem;
import donut.core.api.system.event.EntityEventSystem;
import donut.core.api.system.input.InputSystem;
import donut.core.api.system.physic.PhysicSystem;
import donut.core.api.system.render.RenderSystem;
import donut.core.wrapper.ECSystem.Engine;
import donut.core.wrapper.ECSystem.Entity;
import donut.core.wrapper.ECSystem.EntitySystem;

public class GameEngine extends Engine
{
    public GameEngine()
    {
        addSystem(new InputSystem());
        addSystem(new PhysicSystem());
        addSystem(new EntityEventSystem());
        addSystem(new EntityDisposeSystem());
        addSystem(new RenderSystem());
        addSystem(new DebugSystem());
    }

    @Override
    public void addEntity(Entity entity)
    {
        super.addEntity(entity);
    }
}
