import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceProvider;
import donut.core.wrapper.RESquest.ResourceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RESquestTest
{
    @Test
    public void test1()
    {
        TextProvider provider = new TextProvider();
        provider.addResource("hellobitch");

        TextConsumer consumer = new TextConsumer();
        provider.addConsumer(consumer);

        Assertions.assertTrue(consumer.consumeString.isEmpty());

        provider.processRequests();
        provider.processRequests();

        Assertions.assertEquals(0, consumer.getConsumer().getUnresolvedRequests().length);
        Assertions.assertEquals("hellobitch", consumer.consumeString);
    }

    public void test2()
    {
        TextProvider provider = new TextProvider();
        provider.addResource("hellobitch");

        TextConsumer2 consumer = new TextConsumer2();
        provider.addConsumer(consumer);

        Assertions.assertTrue(consumer.consumeString.isEmpty());

        provider.processRequests();
        provider.processRequests();

        Assertions.assertTrue(consumer.getConsumer().getUnresolvedRequests().length > 0);
        Assertions.assertEquals("hellobitch", consumer.consumeString);
    }
}
