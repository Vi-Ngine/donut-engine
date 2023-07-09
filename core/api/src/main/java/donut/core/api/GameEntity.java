package donut.core.api;

import donut.core.api.component.ComponentCreator;
import donut.core.api.system.dispose.Disposer;
import donut.core.wrapper.ECSystem.Component;
import donut.core.wrapper.ECSystem.Entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
