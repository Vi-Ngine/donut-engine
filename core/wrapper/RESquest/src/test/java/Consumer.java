import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceConsumer;
import donut.core.wrapper.RESquest.ResourceRequest;

public class Consumer implements IResourceConsumer, IConsumeCallback {
    ResourceConsumer consumer = new ResourceConsumer();
    @Override
    public ResourceConsumer getConsumer() {
        return consumer;
    }

    String stringResource = "";
    Integer intResource = 0;

    public Consumer()
    {
        consumer.postRequest(new ResourceRequest(String.class, Consumer.class, this));
        consumer.postRequest(new ResourceRequest(Integer.class, Consumer.class, this));
    }

    @Override
    public boolean consume(Object resource) {
        if(resource instanceof String)
        {
            stringResource = (String) resource;
            return true;
        }

        if(resource instanceof Integer)
        {
            intResource = (Integer) resource;
            return true;
        }

        return false;
    }
}
