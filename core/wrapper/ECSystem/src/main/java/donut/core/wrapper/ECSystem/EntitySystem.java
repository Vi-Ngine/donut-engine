package donut.core.wrapper.ECSystem;

import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceConsumer;

public abstract class EntitySystem<T> implements IResourceConsumer {
    private ResourceConsumer resourceConsumer = new ResourceConsumer();
    public void onUpdate(EntitiesContainer entitiesContainer, T userData) {}

    @Override
    public ResourceConsumer getConsumer() {
        return resourceConsumer;
    }
}
