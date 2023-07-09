package donut.core.wrapper.RESquest;

public class ResourceRequest<T> {
    public final Class<T> resourceClass;
    public final IConsumeCallback<T> consumeCallback;
    public ResourceRequest(Class<T> resourceClass, IConsumeCallback<T> consumeCallback)
    {
        this.resourceClass = resourceClass;
        this.consumeCallback = consumeCallback;
    }
}
