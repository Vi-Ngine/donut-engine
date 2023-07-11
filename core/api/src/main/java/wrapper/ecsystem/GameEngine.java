package wrapper.ecsystem;

import donut.core.api.system.dispose.EntityDisposeSystem;
import donut.core.api.system.event.EntityEventSystem;
//import donut.core.api.system.input.InputSystem;
import donut.core.wrapper.ECSystem.Engine;
import donut.core.wrapper.ECSystem.Entity;

public class GameEngine extends Engine<Float>
{
    public GameEngine()
    {
        //addSystem(new InputSystem());
        //addSystem(new PhysicSystem());
        addSystem(new EntityEventSystem());
        addSystem(new EntityDisposeSystem());
        //addSystem(new RenderSystem());
        //addSystem(new DebugSystem());

        getProvider().addResource(this, GameSystem.class);
    }

    @Override
    public void addEntity(Entity entity)
    {
        super.addEntity(entity);
    }

    @Override
    public void update(Float deltaTime) {
        super.update(deltaTime);
    }
}
