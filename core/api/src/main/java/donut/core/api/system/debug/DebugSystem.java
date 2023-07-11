package donut.core.api.system.debug;

import wrapper.ecsystem.GameSystem;
import donut.core.api.system.render.Renderer;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.Entity;

public class DebugSystem extends GameSystem
{
    @Override
    public void onUpdate(EntitiesContainer entitiesContainer, Float deltaTime) {
        for(Entity entity : entitiesContainer.getEntitiesFor(Debugger.class))
        {
            Debugger debugger = entity.getComponent(Debugger.class);

            if(debugger.hasRenderer()) continue;

            Renderer renderer = entity.requireComponent(Renderer.class);
            renderer.addRenderCallback(debugger);
        }
    }
}
