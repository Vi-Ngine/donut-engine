package donut.core.wrapper.ECSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Engine {
    private EntitiesContainer entities = new EntitiesContainer();
    private Map<String, EntitySystem> systems = new HashMap<>();
    public void addEntity(Entity entity)
    {
        entities.add(entity);
    }

    public void removeEntity(Entity entity)
    {
        entities.remove(entity);
    }

    public void addSystem(EntitySystem system)
    {
        systems.put(system.getClass().toString(), system);
    }

    public <T extends  EntitySystem> T getSystem(Class<? extends EntitySystem> systemClass)
    {
        return (T)systems.get(systemClass.toString());
    }

    public void update(float delta)
    {
        for(EntitySystem system : systems.values())
        {
            system.onUpdate(entities);
        }
    }
}
