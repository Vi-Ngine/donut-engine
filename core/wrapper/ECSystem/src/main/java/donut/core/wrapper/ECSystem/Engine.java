package donut.core.wrapper.ECSystem;

import donut.core.wrapper.RESquest.IResourceProvider;
import donut.core.wrapper.RESquest.ResourceProvider;

import java.util.HashMap;
import java.util.Map;

public class Engine<T> implements IResourceProvider {
    private EntitiesContainer entities = new EntitiesContainer();
    private Map<String, EntitySystem> systems = new HashMap<>();
    private ResourceProvider resourceProvider = new ResourceProvider();
    @Override
    public ResourceProvider getProvider() {
        return resourceProvider;
    }
    public void addEntity(Entity entity)
    {
        entities.add(entity);
    }

    public Entity[] getEntities()
    {
        return entities.toArray();
    }

    public void removeEntity(Entity entity)
    {
        entities.remove(entity);
    }

    public void addSystem(EntitySystem system)
    {
        systems.put(system.getClass().toString(), system);
        resourceProvider.addConsumer(system);
    }
    public EntitySystem[] getSystems()
    {
        return systems.values().toArray(new EntitySystem[0]);
    }

    public <T extends  EntitySystem> T getSystem(Class<? extends EntitySystem> systemClass)
    {
        return (T)systems.get(systemClass.toString());
    }

    public void update(T userData)
    {
        for(EntitySystem system : systems.values())
        {
            system.onUpdate(entities, userData);
        }
    }
}
