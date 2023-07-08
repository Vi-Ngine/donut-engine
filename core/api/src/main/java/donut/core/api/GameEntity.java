package donut.core.api;

import donut.core.api.system.dispose.Disposer;
import donut.core.wrapper.ECSystem.Entity;

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

    public <C extends ComponentWrapper<C>, S extends EntitySystemWrapper<S>, Ctor extends ComponentCreator<C, S>> C requireComponent(Class<Ctor> componentCreatorClass)
    {
        ComponentCreator<C, S> componentCreator = null;
        try
        {
            componentCreator = componentCreatorClass.getConstructor().newInstance();
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        } catch (InstantiationException e)
        {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

        C component = getComponent(componentCreator.getComponentClass());

        if(component == null)
        {
            component = componentCreator.createDefault(getEngine().getSystem(componentCreator.getSystemClass()), this);
            add(component);
        }

        return component;
    }
}
