package donut.core.api;

import donut.core.api.system.dispose.Disposer;
import donut.core.wrapper.ECSystem.Entity;

public abstract class GameEntity extends Entity
{
    public final Transform transform;

    public GameEntity()
    {
        transform = new Transform();
    }

    public void Dispose()
    {
        add(new Disposer());
    }
}
