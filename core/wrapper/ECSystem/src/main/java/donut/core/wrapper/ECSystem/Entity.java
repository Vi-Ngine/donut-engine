package donut.core.wrapper.ECSystem;
import donut.core.wrapper.RESquest.ResourceProvider;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Entity {
    private HashMap<String, Component> components = new HashMap<>();
    private ResourceProvider provider = new ResourceProvider();

    public Entity add(Component component)
    {
        components.put(component.getClass().toString(), component);
        provider.addConsumer(component);
        return this;
    }

    public void addResource(Object resource, Class<?> scope)
    {
        provider.addResource(resource, scope);
    }

    public <T extends Component> T getComponent(Class<T> componentClass)
    {
        return (T)components.get(componentClass.toString());
    }

    public <T extends Component> T requireComponent(Class<T> componentClass)
    {
        T component = getComponent(componentClass);

        if(component != null)
        {
            return component;
        }

        Constructor<T> ctor;
        try {
            ctor = componentClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            component = ctor.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        add(component);
        return component;
    }
}
