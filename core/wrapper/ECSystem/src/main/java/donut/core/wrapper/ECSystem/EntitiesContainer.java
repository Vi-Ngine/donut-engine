package donut.core.wrapper.ECSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntitiesContainer
{
    private final Set<Entity> entities = new HashSet<>();

    public Entity[] getEntitiesFor(Class<? extends Component> componentClass)
    {
        List<Entity> entityList = new ArrayList<>();
        for(Entity entity : entities)
        {
            if(entity.getComponent(componentClass) != null)
            {
                entityList.add(entity);
            }
        }
        return entityList.toArray(new Entity[0]);
    }

    public EntitiesContainer add(Entity entity)
    {
        entities.add(entity);
        return this;
    }

    public EntitiesContainer remove(Entity entity)
    {
        entities.remove(entity);
        return this;
    }
}
