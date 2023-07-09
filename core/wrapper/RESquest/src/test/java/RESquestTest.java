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
        ResourceProvider provider = new ResourceProvider();
        provider.addResource("hellobitch");

        int[] cnt = {0};

        IResourceConsumer consumer = new IResourceConsumer() {
            @Override
            public ResourceRequest[] getRequests() {
                return new ResourceRequest[]{new ResourceRequest(String.class,
                        new IConsumeCallback<String>() {
                            @Override
                            public boolean consume(String resource) {
                                cnt[0]++;
                                return true;
                            }
                        }
                )};
            }
        };


        provider.addConsumer(consumer);
        provider.processRequests();

        Assertions.assertEquals(1, cnt[0]);
    }

    public void test2()
    {
        ResourceProvider provider = new ResourceProvider();
        provider.addResource("hellobitch");

        int[] cnt = {0};

        IResourceConsumer consumer = new IResourceConsumer() {
            @Override
            public ResourceRequest[] getRequests() {
                return new ResourceRequest[]{new ResourceRequest(String.class,
                        new IConsumeCallback<String>() {
                            @Override
                            public boolean consume(String resource) {
                                cnt[0]++;
                                return false;
                            }
                        }
                )};
            }
        };

        provider.addConsumer(consumer);
        provider.processRequests();

        Assertions.assertTrue(cnt[0] > 1);
    }
}
