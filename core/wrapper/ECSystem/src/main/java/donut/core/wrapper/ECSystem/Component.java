package donut.core.wrapper.ECSystem;

import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceConsumer;
import donut.core.wrapper.RESquest.ResourceRequest;

public class Component implements IResourceConsumer {
    private ResourceConsumer consumer = new ResourceConsumer();

    @Override
    public ResourceConsumer getConsumer() {
        return consumer;
    }

    public <R, T extends Component> void requestResource(Class<R> resourceClass, Class<T> scopeClass, IConsumeCallback<R> iConsumeCallback)
    {
        consumer.postRequest(new ResourceRequest(resourceClass, scopeClass, iConsumeCallback));
    }
}
