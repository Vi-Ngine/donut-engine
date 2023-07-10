package donut.core.wrapper.ECSystem;

public abstract class EntitySystem<T> {
    public void onUpdate(EntitiesContainer entitiesContainer, T userData) {}
}
