package wrapper.ecsystem;

import donut.core.api.Transform;
import donut.core.api.system.dispose.Disposer;
import donut.core.wrapper.ECSystem.Entity;

public class GameEntity extends Entity
{
    public final Transform transform;

    public GameEntity()
    {
        transform = new Transform();
    }

    public void dispose()
    {
        add(new Disposer());
    }
}
