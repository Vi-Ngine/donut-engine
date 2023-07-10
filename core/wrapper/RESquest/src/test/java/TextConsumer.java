import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceConsumer;
import donut.core.wrapper.RESquest.ResourceRequest;

public class TextConsumer implements IResourceConsumer {
    ResourceConsumer consumer = new ResourceConsumer();
    @Override
    public ResourceConsumer getConsumer() {
        return consumer;
    }

    String consumeString = "";

    public TextConsumer()
    {
        consumer.postRequest(new ResourceRequest(String.class, TextConsumer.class, new IConsumeCallback<String>() {
            @Override
            public boolean consume(String resource) {
                consumeString = resource;
                return true;
            }
        }));
    }
}
