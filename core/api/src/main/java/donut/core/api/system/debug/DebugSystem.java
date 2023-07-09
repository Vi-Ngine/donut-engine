package donut.core.api.system.debug;

import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.render.Renderer;
import donut.core.api.system.render.RendererCreator;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.Entity;
import donut.core.wrapper.ECSystem.EntitySystem;

public class DebugSystem extends EntitySystem
{
    @Override
    public void onUpdate(EntitiesContainer entitiesContainer, Object userData) {
        for(Entity entity : entitiesContainer.getEntitiesFor(Debugger.class))
        {
            Debugger debugger = entity.getComponent(Debugger.class);

            if(debugger.hasRenderer()) continue;

            Renderer renderer = entity.requireComponent(Renderer.class);
            renderer.addRenderCallback(debugger);
        }
    }
}
