import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceConsumer;
import donut.core.wrapper.RESquest.ResourceRequest;

public class TextConsumer2 implements IResourceConsumer {
    ResourceConsumer consumer = new ResourceConsumer();
    @Override
    public ResourceConsumer getConsumer() {
        return consumer;
    }

    String consumeString = "";

    public TextConsumer2()
    {
        consumer.postRequest(new ResourceRequest(String.class, new IConsumeCallback<String>() {
            @Override
            public boolean consume(String resource) {
                consumeString = resource;
                return false;
            }
        }));
    }
}
