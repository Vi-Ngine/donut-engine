package donut.core.wrapper.RESquest;

public class ResourceRequest<T> {
    enum RequestState
    {
        CREATE,
        RESOLVED
    }
    public final Class<T> resourceClass;
    private Class<?> receiverScope = Object.class;
    private final IConsumeCallback<T> consumeCallback;
    private RequestState state;
    private RunnableOne<ResourceRequest> requestResolvedListener;

    public RequestState getState()
    {
        return state;
    }

    protected void setState(RequestState state)
    {
        this.state = state;

        if(state == RequestState.RESOLVED)
        {
            requestResolvedListener.run(this);
        }
    }

    public boolean consume(T resource)
    {
        return consumeCallback.consume(resource);
    }

    public ResourceRequest(Class<T> resourceClass, IConsumeCallback<T> consumeCallback)
    {
        this.resourceClass = resourceClass;
        this.consumeCallback = consumeCallback;
        state = RequestState.CREATE;
    }

    protected void setRequestResolvedListener(RunnableOne<ResourceRequest> runnable)
    {
        this.requestResolvedListener = runnable;
    }

    public Class<?> getScope()
    {
        return receiverScope;
    }
}
