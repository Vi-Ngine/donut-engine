package donut.core.wrapper.ECSystem;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Entity {
    private HashMap<String, Component> components = new HashMap<>();

    public Entity add(Component component)
    {
        components.put(component.getClass().toString(), component);
        return this;
    }

    public <T extends Component> T getComponent(Class<T> componentClass)
    {
        return (T)components.get(componentClass.toString());
    }

    public <T extends Component> T requireComponent(Class<T> componentClass)
    {
        if(getComponent(componentClass) != null)
        {
            return getComponent(componentClass);
        }

        Constructor<T> ctor;
        try {
            ctor = componentClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            return ctor.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
