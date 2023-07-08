package donut.core.wrapper.ECSystem;
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
}
